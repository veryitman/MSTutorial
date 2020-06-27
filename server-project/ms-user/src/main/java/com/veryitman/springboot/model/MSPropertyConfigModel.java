package com.veryitman.springboot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "msconfig")
public class MSPropertyConfigModel {

    private boolean enableSwagger;

    private boolean enableSecurityCSRF;

}
