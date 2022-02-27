package com.veryitman.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class MSSwaggerConfig {

    @Value("${msconfig.swagger.enable}")
    private boolean enableSwagger;

    @Bean
    public Docket msblogDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2).enable(enableSwagger);

        return docket.apiInfo(msblogAPIInfo())
                .select()
                // 只扫描指定包名下面的Controller中的Swagger注解
                .apis(RequestHandlerSelectors.basePackage("com.veryitman.springboot.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo msblogAPIInfo() {
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        String apiTitle = "MSBlog Server API";
        String apiDes = "API for MSBlog";
        String apiVersion = "1.0.0";
        // 联系方式
        String homePage = "http://veryitman.com";
        String email = "veryitman@126.com";
        Contact contact = new Contact("itman", homePage, email);
        return apiInfoBuilder.title(apiTitle).description(apiDes)
                .version(apiVersion)
                .contact(contact)
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
