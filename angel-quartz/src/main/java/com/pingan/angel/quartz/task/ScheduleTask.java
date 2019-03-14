package com.pingan.angel.quartz.task;

import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * 调度任务
 * testScheduleTask 字符串名称在 quartz.xml 中配置为属性 targetObject 的 value 值。
 * sayHello 方法名称在 quartz.xml 中配置为属性 targetMethod 的 value 值。
 */
@Configuration
@Component("testScheduleTask")
@EnableScheduling
public class ScheduleTask {

    private static final Logger Logger = LoggerFactory.getLogger(ScheduleTask.class);

    public void sayHello(JobExecutionContext context){
        Logger.info("====    sayHello 123456789    ====");
        System.out.println("====    sayHello 123456789    ====");
    }
}
