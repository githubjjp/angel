package com.pingan.stream.Service;

public interface MessageServer {
    /**
     *  接收消息业务处理
     * @param data
     * @param mqType rabbit/kafka-1/kafka-2
     * @return
     */
     void receiveMsg(String data, String mqType);
}
