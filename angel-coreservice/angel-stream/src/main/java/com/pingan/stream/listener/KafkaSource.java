package com.pingan.stream.listener;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface KafkaSource {
    //输入通道
    public static final String OUTPUT="kafka_output";

    public static final String OUTPUT2="kafka_output2";

    @Output(KafkaSource.OUTPUT)
    MessageChannel output();

    @Output(KafkaSource.OUTPUT2)
    MessageChannel output2();

}
