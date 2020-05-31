package com.veryitman.admin.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// THS：https://github.com/anandvarkeyphilips/spring-boot-admin/blob/master/spring-boot-admin-samples/spring-boot-admin-server-with-security/src/main/java/de/codecentric/boot/admin/server/SpringBootAdminServerApplication.java

@Configuration
public class MZSecurityConfig extends WebSecurityConfigurerAdapter {

    private final String adminContextPath;

    public MZSecurityConfig(AdminServerProperties adminServerProperties) {
        this.adminContextPath = adminServerProperties.getContextPath();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //// @formatter:off
        //SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        //successHandler.setTargetUrlParameter("redirectTo");
        //successHandler.setDefaultTargetUrl(adminContextPath + "/");
        //
        //http.authorizeRequests()
        //        .antMatchers(adminContextPath + "/assets/**").permitAll()
        //        .antMatchers(adminContextPath + "/login").permitAll()
        //        .anyRequest().authenticated()
        //        .and()
        //        .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
        //        .logout().logoutUrl(adminContextPath + "/logout").and()
        //        .httpBasic().and()
        //        .csrf()
        //        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
        //        .ignoringAntMatchers(
        //                adminContextPath + "/instances",
        //                adminContextPath + "/actuator/**"
        //        );

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
