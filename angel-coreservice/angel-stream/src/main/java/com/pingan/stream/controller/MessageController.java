package com.pingan.stream.controller;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.common.Content;
import com.pingan.stream.listener.KafkaSender;
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
public class MessageController {
    private Logger logger= LoggerFactory.getLogger(MessageController.class);
    @Autowired
    private KafkaSender kafkaSender;

    /**
     * 调用kafka生产者
     * @param message
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
