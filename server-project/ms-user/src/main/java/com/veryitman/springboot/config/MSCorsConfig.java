package com.veryitman.springboot.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class MSCorsConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
        registry.addMapping("/signin/name") //指定API
                //.addMapping("/**") //所有API
                //allowedOrigins("*") //允许所有的请求地址
                .allowedOrigins("http://localhost:8082", "http://localhost:8083", "http://localhost:63344")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }
}
