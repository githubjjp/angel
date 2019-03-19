package com.pingan.stream.Service.rabbitMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;

/**
 *  rabbitMQ  消息生产者
 * @author chenhao
 */
@EnableBinding(RabbitSource.class)
public class RabbitSender {
    private static Logger logger = LoggerFactory.getLogger(RabbitSender.class);
    @Resource
    private RabbitSource rabbitSource;

    public void sendMsg(String message) {
        logger.info("【rabbit-生产者】-发送消息::" + message);
        //output.send(MessageBuilder.withPayload(object).build());
        rabbitSource.output().send(MessageBuilder.withPayload(message).build());
    }

}
