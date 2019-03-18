package com.pingan.stream.listener;

import com.pingan.stream.Service.MessageServer;
import com.pingan.stream.Service.kafka.KafkaSink;
import com.pingan.stream.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 *  kafka消息监听者
 * @author  chenhao
 */
@Component
@EnableBinding(KafkaSink.class)
public class KafkaListener {
    private Logger logger= LoggerFactory.getLogger(KafkaListener.class);
    @Autowired
    private MessageServer messageServer;

    @StreamListener(value=KafkaSink.INPUT)
    public void receive(String payload){
        logger.info("【kafka-消费者-1】 监听消息::"+payload);
        messageServer.receiveMsg(payload, Content.MQ_TYPE_2);
    }

    @StreamListener(value=KafkaSink.INPUT2)
    public void receive2(String payload){
        logger.info("【kafka-消费者-2】 监听消息::"+payload);
        messageServer.receiveMsg(payload,Content.MQ_TYPE_2);
    }


}
