package com.veryitman.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MSActuatorSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.authorizeRequests()
        //        .requestMatchers(EndpointRequest.toAnyEndpoint())
        //        .hasRole("ACTUATOR_ADMIN")
        //        .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
        //        .permitAll()
        //        .antMatchers("/")
        //        .permitAll()
        //        .antMatchers("/**")
        //        .authenticated()
        //        .and()
        //        .httpBasic();

        //disable CRSF
        http
                //no authentication needed for these context paths
                .authorizeRequests()
                .antMatchers("/error").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/your Urls that dosen't need security/**").permitAll();
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/error/**");
    }
}
