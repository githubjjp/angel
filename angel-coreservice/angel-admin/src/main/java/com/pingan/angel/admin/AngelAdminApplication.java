package com.pingan.angel.admin;


import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

import com.pingan.angel.security.annotation.EnableAngelFeignClients;

/**
 * @author lengleng
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@SpringCloudApplication
@EnableAngelFeignClients
public class AngelAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(AngelAdminApplication.class, args);
	}

}
