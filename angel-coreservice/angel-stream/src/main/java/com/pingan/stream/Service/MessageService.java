package com.pingan.stream.Service;

/**
 *  只负责消息接受不做具体业务处理
 */
public interface MessageService {
    /**
     * 接收MQ消息处理
     * @param mqStr
     */
    public void receive(String mqStr);
}
