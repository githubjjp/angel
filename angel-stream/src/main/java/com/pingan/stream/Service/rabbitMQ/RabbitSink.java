package com.pingan.stream.Service.rabbitMQ;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RabbitSink {
    public static final String INPUT="rabbit_input";//输出通道

    @Input(INPUT)
    public SubscribableChannel input();
}
