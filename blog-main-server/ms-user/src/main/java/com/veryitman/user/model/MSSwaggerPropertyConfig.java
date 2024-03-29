package com.veryitman.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "msconfig.swagger")
public class MSSwaggerPropertyConfig {

    private boolean enableSwagger;

}
