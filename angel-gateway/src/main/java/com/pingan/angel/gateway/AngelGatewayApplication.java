package com.pingan.angel.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@ComponentScan(basePackages="com.pingan.angel")
public class AngelGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(AngelGatewayApplication.class, args);
	}
}
