package com.pingan.stream.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.DeviceOperateService;
import com.pingan.stream.common.Content;
import com.pingan.stream.entity.DeviceReportOrderEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/cmd")
public class DeviceOperateController {
    @Autowired
    private DeviceOperateService deviceOperateService;

    /**
     *  带分页查询指令集合
     * @param request
     * @return
     */
    @RequestMapping("/findAll")
    public String findAll(HttpServletRequest request,HttpServletResponse response){
    	
        String pageNum=request.getParameter("pageNum");//页数
        String pageSize=request.getParameter("pageSize");//记录数
        String cmd=request.getParameter("cmd");//命令关键字
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("pageNum",pageNum);
        param.put("pageSize",pageSize);
        param.put("cmd",cmd);

        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            List<DeviceReportOrderEntity> list=deviceOperateService.findAll(param);
            retMap.put("code", Content.REQ_OK);
            retMap.put("msg", Content.REQ_OK_CONTENT);
            retMap.put("data",list==null?new ArrayList<DeviceReportOrderEntity>():list);
        }catch(Exception e){
            retMap.put("code", Content.REQ_ERROR);
            retMap.put("msg", Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(retMap);
    }

    /**
     * 查询指令详情
     * @param id
     * @return
     */
    @RequestMapping("/findById/{id}")
    public String findById(@PathVariable String id){
        Map<String,Object> retMap=new HashMap<String,Object>();
        try{
            DeviceReportOrderEntity order=deviceOperateService.findById(id);
            retMap.put("code", Content.REQ_OK);
            retMap.put("msg", Content.REQ_OK_CONTENT);
            retMap.put("data",order==null?new DeviceReportOrderEntity():order);
        }catch(Exception e){
            retMap.put("code", Content.REQ_ERROR);
            retMap.put("msg", Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(retMap);
    }


}
