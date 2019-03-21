package com.pingan.stream.Service.impl;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.DeviceOperateService;
import com.pingan.stream.Service.MessageServer;
import com.pingan.stream.common.Content;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 具体处理监听消息业务
 */
@Service
public class MessageServerImpl implements MessageServer {
    private static Logger logger= LoggerFactory.getLogger(MessageServerImpl.class);
    @Autowired
    private DeviceOperateService deviceOperateService;

    @Override
    public void receiveMsg(String data,String mqType,String topicType) {
        logger.info("业务处理前--数据::"+ JSON.toJSONString(data));
        if(Content.MQ_TYPE_1.equals(mqType)){ //处理rabbitMQ监听到的消息
            receiveRabbit(data);
        }else if(Content.MQ_TYPE_2.equals(mqType)){ //暂定处理topic为kafka-Topic-1，kafka-Topic-2监听到的消息
            receiveKafka(data,topicType);
        }
    }

    /**
     * rabbitmq消息-业务处理
     * @param data
     */
    public void receiveRabbit(String data){

    }

    /**
     * 根据监听主题交由不同业务组建处理
     * @param data
     * @param topicType
     */
    public void receiveKafka(String data,String topicType){
        if(topicType.equals(Content.TOPIC_TYPE_1)){  //设备指令监听处理
            deviceOperateService.receiveDeviceOrder(data);
        }
    }


}
