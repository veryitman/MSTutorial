package com.veryitman.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class MSActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${msconfig.security.csrf.enable}")
    private boolean enableCRSF;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (enableCRSF) {
            http.authorizeRequests()
                    .requestMatchers(EndpointRequest.toAnyEndpoint())
                    .hasRole("ACTUATOR_ADMIN")
                    .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll()
                    .antMatchers("/")
                    .permitAll()
                    /** 对应的API不需要认证 */
                    .antMatchers("/signin/name/**").permitAll()
                    .antMatchers("/signup/name/**").permitAll()
                    .antMatchers("/**")
                    .authenticated()
                    .and()
                    .httpBasic();
        } else {
            http.csrf().disable();
            /** disable CRSF */
            //http.authorizeRequests() //no authentication needed for these context paths
            //.antMatchers("/error").permitAll()
            //.antMatchers("/error/**").permitAll()
            //.antMatchers("/signup/name/**").permitAll()
            //.antMatchers("/signin/name/**").permitAll();
        }
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers("/error/**");
    }
}
