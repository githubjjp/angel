package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pingan.angel.admin.api.mysql.DeviceEntity;
import com.pingan.stream.Service.BusinessService;
import com.pingan.stream.common.Content;
import com.pingan.stream.mysql.mapper.DeviceInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:iot.properties")
public class BusinessServiceImpl implements BusinessService {
    private static Logger logger= LoggerFactory.getLogger(BusinessServiceImpl.class);
    @Autowired
    private DeviceInfoMapper deviceInfoMapper;
    @Value("${iot.product.key}")
    private String productKey;
    @Value("${iot.test.url}")
    private String iotUrl;

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
        param.put("barcodeId",clientId.substring(clientId.lastIndexOf("@")+1));
        if(Content.IS_ONLINE.equals(msgCode)){
            param.put("isOnline","Y");
        }else{
            param.put("isOnline","N");
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
        String barcodeId=clientId.substring(clientId.lastIndexOf("@")+1);//设备配件码
        String msgContent=json.getString("content");//指令内容
        long ts=json.getLong("ts");//指令时间
        Map<String,String> paramMap=new HashMap<>();
        paramMap.put("barcodeId",barcodeId);
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
        String analysisResult=null;
        logger.info("上报指令解析后的数据::"+analysisResult);
        String deviceId=dto.getDeviceId();
        boolean flag=saveDeviceLog(deviceId,barcodeId,msgContent,ts);
        if(flag){
            logger.info("该消息已经被消费过，无需重复消费");
            return;
        }



    }

    /**
     *  保存日志信息
     * @param deviceId
     * @param barcodeId
     * @param content
     * @param timestamp
     * @return
     */
    private boolean saveDeviceLog(String deviceId,String barcodeId,String content,long timestamp){

        return false;
    }



}
