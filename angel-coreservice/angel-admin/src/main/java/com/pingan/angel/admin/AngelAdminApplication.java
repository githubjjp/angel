package com.pingan.angel.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;

import com.pingan.angel.security.annotation.EnableAngelFeignClients;

/**
 * @author lengleng
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@SpringCloudApplication
@EnableAngelFeignClients(basePackages = {"com.pingan.angel"})
@ComponentScan("com.pingan.angel")
@MapperScan({"com.pingan.angel.admin.mapper*"})
public class AngelAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AngelAdminApplication.class, args);
	}

}
