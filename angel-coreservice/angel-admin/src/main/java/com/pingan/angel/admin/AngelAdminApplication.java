package com.pingan.angel.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lengleng
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@SpringCloudApplication
@EnableFeignClients(basePackages = {"com.pingan.angel"})
@ComponentScan("com.pingan.angel")
public class AngelAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AngelAdminApplication.class, args);
	}

}
