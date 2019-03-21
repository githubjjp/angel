package com.pingan.stream.listener;

import com.pingan.stream.Service.MessageServer;
import com.pingan.stream.Service.rabbitMQ.RabbitSink;
import com.pingan.stream.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 *  rabbitMQ消息监听者
 * @author chenhao
 */
@Component
@EnableBinding(RabbitSink.class)
public class RabbitListener {
    private Logger logger= LoggerFactory.getLogger(RabbitListener.class);
    @Autowired
    private MessageServer messageServer;

    @StreamListener(RabbitSink.INPUT)
    public void msgToSender(String payload) {
        logger.info("【rabbit-消费者】监听消息::" + payload);
        messageServer.receiveMsg(payload, Content.MQ_TYPE_1,null);
    }
}
