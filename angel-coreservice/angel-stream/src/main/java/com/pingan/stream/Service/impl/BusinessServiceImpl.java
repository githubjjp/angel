package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pingan.angel.admin.api.mongodb.DeviceLogEntity;
import com.pingan.angel.admin.api.mongodb.ErrorLogEntity;
import com.pingan.angel.admin.api.mongodb.QcDeviceLogEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import com.pingan.stream.Service.BusinessService;
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

import java.util.*;

@Service
@PropertySource("classpath:iot.properties")
public class BusinessServiceImpl implements BusinessService {
    private static Logger logger= LoggerFactory.getLogger(BusinessServiceImpl.class);
    @Autowired
    private DeviceInfoMapper deviceInfoMapper; //设备基本信息操作
    @Value("${iot.product.key}")
    private String productKey;   //产品key
    @Value("${iot.test.url}")
    private String iotUrl;       //iot url
    @Autowired
    private QcTestSuccessDeviceDao qcTestSuccessDeviceDao;  //产测成功记录操作
    @Autowired
    private QcDeviceLogDao qcDeviceLogDao;   //产测上报日志操作
    @Autowired
    private QcControlLogDao qcControlLogDao;//产测控制下发日志操作
    @Autowired
    private DeviceLogDao deviceLogDao;//非产测设备上报日志操作
    @Autowired
    private DeviceControlLogDao deviceControlLogDao;//非产测控制下发日志操作
    @Autowired
    private ErrorLogDao errorLogDao;//故障上报日志
    @Autowired
    private DeviceStatusMapper deviceStatusMapper;//设备状态操作
    @Autowired
    private DeviceErrorMapper deviceErrorMapper;//设备故障信息操作
    @Autowired
    private FilterElementMapper filterElementMapper;//滤芯状态操作




