package com.pingan.angel.auth;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

import com.pingan.angel.security.annotation.EnableAngelFeignClients;

/**
 * @author lengleng
 * @date 2018年06月21日
 * 认证授权中心
 */
@SpringCloudApplication
@EnableAngelFeignClients(basePackages = {"com.pingan.angel"})
@ComponentScan("com.pingan.angel")
public class AngelAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelAuthApplication.class, args);
	}
}
