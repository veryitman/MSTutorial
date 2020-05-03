package com.veryitman.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan
//@WebAppConfiguration
// 去掉数据库依赖 @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
public class MZUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MZUserApplication.class, args);
    }
}
