package com.pingan.stream.Service.kafka;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaSource {
    //输入通道
    public static final String OUTPUT="kafka_output1";

    @Output(KafkaSource.OUTPUT)
    MessageChannel output();

}
