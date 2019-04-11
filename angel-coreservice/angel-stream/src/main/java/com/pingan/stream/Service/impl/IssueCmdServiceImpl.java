package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.pingan.angel.admin.api.mongodb.DeviceControlLogEntity;
import com.pingan.angel.admin.api.mongodb.QcControlLogEntity;
import com.pingan.angel.admin.api.mongodb.QcTestSuccessDeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.angel.admin.api.mysql.DeviceStatusEntity;
import com.pingan.angel.admin.api.mysql.FilterElementEntity;
import com.pingan.stream.Service.IotHubService;
import com.pingan.stream.Service.IssueCmdService;
import com.pingan.stream.common.CmdCommon;
import com.pingan.stream.mongodb.dao.DeviceControlLogDao;
import com.pingan.stream.mongodb.dao.QcControlLogDao;
import com.pingan.stream.mongodb.dao.QcTestSuccessDeviceDao;
import com.pingan.stream.mysql.mapper.DeviceInfoMapper;
import com.pingan.stream.mysql.mapper.DeviceStatusMapper;
import com.pingan.stream.mysql.mapper.FilterElementMapper;
import com.pingan.stream.utils.JSONUtils;
import com.pingan.stream.utils.StringUtils;
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
public class IssueCmdServiceImpl implements IssueCmdService {
    private static Logger logger= LoggerFactory.getLogger(IssueCmdServiceImpl.class);
    @Value("${iot.product.key}")
    private String productKey;   //产品key
    @Value("${iot.test.url}")
    private String iotUrl;       //iot url

    @Autowired
    private QcControlLogDao qcControlLogDao;//产测控制下发日志操作
    @Autowired
    private DeviceControlLogDao deviceControlLogDao;//非产测控制下发日志操作
    @Autowired
    private IotHubService iotHubService;//iot操作类
    @Autowired
    private DeviceInfoMapper deviceInfoMapper; //设备基本信息操作
    @Autowired
    private QcTestSuccessDeviceDao qcTestSuccessDeviceDao;  //产测成功记录操作
    @Autowired
    private DeviceStatusMapper deviceStatusMapper;//设备状态操作
    @Autowired
    private FilterElementMapper filterElementMapper;//滤芯状态操作

    private DeviceEntity getDeviceEntity(String deviceId){
        Map<String,String> param=new HashMap<>();
        param.put("deviceId",deviceId);
        DeviceEntity dto=deviceInfoMapper.findByDeviceId(param);
        return dto;
    }

