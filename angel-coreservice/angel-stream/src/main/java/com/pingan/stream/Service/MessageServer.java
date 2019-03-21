package com.pingan.stream.Service;

public interface MessageServer {
    /**
     *  接收消息业务处理
     * @param data
     * @param mqType mq类型  rabbit/kafka-1/kafka-2
     * @param  topicType kafka通道主题
     * @return
     */
    void receiveMsg(String data, String mqType,String topicType);
}
