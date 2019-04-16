package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.dto.req.RequestServer;
import com.pingan.angel.admin.api.mongodb.*;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceErrorEntity;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import com.pingan.stream.Service.BusinessService;
import com.pingan.stream.Service.IssueCmdService;
import com.pingan.stream.common.CmdCommon;
import com.pingan.stream.common.Content;
import com.pingan.stream.mongodb.dao.*;
import com.pingan.stream.mysql.mapper.DeviceErrorMapper;
import com.pingan.stream.mysql.mapper.DeviceInfoMapper;
import com.pingan.stream.mysql.mapper.DeviceStatusMapper;
import com.pingan.stream.mysql.mapper.FilterElementMapper;
import com.pingan.stream.utils.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:iot.properties")
public class BusinessServiceImpl implements BusinessService {
    private static Logger logger= LoggerFactory.getLogger(BusinessServiceImpl.class);
    @Value("${iot.product.key}")
    private String productKey;   //产品key
    @Value("${iot.test.url}")
    private String iotUrl;       //iot url
    @Autowired
    private DeviceInfoMapper deviceInfoMapper; //设备基本信息操作
    @Autowired
    private QcTestSuccessDeviceDao qcTestSuccessDeviceDao;  //产测成功记录操作
    @Autowired
    private QcDeviceLogDao qcDeviceLogDao;   //产测上报日志操作
    @Autowired
    private DeviceLogDao deviceLogDao;//非产测设备上报日志操作
    @Autowired
    private ErrorLogDao errorLogDao;//故障上报日志
    @Autowired
    private DeviceStatusMapper deviceStatusMapper;//设备状态操作
    @Autowired
    private DeviceErrorMapper deviceErrorMapper;//设备故障信息操作
    @Autowired
    private FilterElementMapper filterElementMapper;//滤芯状态操作
    @Autowired
    private IssueCmdService issueCmdService;  //下发指令入口
    @Autowired
    private QcDeviceInfoDao qcDeviceInfoDao;//产测设备操作



    @Override
    public void process(String message) {
        try{
            JSONObject msgJson= JSONObject.parseObject(message);
            String msgType=msgJson.getString("msgType");
            if(Content.MSTYPE_1.equals(msgType)){   //设备上下线消息
                onlineMsg(msgJson);
            }else{                                  //普通消息
                normalMsg(msgJson);
            }
        }catch(Exception e){
            logger.error("业务处理异常",e);
        }
    }

