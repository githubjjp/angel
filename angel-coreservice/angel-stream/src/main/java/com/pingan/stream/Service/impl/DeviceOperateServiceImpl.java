package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingan.stream.Service.DeviceOperateService;
import com.pingan.stream.common.CmdCommon;
import com.pingan.stream.common.Content;
import com.pingan.stream.dao.mongoDao.DeviceOperateDao;
import com.pingan.stream.entity.DeviceReportOrderEntity;
import com.pingan.stream.entity.OrderEntity;
import com.pingan.stream.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class DeviceOperateServiceImpl implements DeviceOperateService {
    private static Logger logger= LoggerFactory.getLogger(DeviceOperateServiceImpl.class);
    @Autowired
    private DeviceOperateDao deviceOperateDao;//保存所有的上报指令


    /**
     *  1.动态处理指令数据
     *  2.存指令到mongodb
     * @param data
     */
    @Override
    public void receiveDeviceOrder(String data) {
        logger.info("设备主动上报指令为::"+data);
       try{
           Map<String,Object> map= JSONUtils.jsonToMap(data);
           logger.info("原始指令   json转map::"+map.toString());
           String cmd=String.valueOf(map.get("cmd"));//命令字影响动态字段和一些业务
           String status="0";//指令状态 0-正常 1-异常
           String description=null;//具体说明 --指令异常才会有说明
           String serviceDeliveryId=null;//用于关联服务器下发信息
           if(cmd.equals(Content.CMD_21)){  //告警指令
                String d1=String.valueOf(map.get("d1"));//故障代码
                String d2=String.valueOf(map.get("d2"));//保护代码
                if(!d1.equals("0x0000") || !d2.equals("0x0000")){
                    status="1";
                }
                StringBuilder sb=new StringBuilder(CmdCommon.protectionCodeMap21.get(d2)).append(";").append(CmdCommon.errorCodeMap21.get(d1));
                description=sb.toString();
           }else if(cmd.equals(Content.CMD_22)){//设备主动上报数据指令
                //数据同步到设备的状态信息表
           }else if(cmd.equals(Content.CMD_25)){ //请求服务器数据指令格式
               String d1=String.valueOf(map.get("d1"));
               description= CmdCommon.cmdMap25.get(d1);
               serviceDeliveryId=getRandomStr();
               cmd25(map,serviceDeliveryId);//告知设备信息
           }else if(cmd.equals(Content.CMD_29)){ //设备上传机器状态指令
               String d1=String.valueOf(map.get("d1"));
               description= CmdCommon.cmdMap29.get(d1);
               serviceDeliveryId=getRandomStr();
               cmd29(map,serviceDeliveryId);//告知设备信息
           }else if(cmd.equals(Content.CMD_32)){ //请求升级文件指令
               serviceDeliveryId=getRandomStr();
               cmd32(map,serviceDeliveryId);//告知设备信息
           }else if(cmd.equals(Content.CMD_33)){ //升级完成状态指令
               String d1=String.valueOf(map.get("d1"));
               String d2=String.valueOf(map.get("d2"));
               if(!d1.equals("255") && !d2.equals("255")){
                   status="1";
                   description= CmdCommon.cmdMap33.get(d2);
               }
           }else if(cmd.equals(Content.CMD_34)){//定时上报心跳指令

           }else if(cmd.equals(Content.CMD_35)){ //滤芯复位上报指令
               String d1=String.valueOf(map.get("d1"));
               JSONObject son= JSON.parseObject(d1);
               String Bit0=String.valueOf(son.get("Bit0"));//0 表示滤芯1不复位，1 表示滤芯1复位
               String Bit1=String.valueOf(son.get("Bit1"));//0 表示滤芯2不复位，1 表示滤芯2复位
               String Bit2=String.valueOf(son.get("Bit2"));//0 表示滤芯3不复位，1 表示滤芯3复位
               String Bit3=String.valueOf(son.get("Bit3"));//0 表示滤芯4不复位，1 表示滤芯4复位
               String Bit4=String.valueOf(son.get("Bit4"));//0 表示滤芯5不复位，1 表示滤芯5复位
               boolean flag=Bit0.equals("1")&&Bit1.equals("1")&&Bit2.equals("1")&&Bit3.equals("1")&&Bit4.equals("1");
               if(!flag){
                   status="1";
                   StringBuilder sb=new StringBuilder();
                   sb.append(CmdCommon.cmdMap35_Bit0.get(Bit0)).append(";");
                   sb.append(CmdCommon.cmdMap35_Bit1.get(Bit1)).append(";");
                   sb.append(CmdCommon.cmdMap35_Bit2.get(Bit2)).append(";");
                   sb.append(CmdCommon.cmdMap35_Bit3.get(Bit3)).append(";");
                   sb.append(CmdCommon.cmdMap35_Bit4.get(Bit4)).append(";");
                   description=sb.toString();
               }
               serviceDeliveryId=getRandomStr();
               cmd35(map,status,serviceDeliveryId);//告知设备信息
           }

           //指令统一保存到deviceReportOrder中
           saveDeviceOrderCollection(map,status,description,serviceDeliveryId);
       }catch(Exception e){
           logger.error("设备主动上报指令处理异常",e);
       }
    }

    private String getRandomStr(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * 所有上报指令统一保存到deviceReportOrder中
     * @param map
     * @param status  指令状态
     * @param description  指令描述
     * @param serviceDeliveryId  关联服务下发的标识ID
     */
    private void saveDeviceOrderCollection(Map<String,Object> map,String status,String description,String serviceDeliveryId){
        DeviceReportOrderEntity order=new DeviceReportOrderEntity();
        Map<String,Object> dynamicParams=getDynamicMap(new OrderEntity(),map);
        //下面是所有指令的通用字段，直接赋值
        order.setCmd(Integer.valueOf(String.valueOf(map.get("cmd"))));//命令字
        order.setBarcodeid(String.valueOf(map.get("barcodeid")));//配件码
        order.setAddr(String.valueOf(map.get("addr")));//经纬度信息
        order.setCreatedTime(new Date());//当前系统时间
        order.setLen(Integer.valueOf(String.valueOf(map.get("len"))));//长度
        order.setFlag(Integer.valueOf(String.valueOf(map.get("flag"))));//起始标志
        order.setVersion(Integer.valueOf(String.valueOf(map.get("version"))));//版本
        order.setGprs(Integer.valueOf(String.valueOf(map.get("gprs"))));//gprs信号标志
        order.setCmdText(CmdCommon.cmdMap.get(String.valueOf(map.get("cmd"))));//指令说明
        order.setData(dynamicParams);
        order.setCmdText(Content.CMD_STYLE_0);
        order.setServiceDeliveryId(serviceDeliveryId);
        order.setDescription(description);
        order.setStatus(status);
        deviceOperateDao.save(order);//暂时先保存到这个指令表中
        logger.info("设备控制指令保存ok!");
    }


    /**
     * 获取对象属性之外的动态字段
     * @param obj
     * @param origialMap
     * @return
     */
    private Map<String,Object> getDynamicMap(Object obj,Map<String,Object> origialMap){
        Set<String> keys=origialMap.keySet();
        logger.info("keys"+JSON.toJSONString(keys));
        Field[] fields=obj.getClass().getDeclaredFields();//获取对象所有属性
        Set<String> compare=new HashSet<String>();//保存对象中的属性
        for(Field field:fields){
            compare.add(field.getName());
        }
        Map<String,Object> dynamic=new HashMap<String,Object>();
        for(String key:keys){
            if(!compare.contains(key)){
                dynamic.put(key,origialMap.get(key));
            }
        }
        logger.info("指令中剩余的动态变动字段--lastParams"+dynamic.toString());
        return dynamic;
    }

    @Override
    public List<DeviceReportOrderEntity> findByCmd(int cmd) {
        logger.info("请求参数::"+cmd);
        Query query=new Query();
        if(cmd!=0){
            query.addCriteria(Criteria.where("cmd").is(cmd));
        }
        return deviceOperateDao.find(query);
    }

    @Override
    public List<DeviceReportOrderEntity> findAll() {
        return deviceOperateDao.find(new Query());
    }

    @Override
    public DeviceReportOrderEntity findById(String id) {
        logger.info("请求参数::"+id);
        Query query=new Query(Criteria.where("id").is(id));
        return deviceOperateDao.findOne(query);
    }



    /**
     *  设备主动上报指令25需返回消息
     * @param dataMap
     */
    private void cmd25(Map<String,Object> dataMap,String serviceDeliveryId){
        if(String.valueOf(dataMap.get("d1")).equals("1")){  //1 表示请求服务器更新时间数据，服务器接收之后，发送校时指令，参见第3条的校时指令格式。(设备上电之后会主动请求一次校时指令)
            logger.info("cmd25----1");
        }else if(String.valueOf(dataMap.get("d1")).equals("2")){ //2 表示请求服务器滤芯最大寿命，滤芯剩余寿命，服务器接收之后，发送第14条指令，参见第14条滤芯数据下发指令格式。
            logger.info("cmd25----2");
        }
    }

    /**
     *  设备主动上报29指令返回信息
     * @param dataMap
     * @param serviceDeliveryId
     */
    private void cmd29(Map<String,Object> dataMap,String serviceDeliveryId){
        logger.info("cmd29----1");
    }

    /**
     *  设备主动上报32指令返回信息
     * @param dataMap
     * @param serviceDeliveryId
     */
    private void cmd32(Map<String,Object> dataMap,String serviceDeliveryId){
        logger.info("cmd32----1");
    }

    /**
     * 设备主动上报35指令返回信息
     * @param dataMap
     * @param status
     * @param serviceDeliveryId
     */
    public void cmd35(Map<String,Object> dataMap,String status,String serviceDeliveryId){//告知设备信息
        logger.info("cmd35----1");
    }






}
