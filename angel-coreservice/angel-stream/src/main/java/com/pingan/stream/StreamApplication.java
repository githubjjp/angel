package com.pingan.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *  消息总线服务启动类
 * @author  chenhao
 * @date 2019-03-14
 */

@SpringBootApplication
@EnableEurekaClient
@EnableCircuitBreaker
@EnableDiscoveryClient
public class StreamApplication {
    public static void main(String[] args){
        SpringApplication.run(StreamApplication.class,args);
    }

}
