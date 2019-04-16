package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSONObject;
import com.pingan.stream.Service.IotHubService;
import com.pingan.stream.common.Content;
import com.pingan.stream.utils.StringUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("iotHubService")
@PropertySource("classpath:iot.properties")
public class IotHubServiceImpl implements IotHubService {
    private static Logger logger= LoggerFactory.getLogger(IotHubServiceImpl.class);
    @Value("${iot.test.url}")
    private String iotUrl;
    @Value("${iot.test.accessKey}")
    private String accessKeyId;
    @Value("${iot.test.accessKeySecret}")
    private String accessKeySecret;
    @Value("${iot.topic.first.part}")
    private String topicFirst;
    @Value("${iot.topic.third.part}")
    private String topicThird;
    @Value("${iot.product.key}")
    private String productKey;
    @Value("${iot.product.secret}")
    private String productSecret;

    @Override
    public String publish(String deviceName, String content, String qos) {
        Map<String,Object> map=new HashMap<>();
        map.put("productKey",productKey);
        map.put("deviceName",deviceName);
        String topic=new StringBuffer(topicFirst).append(deviceName).append(topicThird).toString();
        map.put("topicName",topic);
        map.put("content",content);
        map.put("gos",qos);
        String action="Publish";
        logger.info("请求iot 参数::"+map.toString()+"   action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    public String reqFail(){
        JSONObject json=new JSONObject();
        json.put("code", Content.REQ_ERROR);
        json.put("msg", Content.REQ_ERROR_CONTENT);
        json.put("data", new JSONObject());
        return json.toJSONString();
    }


    @Override
    public String publishBroadcast(String content, String qos) {
        Map<String,Object> map=new HashMap<>();
        map.put("productKey",productKey);
        map.put("content",content);
        map.put("gos",qos);
        String action="PublishBroadcast";
        logger.info("请求iot 参数::"+map.toString()+"   action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String createProduct(String productName, String productDesc, String authId, String region) {
        Map<String,Object> map=new HashMap<>();
        map.put("productName",productName);
        map.put("productDesc",productDesc);
        map.put("authId",authId);
        map.put("region",region);
        String action="CreateProduct";
        logger.info("请求iot 参数::"+map.toString()+"   action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String queryProduct(String productId) {
        Map<String,Object> map=new HashMap<>();
        map.put("id",productId);
        String action="QueryProduct";
        logger.info("请求iot 参数::"+map.toString()+"  action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String registDevice(String deviceName) {
        Map<String,Object> map=new HashMap<>();
        map.put("deviceName",deviceName);
        map.put("productKey",productKey);
        map.put("productSecret",productSecret);
        String action="RegisterDevice";
        logger.info("请求iot 参数::"+map.toString()+"   action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String batchCreateDeviceList(List<String> deviceNames) {
        Map<String,Object> map=new HashMap<>();
        map.put("productKey",productKey);
        map.put("deviceNames",deviceNames);
        String action="BatchCreateDeviceWithNames";
        logger.info("请求iot 参数::"+map.toString()+"  action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String queryDeviceListByApplyId(String applyId, int pageIndex, int pageSize) {
        Map<String,Object> map=new HashMap<>();
        map.put("productKey",productKey);
        map.put("applyId",applyId);
        map.put("pageIndex",pageIndex);
        map.put("pageSize",pageSize);
        String action="QueryDeviceListWithApplyId";
        logger.info("请求iot 参数::"+map.toString()+"  action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String queryDevice(String deviceId) {
        Map<String,Object> map=new HashMap<>();
        map.put("id",deviceId);
        String action="QueryDevice";
        logger.info("请求iot 参数::"+map.toString()+"  action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    @Override
    public String isEnableDevice(String deviceName, String type) {
        String action=null;
        if("0".equals(type)){
            action="EnableDevice";
        }else{
            action="DisableDevice";
        }
        Map<String,Object> map=new HashMap<>();
        map.put("deviceName",deviceName);
        map.put("productKey",productKey);
        logger.info("请求iot 参数::"+map.toString()+"  action::"+action);
        String httpResult=reqIOT(action,map);
        if(StringUtils.isEmpty(httpResult)){
            return reqFail();
        }
        return httpResult;
    }

    /**
     * 拼接请求参数
     * @param action
     * @param params
     * @return
     */
    public String reqIOT(String action, Map<String,Object> params){
        try{
            logger.info("请求为::"+action+"   入参为::"+params.toString());
            //公共入参部分
            params.put("version","2019-01-01");
            params.put("signatureMethod","HMAC-SHA1");
            params.put("timestamp",Long.toString(System.currentTimeMillis()));
            params.put("signatureNonce",Long.toString((long)Math.random()*10000000000L));
            params.put("accessKeyId",accessKeyId);
            params.put("action",action);
            String prepareSignature=beforeSignatureParams(params);
            logger.info("包含公共参数::"+params.toString()+"   加签前的参数处理::"+prepareSignature);
            String signature=signatureParams(accessKeySecret,prepareSignature);
            logger.info("签名::"+signature);
            if(StringUtils.isEmpty(signature)){
                logger.info("加签失败，请求IOT异常");
                return null;
            }
            params.put("signature",signature);
            String requestParamStr=getRequestParamStr(params);
            logger.info("拼接签名后的参数::"+requestParamStr);
            String url=iotUrl+"?"+requestParamStr;
            logger.info("请求地址为::"+url);
            String result=doHttpRequest(url);
            logger.info("[IOT]请求结果为::"+result);
            return result;
        }catch(Exception e){
            logger.error("请求IOT异常",e);
        }
        return null;
    }

    /**
     * 入参进行排序
     * @param params
     * @return
     */
    public String beforeSignatureParams(Map<String,Object> params){
        if(params.isEmpty()){
            return null;
        }
        List<String> signList=new ArrayList<String>();
        Iterator<Map.Entry<String, Object>> iterator=  params.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry=iterator.next();
            String paramName=entry.getKey();
            String paramValue=String.valueOf(entry.getValue());
            try{
                 String encodedParamValue= URLEncoder.encode(paramValue,"UTF-8").replace("+","%20")
                         .replace("*","%2A")
                         .replace("%7E","~")
                         .replace(":","%3A");
                signList.add(paramName.toLowerCase()+"="+encodedParamValue.toLowerCase());//拼接
                params.put(paramName,encodedParamValue);
            }catch(Exception e){
                logger.error("参数处理异常",e);
            }

        }
        Collections.sort(signList);
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<signList.size();i++){
            stringBuilder.append(signList.get(i));
            if(i !=signList.size()-1){
                stringBuilder.append("&");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 请求加签处理
     * @param accessKeySecret
     * @param prepareSignature
     * @return
     */
    public String signatureParams(String accessKeySecret,String prepareSignature){
        Mac mac=null;
        try{
            mac=Mac.getInstance("HmacSHA1");
            SecretKeySpec keySpec=new SecretKeySpec(accessKeySecret.getBytes(),"HmacSHA1");
            mac.init(keySpec);
            mac.update(prepareSignature.getBytes());
            byte[] encrypedBates=mac.doFinal();
            return Base64.encodeBase64String(encrypedBates).replace("+","%2B")
                    .replace("\r\n","");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将签名跟原来的参数骗拼接到一起
     * @param params
     * @return
     */
    public String getRequestParamStr(Map<String,Object> params){
        Iterator<Map.Entry<String, Object>> iterator=  params.entrySet().iterator();
        StringBuilder builder=new StringBuilder();
        while(iterator.hasNext()){
            Map.Entry<String, Object> entry=iterator.next();
            String paramName=entry.getKey();
            String paramValue=String.valueOf(entry.getValue());
            builder.append(paramName).append("=").append(paramValue).append("&");
        }
        return builder.toString().substring(0,builder.length()-1);
    }

    /**
     *  get方式请求IOT Hub
     * @param url
     * @return
     * @throws IOException
     */
    public String doHttpRequest(String url) throws IOException {
        PoolingHttpClientConnectionManager connectionManager=new PoolingHttpClientConnectionManager();
        HttpClientBuilder clientBuilder= HttpClients.custom();
        clientBuilder.setConnectionManager(connectionManager);
        CloseableHttpClient client=clientBuilder.build();
        HttpGet get=new HttpGet(url);
        RequestConfig.Builder configBuilder=RequestConfig.custom();
        configBuilder.setConnectTimeout(3000);
        configBuilder.setSocketTimeout(3000);
        configBuilder.setCookieSpec(CookieSpecs.IGNORE_COOKIES);
        RequestConfig config=configBuilder.build();
        get.setConfig(config);
        CloseableHttpResponse httpResponse=null;
        httpResponse=client.execute(get);
        HttpEntity httpEntity=httpResponse.getEntity();
        BufferedReader br=new BufferedReader(new InputStreamReader(httpEntity.getContent(),"UTF-8"));
        StringBuffer result=new StringBuffer();
        int len=0;
        while((len=br.read())!=-1){
            result.append((char)len);
        }
        httpResponse.close();
        return result.toString();
    }


}
