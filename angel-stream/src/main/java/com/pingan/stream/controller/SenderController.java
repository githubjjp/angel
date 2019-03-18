package com.pingan.stream.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.kafka.KafkaSender;
import com.pingan.stream.Service.rabbitMQ.RabbitSender;
import com.pingan.stream.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/msg")
public class SenderController {
    private Logger logger= LoggerFactory.getLogger(SenderController.class);
    @Autowired
    private RabbitSender rabbitSender;
    @Autowired
    private KafkaSender kafkaSender;

    /**
     * 调用rabbitMQ生产者
     * @param message
     * @return
     */
    @GetMapping("/rabbit/sendMsg")
    public String sendMsg(@RequestParam("message") String message){
        Map<String,Object> resultMap=new HashMap<String,Object>();
        if(message==null || "".equals(message)){
            resultMap.put("code", Content.REQ_ERROR);
            resultMap.put("msg",Content.PARAM_NULL);
            return JSON.toJSONString(resultMap);
        }
        try{
            this.rabbitSender.sendMsg(message);
            resultMap.put("code", Content.REQ_OK);
            resultMap.put("msg",Content.REQ_OK_CONTENT);
            resultMap.put("data","处理的推送消息为--{"+message+"}");
        }catch(Exception e){
            resultMap.put("code", Content.REQ_ERROR);
            resultMap.put("msg",Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(resultMap);
    }

    /**
     * 调用kafka生产者
     * @param message
     * @param type
     * @return
     */
    @GetMapping("/kafka/sendMsg")
    public String sendMsg2(@RequestParam("message") String message){
        logger.info("------------SenderController-------param:{"+message+"}");
        Map<String,Object> resultMap=new HashMap<String,Object>();
        if(message==null || "".equals(message)){
            resultMap.put("code", Content.REQ_ERROR);
            resultMap.put("msg",Content.PARAM_NULL);
            return JSON.toJSONString(resultMap);
        }
        try{
            this.kafkaSender.sendMsg(message);
            resultMap.put("code", Content.REQ_OK);
            resultMap.put("msg",Content.REQ_OK_CONTENT);
            resultMap.put("data","处理的推送消息为--{"+message+"}");
        }catch(Exception e){
            resultMap.put("code", Content.REQ_ERROR);
            resultMap.put("msg",Content.REQ_ERROR_CONTENT);
        }
        return JSON.toJSONString(resultMap);
    }
}