    @Override
    public String issueCmd16(String deviceId, String barcodeId) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("用户实时获取数据指令   deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，用户实时获取数据失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，用户实时获取数据异常");
            return JSON.toJSONString(retMap);
        }
        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,16,deviceId));
    }



    @Override
    public String issueCmd17(String deviceId, String barcodeId, String type) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("设备控制指令   deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，设备控制指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，设备控制指令异常");
            return JSON.toJSONString(retMap);
        }
        int operateType=0; //控制类型
        if("1".equals(type)){
            operateType=0x01;//冲洗
        }else if("2".equals(type)){
            operateType=0x02;//开机
        }else if("3".equals(type)){
            operateType=0x04;//关机
        }else if("4".equals(type)){
            operateType=0x08;//锁机
        }else if("5".equals(type)){
            operateType=0x10;//预留
        }else if("6".equals(type)){
            operateType=0x20;//预留
        }else if("7".equals(type)){
            operateType=0x40;//预留
        }else if("8".equals(type)){
            operateType=0x80;//预留
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,17,deviceId));
    }

    @Override
    public void issueCmd18(String deviceId) {
        logger.info("校准时间指令   deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，校准指令发送失败");
            return;
        }
        Date date=new Date();//当前系统时间
        String requestJSON=null;    //调用动态解析组件组装数据
        operate(dto,requestJSON,18,deviceId);
    }

    @Override
    public String issueCmd20(String deviceId, String barcodeId, String type) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("认证指令   deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，认证指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，认证指令异常");
            return JSON.toJSONString(retMap);
        }
        FilterElementEntity filterDto=filterElementMapper.findByDeviceId(deviceId);//滤芯状态表
        int d1=0;
        if("1".equals(type)){
            d1=0x01 ;//第1个滤芯认证
            int d2=filterDto.getInvalidDateFilter1();//总时长
            double d3=filterDto.getConfigTotalFlowFilterCount1();//总流量
        }else if("1".equals(type)){
            d1=0x02 ;//第2个滤芯认证
            int d2=filterDto.getInvalidDateFilter2();//总时长
            double d3=filterDto.getConfigTotalFlowFilterCount2();//总流量
        }else if("3".equals(type)){
            d1=0x03 ;//第3个滤芯认证
            int d2=filterDto.getInvalidDateFilter3();//总时长
            double d3=filterDto.getConfigTotalFlowFilterCount3();//总流量
        }else if("4".equals(type)){
            d1=0x04 ;//第4个滤芯认证
            int d2=filterDto.getInvalidDateFilter4();//总时长
            double d3=filterDto.getConfigTotalFlowFilterCount4();//总流量
        }else if("5".equals(type)){
            d1=0x05 ;//第3个滤芯认证
            int d2=filterDto.getInvalidDateFilter5();//总时长
            double d3=filterDto.getConfigTotalFlowFilterCount5();//总流量
        }else if("6".equals(type)){   //发送开机码
            d1=0x06;
            String d2=dto.getActiveId(); //开机码字符串
            int d3=00;
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,20,deviceId));
    }

    @Override
    public String issueCmd23(String deviceId, String barcodeId, int hour) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("发送上报数据的时间间隔指令   deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，发送上报数据的时间间隔指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，发送上报数据的时间间隔指令异常");
            return JSON.toJSONString(retMap);
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,20,deviceId));
    }

    @Override
    public String issueCmd24(String deviceId, String barcodeId) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("清除认证状态指令  deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，清除认证状态指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，清除认证状态指令异常");
            return JSON.toJSONString(retMap);
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,24,deviceId));
    }

    @Override
    public String issueCmd26(String deviceId, String barcodeId) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("请求SIM卡的CCID指令  deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，请求SIM卡的CCID指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，请求SIM卡的CCID指令异常");
            return JSON.toJSONString(retMap);
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,26,deviceId));
    }

    @Override
    public String issueCmd27(String deviceId, String barcodeId, int type) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("工作模式选择指令  deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，工作模式选择指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，工作模式选择指令异常");
            return JSON.toJSONString(retMap);
        }
        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,27,deviceId));
    }

    @Override
    public String issueCmd28(String deviceId, String barcodeId) {
        Map<String,Object> retMap=new HashMap<>();
        logger.info("测试模式读取数据指令  deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，测试模式读取数据指令发送失败");
            retMap.put("code","99");
            retMap.put("msg","设备尚未注册，测试模式读取数据指令异常");
            return JSON.toJSONString(retMap);
        }

        String requestJSON=null;//调用解析组件
        return JSON.toJSONString(operate(dto,requestJSON,28,deviceId));
    }

    @Override
    public void issueCmd29(String deviceId) {
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，反馈设备状态信息失败");
            return;
        }
        String requestStr=null;
        operate(dto,requestStr,29,deviceId);
    }

    @Override
    public void issueCmd30(String deviceId) {
        logger.info("滤芯数据下发指令---deviceId::"+deviceId);
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，滤芯数据下发指令失败");
            return;
        }
        DeviceStatusEntity statusDto=deviceStatusMapper.findByDeviceId(deviceId);//设备状态表
        FilterElementEntity filterDto=filterElementMapper.findByDeviceId(deviceId);//滤芯状态表
        if(statusDto==null){
            logger.info("滤芯数据下发指令失败");
            return;
        }

        String requestJSON=null;    //调用动态解析组件组装数据
        operate(dto,requestJSON,30,deviceId);
    }

    @Override
    public String issueCmd31(String deviceId, String barcodeId) {
        return null;
    }



    @Override
    public void issueCmd32(String deviceId) {
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，反馈设备状态信息失败");
            return;
        }
        String requestStr=null;
        operate(dto,requestStr,32,deviceId);
    }

    @Override
    public void issueCmd35(String deviceId, int d1) {
        DeviceEntity dto=getDeviceEntity(deviceId);
        if(dto==null){
            logger.info("设备尚未注册，反馈设备滤芯复位");
            return;
        }
        String requestStr=null;
        operate(dto,requestStr,35,deviceId);
    }

    /**
     *  1. 控制指令保存日志，有些上报指令反馈设备的无需保存
     *  2. 请求IOT下发设备数据
     *  3. 解析IOT返回的结果
     * @param dto
     * @param requestJSON
     * @param cmd
     * @param deviceId
     * @return
     */
    private Map<String,Object> operate(DeviceEntity dto,String requestJSON,int cmd,String deviceId){
        /*
         *  控制下发指令需要保存在mongodb对应表中
         */
        if(cmd !=35 && cmd !=29){   //上报指令的一些返回不需要保存
            saveIssueLog(deviceId,requestJSON,cmd,dto.getSnCode());
        }
        /*
         * 请求IOT
         */
        String deviceName=productKey+"@@"+dto.getSnCode();
        String result=iotHubService.publish(deviceName,requestJSON,"1");//至少发送一次
        return getResponse(deviceId,result,cmd);
    }

    /**
     * 保存控制下发日志
     * @param deviceId  设备id
     * @param jsonStr   指令内容
     * @param cmd       命令字
     * @param snCode    整机码
     */
    private void saveIssueLog(String deviceId,String jsonStr,int cmd,String snCode){
        Query query=new Query(Criteria.where("deviceId").is(deviceId));
        QcTestSuccessDeviceEntity qcTest=qcTestSuccessDeviceDao.findOne(query);
        Map<String,Object> originMap= JSONUtils.jsonToMap(jsonStr);
        if(qcTest==null){  //产测设备
            QcControlLogEntity one=new QcControlLogEntity();
            one.setDeviceId(deviceId);
            one.setBarcodeId(String.valueOf(originMap.get("barcodeid")));
            one.setJson(jsonStr);
            one.setTimestamp(new Date().getTime());
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
            one.setData(JSONUtils.getDynamicMap(originMap));
            qcControlLogDao.save(one);
            logger.info("保存产测设备 控制下发日志ok.");
        } else{           //正常设备
            DeviceControlLogEntity one=new DeviceControlLogEntity();
            one.setDeviceId(deviceId);
            one.setBarcodeId(String.valueOf(originMap.get("barcodeid")));
            one.setJson(jsonStr);
            one.setTimestamp(new Date().getTime());
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
            one.setData(JSONUtils.getDynamicMap(originMap));
            deviceControlLogDao.save(one);
            logger.info("保存非产测设备 控制下发日志ok.");
        }
    }

    /**
     *  解析结果集
     * @param responseJSON
     * @param cmd
     * @return
     */
    private Map<String,Object> getResponse(String deviceId,String responseJSON,int cmd){
        Map<String,Object> retMap=new HashMap<>();
        JSONObject json=JSONObject.parseObject(responseJSON);
        String code=json.getString("code");
        if(StringUtils.isNotEmpty(code)){
            logger.info("请求iot失败");
            return JSONUtils.jsonToMap(code);
        }
        String status=json.getString("status");
        if(status.equals("true")){
            JSONObject resultJSON=JSONObject.parseObject(json.getString("content"));
            retMap.put("code","00");
            retMap.put("msg","请求成功");
            Map<String,Object> dataMap=new HashMap<>();
            if(cmd==18){ //校时指令
                int d1=resultJSON.getIntValue("d1");
                if(d1==0xff){
                    dataMap.put("cmdMsg","校时设置失败");
                }else{
                    dataMap.put("cmdMsg","校时设置成功");
                }
            }else if(cmd==30){
                int d1=resultJSON.getIntValue("d1");
                if(d1==0xff){
                    dataMap.put("cmdMsg","设备接受滤芯数据失败");
                }else{
                    dataMap.put("cmdMsg","设备接受滤芯数据成功");
                }
            }else if(cmd==29){
                dataMap.put("cmdMsg","反馈设备状态ok.");
            }else if(cmd==32){
                dataMap.put("cmdMsg","反馈设备文件升级ok.");
            }else if(cmd==16){
                updateFileStatus(deviceId,resultJSON);
                dataMap.put("cmdMsg","获取设备数据ok");
            }else if(cmd==17){
                int d2=resultJSON.getIntValue("d2");
                int d1=resultJSON.getIntValue("d1");
                if(d2==0xff){
                    dataMap.put("status","成功");
                }else{
                    dataMap.put("status","失败");
                }
                dataMap.put("modelType",d1);
                dataMap.put("modelContent",CmdCommon.CMD17_MODEL_TYPE.get(d1));
            }else if(cmd==20){   //设备认证
                //需要更新设备表中的滤芯认证字段
                int d1=resultJSON.getIntValue("d1"); //认证滤芯X
                int d2=resultJSON.getIntValue("d2");//结果
                updateFilterAuthor(deviceId,dataMap,d1,d2);
            }else if(cmd==23){  //发送上报数据的时间间隔指令
                int d1=resultJSON.getIntValue("d1");
                if(d1==0xff){
                    dataMap.put("status","成功");
                }else{
                    dataMap.put("status","失败");
                }
            }else if(cmd==24){   //清除认证
                int d2=resultJSON.getIntValue("d2");//结果
                if(d2==0xff){ //成功
                    DeviceEntity device=new DeviceEntity();
                    device.setDeviceId(deviceId);
                    device.setFilterAuthor1("N"); //清除滤芯1认证
                    device.setFilterAuthor2("N");//清除滤芯2认证
                    device.setFilterAuthor3("N");//清除滤芯3认证
                    device.setFilterAuthor4("N");//清除滤芯4认证
                    device.setFilterAuthor5("N");//清除滤芯5认证
                    device.setIsAuthorization("N");//清除设备认证
                    deviceInfoMapper.update(device,Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
                    logger.info("更新滤芯认证信息ok");
                    dataMap.put("status","成功");
                }else{
                    dataMap.put("status","失败");
                }
            }else if(cmd==26){   //请求SIM卡的CCID指令
                 int d1=resultJSON.getIntValue("d1");//ccid
                 DeviceEntity device=new DeviceEntity();
                 device.setDeviceId(deviceId);
                 device.setCcid(d1);
                 deviceInfoMapper.update(device,Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
                 logger.info("更新设备ccid值ok.");
                dataMap.put("cmdMsg","请求SIM卡的CCID指令成功");
            }else if(cmd==27) {  //工作模式选择指令
                int d1=resultJSON.getIntValue("d1");//1-正常模式  2-工厂测试模式
                int d2=resultJSON.getIntValue("d2");
                if(d2==0xff){
                    dataMap.put("status","成功");
                }else{
                    dataMap.put("status","失败");
                }
                dataMap.put("modelType",d1);
                dataMap.put("modelContent",CmdCommon.CMD27_MODEL_TYPE.get(d1));
            }else if(cmd==28){   //测试模式读取数据指令
                updateFileStatus(deviceId,resultJSON);
                dataMap.put("status","成功");
            }
            retMap.put("data",dataMap);
        }else{
            retMap.put("code","99");
            retMap.put("msg",json.getString("desc"));
        }
        logger.info("请求IOT的结果::"+JSON.toJSONString(retMap));
        return retMap;
    }

    /**
     *  滤芯认证处理
     * @param dataMap
     * @param d1
     * @param d2
     * @param deviceId
     */
    private void updateFilterAuthor(String deviceId,Map<String,Object> dataMap,int d1,int d2){
        if(d2==0xff){ //成功
            DeviceEntity device=new DeviceEntity();
            device.setDeviceId(deviceId);
            if(d1==0x01){   //第1个滤芯认证
                device.setFilterAuthor1("Y");
            }else if(d1==0x02){   //第2个滤芯认证
                device.setFilterAuthor2("Y");
            }else if(d1==0x03){    //第3个滤芯认证
                device.setFilterAuthor3("Y");
            }else if(d1==0x04){     //第4个滤芯认证
                device.setFilterAuthor4("Y");
            }else if(d1==0x05){    //第5个滤芯认证
                device.setFilterAuthor5("Y");
            }else if(d1==0x06){    //第5个滤芯认证
                device.setIsAuthorization("Y");
            }
            deviceInfoMapper.update(device,Wrappers.<DeviceEntity>query().lambda().eq(DeviceEntity::getDeviceId, deviceId));
            logger.info("更新滤芯认证信息ok");
            dataMap.put("status","成功");
        }else{
            dataMap.put("status","失败");
        }
    }

    /**
     * 更新滤芯信息
     * @param deviceId
     * @param resultJSON
     */
    @Override
    public void updateFileStatus(String deviceId,JSONObject resultJSON){
        DeviceStatusEntity statusDto=deviceStatusMapper.findByDeviceId(deviceId);//设备状态表
        //FilterElementEntity filterDto=filterElementMapper.findByDeviceId(deviceId);//滤芯状态表

        DeviceStatusEntity status=new DeviceStatusEntity();
        status.setInTemperature(resultJSON.getDoubleValue("inTemperature"));//进水温度
        status.setOutTemperature(resultJSON.getDoubleValue("outTemperature"));//出水温度
        status.setInTds(resultJSON.getIntValue("inTds"));//进水tds
        status.setOutTds(resultJSON.getIntValue("outTds"));//出水tds
        status.setRateOfDesalination(resultJSON.getDoubleValue("rateOfDesalination"));//脱盐率
        status.setTotalWater(resultJSON.getDoubleValue("totalWater"));//总水量
        status.setTotalCleanWater(resultJSON.getDoubleValue("totalCleanWater"));//净水总量
        status.setDeviceId(deviceId);

        FilterElementEntity filter=new FilterElementEntity();
        filter.setReportFlowFilterCount1(resultJSON.getDoubleValue("reportFlowFilterCount1"));
        filter.setReportHourFilterCount1(resultJSON.getIntValue("reportHourFilterCount1"));
        filter.setReportFlowFilterCount2(resultJSON.getDoubleValue("reportFlowFilterCount2"));
        filter.setReportHourFilterCount2(resultJSON.getIntValue("reportHourFilterCount2"));
        filter.setReportFlowFilterCount3(resultJSON.getDoubleValue("reportFlowFilterCount3"));
        filter.setReportHourFilterCount3(resultJSON.getIntValue("reportHourFilterCount3"));
        filter.setReportFlowFilterCount4(resultJSON.getDoubleValue("reportFlowFilterCount4"));
        filter.setReportHourFilterCount4(resultJSON.getIntValue("reportHourFilterCount4"));
        filter.setReportFlowFilterCount5(resultJSON.getDoubleValue("reportFlowFilterCount5"));
        filter.setReportHourFilterCount5(resultJSON.getIntValue("reportHourFilterCount5"));
        filter.setDeviceId(deviceId);
        if(statusDto==null){
            deviceStatusMapper.insert(status);
            logger.info("新增设备状态信息ok");
            filterElementMapper.insert(filter);
            logger.info("新增滤芯状态信息ok");
        }else{
            deviceStatusMapper.update(status, Wrappers.<DeviceStatusEntity>query().lambda().eq(DeviceStatusEntity::getDeviceId, deviceId));
            logger.info("更新设备状态信息ok");
            filterElementMapper.update(filter,Wrappers.<FilterElementEntity>query().lambda().eq(FilterElementEntity::getDeviceId, deviceId));
            logger.info("更新滤芯状态信息ok");
        }
    }




}
