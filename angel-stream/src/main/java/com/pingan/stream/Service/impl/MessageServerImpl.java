package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.MessageServer;
import com.pingan.stream.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
@Service
public class MessageServerImpl implements MessageServer {
    private static Logger logger= LoggerFactory.getLogger(MessageServerImpl.class);
    @Override
    public void receiveMsg(String data,String mqType) {
        logger.info("业务处理前--数据::"+ JSON.toJSONString(data));
        if(Content.MQ_TYPE_1.equals(mqType)){ //处理rabbitMQ监听到的消息
            receiveRabbit(data);
        }else if(Content.MQ_TYPE_2.equals(mqType)){ //暂定处理topic为kafka-Topic-1，kafka-Topic-2监听到的消息
            receiveKafka(data);
        }
    }

    /**
     * rabbitmq消息-业务处理
     * @param data
     */
    public void receiveRabbit(String data){

    }

    /**
     *
     * @param data
     */
    public void receiveKafka(String data){

    }
}
