package com.pingan.angel.quartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Scanner;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.pingan.angel.quartz.dao")
//@ComponentScan("com.pingan.angel.quartz")
public class SpringbootQuartzApplication {

	public static void main(String[] args) {
		System.out.print("请输入端口号：");
		Scanner scanner = new Scanner(System.in);
		String port = scanner.nextLine();
		new SpringApplicationBuilder(SpringbootQuartzApplication.class)
				.properties("server.port=" + port).run(args);
	}


}