    /**
     * 处理设备上下线消息
     * @param json
     */
    public void  onlineMsg(JSONObject json){
        logger.info("处理设备上下线消息::"+json.toJSONString());
        String clientId=json.getString("clientId");//productKey@@deviceName   deviceName相当于整机码
        String snCode=clientId.substring(clientId.lastIndexOf("@")+1);//整机码/物流码
        /*
         *  检查产测通过记录表是否有记录，
         *     无记录：  更新产测设备表---mongodb
         *     有记录:   更新设备表--mysql
         */

        boolean isOnline=false;
        String msgCode=json.getString("msgCode");// 100-上线  101-下线
        long ts=json.getLong("ts");//上线时间戳
        if(Content.IS_ONLINE.equals(msgCode)){
            isOnline=true;
        }

        QcTestSuccessDeviceEntity qcTest=issueCmdService.getQcTestSuccessDeviceEntity(snCode,null);//产测成功记录表
         if(qcTest==null){  //尚未产测    以   mongodb中产测记录表为主
             Query query=new Query(Criteria.where("snCode").is(snCode));
             QcDeviceEntity qcDto=qcDeviceInfoDao.findOne(query);
             if(qcDto==null){
                 logger.info("设备尚未注册,无法更新上下线信息");
                 return;
             }
             qcDto.setOnline(isOnline);//是否在线
             qcDto.setUpdateTime(new Date());
             qcDto.setLastActiveTime(new Date(ts));//心跳时间
             qcDeviceInfoDao.updateById(qcDto.getId(),qcDto);
             logger.info("mongodb  产测设备表更新是否在线ok");
         }else{            //产测已完成
             String deviceId=qcTest.getDeviceId();
             DeviceEntity device=deviceInfoMapper.selectOne(Wrappers.<DeviceEntity>query()
                     .lambda().eq(DeviceEntity::getDeviceId, deviceId));
             if(device==null){
                 logger.info("设备信息不存在，更新上下线失败");
                 return;
             }
             device.setDeviceId(deviceId);
             device.setIsOnline(String.valueOf(isOnline));
             device.setIsOnlineDate(new Date(ts));
             deviceInfoMapper.update(device,Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
             logger.info("mysql  设备表更新是否在线ok");
         }
    }


    @Override
    public Map<String, Object> isOnline(String deviceId) {
        QcTestSuccessDeviceEntity qcTest=issueCmdService.getQcTestSuccessDeviceEntity(null,deviceId);//产测成功记录表
        Map<String,Object> resultMap=new HashMap<String,Object>();
        if(qcTest==null){  //产测设备
            Query query=new Query(Criteria.where("deviceId").is(deviceId));
            QcDeviceEntity qcDto=qcDeviceInfoDao.findOne(query);
            if(qcDto==null){
                logger.info("设备尚未注册，查询上下线信息异常");
                resultMap.put("status","99");
                resultMap.put("content","设备尚未产测");
                return resultMap;
            }
            resultMap.put("status","00");
            resultMap.put("content","查询ok");
            resultMap.put("isOnline",qcDto.isOnline());
            resultMap.put("lastActiveTime",qcDto.getLastActiveTime());
        }else{            //正常设备
            DeviceEntity device=deviceInfoMapper.selectOne(Wrappers.<DeviceEntity>query()
                    .lambda().eq(DeviceEntity::getDeviceId, deviceId));
            if(device==null){
                logger.info("设备已通过了产测，结果设备表无记录，无法查询上下线信息");
                resultMap.put("status","99");
                resultMap.put("content","设备未注册");
                return resultMap;
            }
            resultMap.put("status","00");
            resultMap.put("content","查询ok");
            resultMap.put("isOnline",device.getIsOnline());
            resultMap.put("lastActiveTime",device.getIsOnlineDate());
        }
        logger.info("查询上下线状态::"+resultMap.toString());
        return resultMap;
    }



    @Override
    public Map<String, Object> getConnectionStr(String deviceId) {
        /*
        *  获取连接字符串的逻辑梳理
        *  同步物流码系统获取一些信息---异步操作
        *  先判断是否为产测设备，从产测设备表或者设备信息表中获取相应的信息
         */


        QcTestSuccessDeviceEntity dto=issueCmdService.getQcTestSuccessDeviceEntity(null,deviceId);
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("deviceId",deviceId);
        retMap.put("productKey",productKey);
        retMap.put("host",iotUrl);//连接地址
        if(dto==null){
            Query query=new Query(Criteria.where("deviceId").is(deviceId));
            QcDeviceEntity qcDto=qcDeviceInfoDao.findOne(query);
            if(qcDto==null){
                logger.info("设备尚未注册，获取连接字符串异常");
                return new HashMap<>();
        }
            retMap.put("deviceName",productKey+"@@"+qcDto.getSnCode());//注册设备的时候是默认按照配件码作为设备名称
            retMap.put("deviceSecret",qcDto.getDeviceSecret());
        }else{
            DeviceEntity device=deviceInfoMapper.selectOne(Wrappers.<DeviceEntity>query()
                    .lambda().eq(DeviceEntity::getDeviceId, deviceId));
            if(device==null){
                logger.info("设备已通过了产测，结果设备表无记录，获取连接字符串异常");
                return new HashMap<>();
            }
            retMap.put("deviceName",productKey+"@@"+device.getSnCode());//注册设备的时候是默认按照配件码作为设备名称
            retMap.put("deviceSecret",device.getDeviceSecret());
        }
        logger.info("获取连接字符串::"+retMap.toString());
        return retMap;
    }



    /**
     * 处理设备普通消息
     * @param json
     */
    public void normalMsg(JSONObject json){
        logger.info("处理设备普通上报消息::"+json.toJSONString());
        String clientId=json.getString("clientId"); //productKey@@deviceName
        //String deviceName=clientId.substring(clientId.lastIndexOf("@")+1); //设备名称
        String snCode=clientId.substring(clientId.lastIndexOf("@")+1);//整机码
        String msgContent=json.getString("content");//指令内容
        long ts=json.getLong("ts");//指令时间
        /*
         * 业务处理步骤
         *  1. 检查产测通过记录表是否有数据判断当前设备是产测设备还是正常设备，更新标志字段
         *  2. 根据整机码判断产测设备表或者设备表是否有记录，无记录则不用处理上报的消息
         *  2. 保存上报消息日志
         *     isQcDevice=true     保存记录到产测上报日志表中
         *     isQcDevice=false    保存记录到正常上报日志表中
         *      注意: 根据deviceId，barcodeId，ts保存前检查是否已经存在记录,防止消息重复消费;
         *  4. 调用动态解析组件进行解析
         *  5. 根据cmd 执行不同的业务处理
         *       有些 cmd指令需要下发指令或者反馈设备信息
         */

        QcTestSuccessDeviceEntity testDto=issueCmdService.getQcTestSuccessDeviceEntity(snCode,null);
        boolean isQcDevice=false;//是否产测设备标志
        String deviceId=null;
        if(testDto==null){
            isQcDevice=true;
            Query query=new Query(Criteria.where("deviceId").is(testDto.getDeviceId()));
            QcDeviceEntity qcDto=qcDeviceInfoDao.findOne(query);
            if(qcDto==null){
                logger.info("产测阶段设备尚未注册，无法处理上报消息");
                return ;
            }
            deviceId=qcDto.getDeviceId();
        }else{
            DeviceEntity device=deviceInfoMapper.selectOne(Wrappers.<DeviceEntity>query()
                    .lambda().eq(DeviceEntity::getDeviceId, testDto.getDeviceId()));
            if(device==null){
                logger.info("设备已通过了产测，结果设备表无记录，无法处理上报消息");
                return ;
            }
            deviceId=device.getDeviceId();

        }

        boolean flag=saveDeviceLog(isQcDevice,deviceId,snCode,msgContent,ts);
        if(flag){
            logger.info("该消息已经被消费过，无需重复消费");
            return;
        }
        String analysisResult=null;
        logger.info("上报指令解析后的数据::"+analysisResult);
        resolve(analysisResult,deviceId,snCode);
    }

    /**
     *  保存日志信息
     * @param deviceId   设备Id
     * @param snCode     整机码
     * @param content    消息内容
     * @param timestamp  上报指令时间戳
     * @param isQcDevice  是否产测设备
     * @return
     */
    private boolean saveDeviceLog(boolean isQcDevice,String deviceId,String snCode,String content,long timestamp){
        //根据  设备id，配件码，时间戳，原指令数据检查是否有保存过
        Query q2=new Query();
        q2.addCriteria(Criteria.where("deviceId").is(deviceId));
        q2.addCriteria(Criteria.where("snCode").is(snCode));
        q2.addCriteria(Criteria.where("timestamp").is(timestamp));
        q2.addCriteria(Criteria.where("json").is(content));

        Map<String,Object> originMap= JSONUtils.jsonToMap(content);
        int cmd=(Integer)originMap.get("cmd");
        logger.info("指令内容::"+originMap.toString());
        if(isQcDevice){ //检查产测设备完成表中是否有记录，如果有则表示设备通过了产测，反之为需要产测设备
            QcDeviceLogEntity oldQcDeviceLog= qcDeviceLogDao.findOne(q2);//产测设备上报日志
            if(oldQcDeviceLog!=null){ //说明消息已经保存过一次了;
                logger.info("产测日志上报表中存在记录，无需重复保存");
                return true;
            }
            QcDeviceLogEntity one=new QcDeviceLogEntity();
            one.setDeviceId(deviceId);
            one.setBarcodeId(String.valueOf(originMap.get("barcodeid")));
            one.setJson(content);
            one.setTimestamp(timestamp);
            one.setLen((Integer)originMap.get("len"));
            one.setFlage((Integer)originMap.get("flag"));
            one.setCreateTime(new Date());
            one.setCreateUser("system");
            one.setVersion(String.valueOf(originMap.get("version")));
            one.setIMEI(String.valueOf(originMap.get("addr")));//当cmd=28的时候表示IMIO号,默认是经纬度
            one.setAddr(String.valueOf(originMap.get("addr")));
            one.setGprs(String.valueOf(originMap.get("gprs")));
            one.setCmd(cmd);
            one.setCmdText(CmdCommon.cmd_text_map.get(cmd));
            one.setSnCode(snCode);
            if(cmd==25){
                one.setGprsLv(String.valueOf(originMap.get("gprs")));//gprs信号强度
            }
            one.setData(JSONUtils.getDynamicMap(originMap));
            QcDeviceLogEntity ss=qcDeviceLogDao.save(one);
            logger.info("产测设备上报日志保存ok..."+ss.getId());
        }else{
            DeviceLogEntity oldDeviceLogEntity=deviceLogDao.findOne(q2);
            if(oldDeviceLogEntity!=null){
                logger.info("非产测设备日志上报表中存在记录，无需重复保存");
                return true;
            }
            DeviceLogEntity one=new DeviceLogEntity();
            one.setDeviceId(deviceId);
            one.setBarcodeId(String.valueOf(originMap.get("barcodeid")));
            one.setJson(content);
            one.setTimestamp(timestamp);
            one.setLen((Integer)originMap.get("len"));
            one.setFlage((Integer)originMap.get("flag"));
            one.setCreateTime(new Date());
            one.setCreateUser("system");
            one.setVersion(String.valueOf(originMap.get("version")));
            one.setIMEI(String.valueOf(originMap.get("addr")));//当cmd=28的时候表示IMIO号,默认是经纬度
            one.setAddr(String.valueOf(originMap.get("addr")));
            one.setGprs(String.valueOf(originMap.get("gprs")));
            one.setCmd(cmd);
            one.setCmdText(CmdCommon.cmd_text_map.get(cmd));
            one.setSnCode(snCode);
            if(cmd==25){
                one.setGprsLv(String.valueOf(originMap.get("gprs")));//gprs信号强度
            }
            one.setData(JSONUtils.getDynamicMap(originMap));
            DeviceLogEntity ss=deviceLogDao.save(one);
            logger.info("非产测设备保存上报日志ok..."+ss.getId());
        }

        //保存故障上报日志
        if(cmd==21){
            int d1=(Integer)originMap.get("d1");//故障代码  0x0000: 无故障
            int d2=(Integer)originMap.get("d2");//保护代码  0x0000: 无保护
            if(d1 !=0x0000){
                //mongodb操作
                ErrorLogEntity one=new ErrorLogEntity();
                one.setDeviceId(deviceId);
                one.setBarcodeId(String.valueOf(originMap.get("barcodeid")));
                one.setJson(content);
                one.setTimestamp(timestamp);
                one.setLen((Integer)originMap.get("len"));
                one.setFlage((Integer)originMap.get("flag"));
                one.setCreateTime(new Date());
                one.setCreateUser("system");
                one.setVersion(String.valueOf(originMap.get("version")));
                one.setIMEI(String.valueOf(originMap.get("addr")));//当cmd=28的时候表示IMIO号,默认是经纬度
                one.setAddr(String.valueOf(originMap.get("addr")));
                one.setGprs(String.valueOf(originMap.get("gprs")));
                one.setCmd(cmd);
                one.setCmdText(CmdCommon.cmd_text_map.get(cmd));
                one.setData(JSONUtils.getDynamicMap(originMap));
                one.setSnCode(snCode);
                ErrorLogEntity ss=errorLogDao.save(one);
                logger.info("故障日志保存 mongodb  Ok..."+ss.getId());
            }
            //mysql操作
            DeviceErrorEntity error=new DeviceErrorEntity();
            error.setDeviceId(deviceId);
            error.setSnCode(snCode);
            error.setFaultCode(d1);
            error.setFaultContent(CmdCommon.CMD_FAULT_CONTENT.get(d1));
            error.setProtectCode(d2);
            error.setProtectContent(CmdCommon.CMD_PROTECT_CONTENT.get(d2));
            if(d1 ==0x0000 && d2==0x0000){
                error.setIsDeal("Y");//设备正常
            }else{
                error.setIsDeal("N");//设备故障
            }
            error.setLastPostTime(new Date(timestamp));
            DeviceErrorEntity errorOne=deviceErrorMapper.findByDeviceId(deviceId);
            if(errorOne !=null){
                error.setId(errorOne.getId());
                deviceErrorMapper.updateById(error);
                logger.info("设备故障信息更新mysql   ok.");
            }else{
                deviceErrorMapper.insert(error);
                logger.info("设备故障信息新增mysql   ok.");
            }
        }
        return false;
    }

    /**
     * 上报指令的业务处理
     * @param json
     * @param deviceId
     * @param snCode 整机码
     */
    private void resolve(String json,String deviceId,String snCode){
        JSONObject jsonData=JSONObject.parseObject(json);
        logger.info("待处理业务的解析后数据::"+jsonData.toJSONString());
        int cmd=(Integer)jsonData.get("cmd");
        if(cmd==22){    //同步滤芯数据到设备状态表
            //设备基本状态信息


        }else if(cmd==25){ //请求服务器数据指令
            RequestServer rs=JSONUtils.toObejct(json,RequestServer.class);
            int type=rs.getType();
            if(type==1){   //请求校时指令
                issueCmdService.issueCmd18(deviceId);
            }else{       //滤芯数据下发指令
                issueCmdService.issueCmd30(deviceId);
            }
        }else if(cmd==29){   //设备上传机器状态
            int d1=jsonData.getIntValue("d1");
            //更新设备状态表deviceState字段状态
            Map<String,Object> param=new HashMap<String,Object>();
            param.put("deviceId",deviceId);
            if(d1==0x01){ //制水
                param.put("deviceState",1);
            }else if(d1==0x02){
                param.put("deviceState",2); //满水
            }else{
                param.put("deviceState",3); //其他  ---扩展
            }
            deviceStatusMapper.updateByDeviceId(param);
            logger.info("设备制水状态更新ok");
            issueCmdService.issueCmd29(deviceId);
        }else if(cmd==32){   //请求升级文件指令
            issueCmdService.issueCmd32(deviceId);

        }else if(cmd==33){
            int d1=jsonData.getIntValue("d1");
            int d2=jsonData.getIntValue("d2");
            if(d1==255 && d2==255) {
                logger.info("设备升级成功");
            }else{
                logger.info("设备升级失败::"+CmdCommon.CMD35_D2_CONTENT.get(d2));
            }
        }else if(cmd==34){  //定时上报心跳指令
            DeviceEntity device=new DeviceEntity();
            device.setProgramMainSupplier(jsonData.getIntValue("programMainSupplier"));
            device.setProgramMainVersion(jsonData.getIntValue("programMainVersion"));
            device.setProgramMainVersionName(jsonData.getIntValue("programMainVersion")==100?"V1.00版本":"V1.01版本");
            device.setMac(jsonData.getString("mac"));
            device.setIccid(jsonData.getString("ccid"));//WIFI版本不用上传CCID，GPRS版本上传此字段信息。
            device.setIsOnlineDate(new Date());//正常情况每次10分钟上报一次

            device.setProgramSmallSupplier(jsonData.getIntValue("programSmallSupplier"));
            device.setProgramSmallVersion(jsonData.getIntValue("programSmallVersion"));
            device.setProgramSmallVersionName(jsonData.getIntValue("programSmallVersion")==100?"V1.00版本":"V1.01版本");
            //更新设备信息表
            deviceInfoMapper.update(device, Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
            logger.info("更新设备上报心跳信息ok.");
        }else if(cmd==35){   //滤芯复位上报指令
            FilterElementEntity filter=new FilterElementEntity();//设备滤芯状态信息
            filter.setReportHourFilterCount1(jsonData.getIntValue("reportHourFilterCount1"));
            filter.setReportFlowFilterCount1(jsonData.getDoubleValue("ReportFlowFilterCount1"));
            filter.setReportHourFilterCount2(jsonData.getIntValue("reportHourFilterCount2"));
            filter.setReportFlowFilterCount2(jsonData.getDoubleValue("ReportFlowFilterCount2"));
            filter.setReportHourFilterCount3(jsonData.getIntValue("reportHourFilterCount3"));
            filter.setReportFlowFilterCount3(jsonData.getDoubleValue("ReportFlowFilterCount3"));
            filter.setReportHourFilterCount4(jsonData.getIntValue("reportHourFilterCount4"));
            filter.setReportFlowFilterCount4(jsonData.getDoubleValue("ReportFlowFilterCount4"));
            filter.setReportHourFilterCount5(jsonData.getIntValue("reportHourFilterCount5"));
            filter.setReportFlowFilterCount5(jsonData.getDoubleValue("ReportFlowFilterCount5"));
            filterElementMapper.update(filter,Wrappers.<FilterElementEntity>query().lambda().eq(FilterElementEntity::getDeviceId, deviceId));
            logger.info("更新滤芯状态信息ok。。");

            JSONObject jsonD1=jsonData.getJSONObject("d1");
            logger.info("复位标志---jsonD1::"+jsonD1.toJSONString());
            int Bit0=jsonD1.getIntValue("Bit0"); //滤芯1复位  0-不复位  1-复位
            int Bit1=jsonD1.getIntValue("Bit1"); //滤芯2复位  0-不复位  1-复位
            int Bit2=jsonD1.getIntValue("Bit2"); //滤芯3复位  0-不复位  1-复位
            int Bit3=jsonD1.getIntValue("Bit3"); //滤芯4复位  0-不复位  1-复位
            int Bit4=jsonD1.getIntValue("Bit4");  //滤芯5复位  0-不复位  1-复位
            boolean flag = Bit0==1 && Bit1==1 && Bit2==1 && Bit3==1 && Bit4==1;
            if(flag){
                issueCmdService.issueCmd35(deviceId,0xff);//成功
            }else{
                issueCmdService.issueCmd35(deviceId,0x00);//成功
            }

        }
    }





}
