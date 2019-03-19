package com.pingan.angel.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.pingan.angel.quartz.dao")
//@ComponentScan("com.pingan.angel.quartz")
public class SpringbootQuartzApplication {
	public static void main(String[] args) {
		System.out.print("请输入端口号：");
		SpringApplication.run(SpringbootQuartzApplication.class, args);
	}


}