    @Override
    public void process(String message) {
        try{
            JSONObject msgJson= JSONObject.parseObject(message);
            String msgType=msgJson.getString("msgType");
            if(Content.MSTYPE_0.equals(msgType)){   //设备上下线消息
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
        String clientId=json.getString("clientId");//productKey@@deviceName   deviceName相当于配件码
        String msgCode=json.getString("msgCode");// 100-上线  101-下线
        long ts=json.getLong("ts");//上线时间戳
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("snCode",clientId.substring(clientId.lastIndexOf("@")+1));
        if(Content.IS_ONLINE.equals(msgCode)){
            param.put("isOnline","Y");//上线
        }else{
            param.put("isOnline","N");//下线
        }
        param.put("isOnlineDate",new Date(ts));
        deviceInfoMapper.updateOnlineStatus(param);
        logger.info("更新设备上下线状态ok");
    }

    @Override
    public Map<String, Object> isOnline(String deviceId) {
        return deviceInfoMapper.queryDeviceOnline(deviceId);
    }

    @Override
    public String issueCmd16(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd17(String deviceId, String barcodeId, String type) {
        return null;
    }

    @Override
    public String issueCmd18(String deviceId, String barcodeId) {
        return null;
    }

    @Override
    public String issueCmd20(String deviceId, String barcodeId, String type) {
        return null;
    }

    @Override
    public Map<String, Object> getConnectionStr(String deviceId) {
        Map<String,String> param=new HashMap<>();
        param.put("deviceId",deviceId);
        DeviceEntity dto=deviceInfoMapper.findByDeviceId(param);
        if(dto==null){
            logger.info("设备尚未注册");
            return null;
        }
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("deviceId",deviceId);
        retMap.put("deviceName",dto.getBarcodeId());//注册设备的时候是默认按照配件码作为设备名称
        retMap.put("productKey",productKey);
        retMap.put("deviceSecret",dto.getDeviceSecret());
        retMap.put("host",iotUrl);//连接地址
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
        String snCode=clientId.substring(clientId.lastIndexOf("@")+1);//设备配件码
        String msgContent=json.getString("content");//指令内容
        long ts=json.getLong("ts");//指令时间
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("snCode",snCode);
        DeviceEntity dto=deviceInfoMapper.findByDeviceId(paramMap);
        if(dto==null){
            logger.info("产品尚未注册");
            return;
        }
        /*
         * 业务处理步骤
         * 1. 根据设备id查询mongoDB  表QcTestSuccessDeviceEntity
         *       存在记录   -非产测设备
         *       无记录     -产测设备
         * 2. 根据是否产测设备保存日志到不同的表中,
         *    根据deviceId，barcodeId，ts保存前检查是否已经存在记录,防止消息重复消费;
         * 3. 调用动态解析组件进行解析
         * 4. 根据cmd 执行不同的业务处理
         *       有些 cmd指令需要下发指令或者反馈设备信息
         */
        String deviceId=dto.getDeviceId();
        boolean flag=saveDeviceLog(deviceId,snCode,msgContent,ts);
        if(flag){
            logger.info("该消息已经被消费过，无需重复消费");
            return;
        }
        String analysisResult=null;
        logger.info("上报指令解析后的数据::"+analysisResult);
        resolve(analysisResult,deviceId);
    }

    /**
     * 上报指令的业务处理
     * @param json
     * @param deviceId
     */
    private void resolve(String json,String deviceId){
        JSONObject jsonData=JSONObject.parseObject(json);
        int cmd=(Integer)jsonData.get("cmd");
        if(cmd==22){    //同步滤芯数据到设备状态表
            //设备基本状态信息
            DeviceStatusEntity status=new DeviceStatusEntity();
            status.setDeviceId(deviceId);
            status.setInTemperature(jsonData.getDoubleValue("inTemperature"));//进水水温
            status.setInTds(jsonData.getIntValue("inTds"));//进水tds
            status.setOutTds(jsonData.getIntValue("outTds"));//出水tds
            status.setOutTemperature(jsonData.getDoubleValue("outTemperature"));//出水温度
            status.setCmd(jsonData.getIntValue("cmd"));//cmd
            status.setTotalCleanWater(jsonData.getDoubleValue("totalCleanWater"));//总纯水量
            //status.setTotalUsedWater();
            status.setTotalWater(jsonData.getDoubleValue("totalWater"));//总水量
            status.setReportTime(jsonData.getDate("reportTime"));//最后一次上报时间
            //滤芯设置
            FilterElementEntity filter=new FilterElementEntity();
            filter.setDeviceId(deviceId);
            filter.setReportFlowFilterCount1(jsonData.getDoubleValue("reportFlowFilterCount1"));//滤芯1剩余流量
            filter.setReportHourFilterCount1(jsonData.getIntValue("reportHourFilterCount1"));//滤芯1剩余时长
            filter.setReportFlowFilterCount2(jsonData.getDoubleValue("reportFlowFilterCount2"));//滤芯2剩余流量
            filter.setReportHourFilterCount2(jsonData.getIntValue("reportHourFilterCount2"));//滤芯2剩余时长
            filter.setReportFlowFilterCount3(jsonData.getDoubleValue("reportFlowFilterCount3"));//滤芯3剩余流量
            filter.setReportHourFilterCount3(jsonData.getIntValue("reportHourFilterCount3"));//滤芯3剩余时长
            filter.setReportFlowFilterCount4(jsonData.getDoubleValue("reportFlowFilterCount4"));//滤芯4剩余流量
            filter.setReportHourFilterCount4(jsonData.getIntValue("reportHourFilterCount4"));//滤芯4剩余时长
            filter.setReportFlowFilterCount5(jsonData.getDoubleValue("reportFlowFilterCount5"));//滤芯5剩余流量
            filter.setReportHourFilterCount5(jsonData.getIntValue("reportHourFilterCount5"));//滤芯5剩余时长

            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("deviceId",deviceId);
            DeviceStatusEntity statusDtoOne=deviceStatusMapper.findByCondition(paramMap);
            if(statusDtoOne !=null){
                deviceStatusMapper.update(status);
                logger.info("设备状态基本信息更新ok.");
                filterElementMapper.update(filter);
                logger.info("设备滤芯状态信息更新ok.");
            }else{
                deviceStatusMapper.insert(status);
                logger.info("设备状态基本信息新增ok.");
                filterElementMapper.insert(filter);
                logger.info("设备滤芯状态信息新增ok.");
            }
        }else if(cmd==25){ //请求服务器数据指令

        }else if(cmd==29){   //设备上传机器状态

        }else if(cmd==32){   //请求升级文件指令

        }else if(cmd==35){   //滤芯复位上报指令

        }
    }

    /**
     *  保存日志信息
     * @param deviceId
     * @param snCode
     * @param content
     * @param timestamp
     * @return
     */
    private boolean saveDeviceLog(String deviceId,String snCode,String content,long timestamp){
        Query query=new Query(Criteria.where("deviceId").is(deviceId));
        QcTestSuccessDeviceEntity qcTest=qcTestSuccessDeviceDao.findOne(query);
        //根据  设备id，配件码，时间戳，原指令数据检查是否有保存过
        Query q2=new Query();
        q2.addCriteria(Criteria.where("deviceId").is(deviceId));
        q2.addCriteria(Criteria.where("snCode").is(snCode));
        q2.addCriteria(Criteria.where("timestamp").is(timestamp));
        q2.addCriteria(Criteria.where("json").is(content));

        Map<String,Object> originMap= JSONUtils.jsonToMap(content);
        int cmd=(Integer)originMap.get("cmd");
        logger.info("指令内容::"+originMap.toString());
        if(qcTest==null){ //检查产测设备完成表中是否有记录，如果有则表示设备通过了产测，反之为需要产测设备
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
            one.setData(getDynamicMap(originMap));
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
            one.setData(getDynamicMap(originMap));
            DeviceLogEntity ss=deviceLogDao.save(one);
            logger.info("非产测设备保存上报日志ok..."+ss.getId());
        }

        //保存故障上报日志
        if(cmd==21){
            int d1=(Integer)originMap.get("d1");//故障代码  0x0000: 无故障
            //int d2=(Integer)originMap.get("d2");//保护代码  0x0000: 无保护
             if(d1 !=0x0000){
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
                 one.setData(getDynamicMap(originMap));
                 one.setSnCode(snCode);
                 ErrorLogEntity ss=errorLogDao.save(one);
                 logger.info("故障日志保存Ok..."+ss.getId());
             }
        }
        return false;
    }



    /**
     * 获取对象属性之外的动态字段
     * @param origialMap
     * @return
     */
    private Map<String,Object> getDynamicMap(Map<String,Object> origialMap){
        Set<String> keys=origialMap.keySet();
        logger.info("keys"+ JSON.toJSONString(keys));
        LinkedHashMap map=new LinkedHashMap();
        for(String key:keys){
            if(key.indexOf("d")==0 && key.length() <=3){  //只需要d1-dn这些字段信息
                map.put(key,origialMap.get(key));
            }
        }
        return map;
    }



}
