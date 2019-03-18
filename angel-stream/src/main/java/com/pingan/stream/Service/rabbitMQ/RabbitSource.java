package com.pingan.stream.Service.rabbitMQ;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RabbitSource {
    public static final String OUTPUT="rabbit_output"; //输入通道

    @Output(OUTPUT)
    public MessageChannel output();

}
