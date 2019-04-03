package com.pingan.stream.fmq;

import com.paic.fincloud.client.FCFactory;
import com.paic.fincloud.client.consumer.Consumer;
import com.paic.fincloud.client.dto.FCMessageFilter;
import com.paic.fincloud.client.util.FCConstant;
import com.paic.mqcp.common.consumer.ConsumeFromWhere;
import com.pingan.stream.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;
import java.util.UUID;

/**
 * 配置消费者
 */
@Configuration
@PropertySource("classpath:fmq.properties")
public class FmqConsumerConfig {
    private static Logger logger= LoggerFactory.getLogger(FmqConsumerConfig.class);
    @Value("${fmq.consumerId}")
    private String consumerId;
    @Value("${fmq.serverHost}")
    private String serverHost;
    @Value("${fmq.accessKey}")
    private String accessKey;
    @Value("${fmq.secretKey}")
    private String secretKey;
    @Value("${fmq.topic}")
    private String topic;
    @Autowired
    private MsgConsumer msgConsumer;
    @Bean
    public Consumer initConsumer(){
        if(StringUtils.isEmpty(consumerId) ){
            logger.info("【consumer】消费者创建失败,consumerId is null");
            return null;
        }
        if(StringUtils.isEmpty(serverHost) ){
            logger.info("【consumer】消费者创建失败,serverHost is null");
            return null;
        }
        if(StringUtils.isEmpty(accessKey) ){
            logger.info("【consumer】消费者创建失败,accessKey is null");
            return null;
        }
        if(StringUtils.isEmpty(secretKey) ){
            logger.info("【producer】消费者创建失败,secretKey is null");
            return null;
        }

        Properties properties=new Properties();
        properties.setProperty(FCConstant.CONSUMER_ID,consumerId);
        properties.setProperty(FCConstant.NAME_SERVER_ADDRESS,serverHost);
        properties.setProperty(FCConstant.ACCESS_KEY,accessKey);
        properties.setProperty(FCConstant.SECRET_KEY,secretKey);
        properties.setProperty(FCConstant.INSTANCE_NAME, serverHost+"@"+UUID.randomUUID().toString());//主机地址@随机字符串
        properties.put("CONSUMER_THREAD_NUM","10");
        Consumer consumer=null;
        try{
            FCMessageFilter fcf=new FCMessageFilter();//消息过滤器
            consumer= FCFactory.createConsumer(properties);
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);//从上次最后消费的位置开始
            consumer.subscribe(topic,fcf,msgConsumer);
            consumer.start();  //开启消费者
            logger.info("【consumer】消费者启动成功！");
        }catch(Exception e){
            logger.error("【consumer】消费者启动失败",e);
            consumer.shutdown(); //关闭消费者
        }
        return consumer;
    }
}
