/**
 * @Description：拦截器配置管理
 * @author：Mark
 * @CreateDate：2020.12.29
 * @update：[1][2020.12.29][Mark][New]
 */
package com.veryitman.user.config;

import com.veryitman.user.interceptor.MSAuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MSAuthConfigurer implements WebMvcConfigurer {

    private MSAuthInterceptor authInterceptor;

    public MSAuthConfigurer(MSAuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 如下路径不做拦截
        List<String> excludePaths = new ArrayList<>();
        excludePaths.add("/signup/**"); //注册
        excludePaths.add("/signin/name/**"); //用户名登录
        excludePaths.add("/signin/get/token/**"); //获取token
        excludePaths.add("/signout/**"); //登出
        excludePaths.add("/static/**");  //静态资源
        excludePaths.add("/assets/**");  //静态资源

        // 除了 excludePaths 外的请求地址都做拦截
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePaths);

        WebMvcConfigurer.super.addInterceptors(registry);
    }

    /**
     * 如果不在 MSAuthInterceptor 类上面加入Component注解，这里可以使用bean注解
    @Bean
    public MSAuthInterceptor authenticationInterceptor() {
        return new MSAuthInterceptor();
    }
    */
}
