package com.pingan.angel.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * Quartz-Cluster微服务启动类
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ImportResource("quartz.xml")
public class SimpleQuartzClusterApplication {

    private static final Logger Logger = LoggerFactory.getLogger(SimpleQuartzClusterApplication.class);

    public static void main(String[] args) {
        Logger.info("简单Quartz-Cluster微服务入口函数编码-" + System.getProperty("file.encoding"));

        SpringApplication.run(SimpleQuartzClusterApplication.class, args);

        System.out.println("【【【【【【 简单Quartz-Cluster微服务 】】】】】】已启动.");
    }
}
