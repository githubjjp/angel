package com.pingan.stream.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.BusinessService;
import com.pingan.stream.common.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceOperateController {
    @Autowired
    private BusinessService businessService;
    @RequestMapping("/getConnectionStr/{deviceId}")
    public String getConnectionStr(@PathVariable String deviceId){
        Map<String,Object> ret=new HashMap<>();
        try{
            Map<String,Object> param=businessService.getConnectionStr(deviceId);
            ret.put("code", Content.REQ_OK);
            ret.put("msg", Content.REQ_OK_CONTENT);
            ret.put("data", param);
        }catch(Exception e){
            ret.put("code", Content.REQ_ERROR);
            ret.put("msg", Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(ret);
    }
    @RequestMapping("/isOnline /{deviceId}")
    public String isOnline(@PathVariable String deviceId){
        Map<String,Object> ret=new HashMap<>();
        try{
            Map<String,Object> param=businessService.isOnline(deviceId);
            ret.put("code", Content.REQ_OK);
            ret.put("msg", Content.REQ_OK_CONTENT);
            ret.put("data", param);
        }catch(Exception e){
            ret.put("code", Content.REQ_ERROR);
            ret.put("msg", Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(ret);
    }



}
