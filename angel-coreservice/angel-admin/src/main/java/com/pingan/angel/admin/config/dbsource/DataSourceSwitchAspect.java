//package com.pingan.angel.admin.config.dbsource;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Aspect
//@Order(-100) //这是为了保证AOP在事务注解之前生效,Order的值越小,优先级越高
//@Slf4j
//public class DataSourceSwitchAspect {
//
//    @Pointcut("execution(* com.pingan.angel.admin.service.mysql.service..*.*(..))")
//    private void mysqlAspect() {
//    }
//
//    @Pointcut("execution(* com.pingan.angel.admin.service.mongodb.service..*.*(..))")
//    private void mongodbAspect() {
//    }
//
//    @Before( "mysqlAspect()" )
//    public void mysql() {
//        log.info("切换到mysql 数据源...");
//        System.out.println("切换到mysql 数据源...");
//        DbContextHolder.setDbType(DBTypeEnum.mysql);
//    }
//
//    @Before("mongodbAspect()" )
//    public void mongodb() {
//        log.info("切换到mongodb 数据源...");
//        System.out.println("切换到mongodb 数据源...");
//        DbContextHolder.setDbType(DBTypeEnum.mongodb);
//    }
//}
