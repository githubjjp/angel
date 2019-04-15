package com.pingan.stream.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.angel.admin.api.entity.ApiResult;
import com.pingan.angel.admin.api.entity.ResultCode;
import com.pingan.stream.Service.BusinessService;
import com.pingan.stream.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceOperateController {
    @Autowired
    private BusinessService businessService;

    /**
     * 获取连接字符串
     * @param deviceId
     * @return
     */
    @RequestMapping("/getConnectionStr/{deviceId}")
    public String getConnectionStr(@PathVariable String deviceId){
        Map<String,Object> retMap=null;
        try{
            if(StringUtils.isEmpty(deviceId)){
                retMap=ApiResult.error(ResultCode.REQUEST_PARAM_LOSET.getMsg());
            }else{
                Map<String,Object> dataMap=businessService.getConnectionStr(deviceId);
                retMap= ApiResult.success(dataMap);
            }
        }catch(Exception e){
            retMap=ApiResult.error("请求服务器异常");
        }
        return JSON.toJSONString(retMap);
    }

    /**
     *  获取设备在线状态
     * @param deviceId
     * @return
     */
    @RequestMapping("/isOnline/{deviceId}")
    public String isOnline(@PathVariable String deviceId){
        Map<String,Object> retMap=null;
        try{
            if(StringUtils.isEmpty(deviceId)){
                retMap=ApiResult.error(ResultCode.REQUEST_PARAM_LOSET.getMsg());
            }else{
                Map<String,Object> dataMap=businessService.isOnline(deviceId);
                retMap= ApiResult.success(dataMap);
            }
        }catch(Exception e){
            retMap=ApiResult.error("请求服务器异常");
        }
        return JSON.toJSONString(retMap);
    }











}
