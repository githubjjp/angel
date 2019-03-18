package com.pingan.stream.Service.kafka;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

@EnableBinding(KafkaSource.class)
public class KafkaSender {
    private static Logger logger = LoggerFactory.getLogger(KafkaSender.class);
    @Resource
    private KafkaSource KafkaSource;

    /**
     * 通道1
     * @param message
     */
    public void sendMsg(String message) {
        try{
            logger.info("【kafka-生产者-1】-发送消息::" + JSON.toJSONString(message));
            KafkaSource.output().send(MessageBuilder.withPayload(message).build());
        }catch(Exception e){
            logger.error("【kafka-生产者-1】-消息发送失败",e);
        }
    }

     /**
     * 通道2
     * @param message
     */
    public void sendMsg2(String message) {
        try{
            logger.info("【kafka-生产者-2】-发送消息::" + JSON.toJSONString(message));
            KafkaSource.output2().send(MessageBuilder.withPayload(message).build());
        }catch(Exception e){
            logger.error("【kafka-生产者-2】-消息发送失败",e);
        }
    }



}
