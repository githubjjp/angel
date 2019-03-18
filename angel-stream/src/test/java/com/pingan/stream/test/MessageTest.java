package com.pingan.stream.test;

import com.alibaba.fastjson.JSON;
import com.pingan.stream.Service.kafka.KafkaSender;
import com.pingan.stream.Service.rabbitMQ.RabbitSender;
import com.pingan.stream.StreamApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;


/**
 *  消息生产者测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = StreamApplication.class)
@WebAppConfiguration
public class MessageTest {
    @Autowired
    private KafkaSender kafkaSender;
    @Autowired
    private RabbitSender rabbitSender;
    @Test
    public void execute(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("name","chenhao");
        map.put("age",30);
        map.put("place","深圳高新园");
        map.put("boss",false);
        System.out.println("测试类::map ["+map.toString());
        map.put("type","rabbitMQ");
        rabbitSender.sendMsg(JSON.toJSONString(map));
        System.out.println("-----kafka1----");
        map.put("type","kafka1");
        kafkaSender.sendMsg(JSON.toJSONString(map));
        System.out.println("------kafka2------");
        map.put("type","kafka2");
        kafkaSender.sendMsg2(JSON.toJSONString(map));

    }
}
