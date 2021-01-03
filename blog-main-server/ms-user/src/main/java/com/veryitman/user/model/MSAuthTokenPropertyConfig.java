package com.veryitman.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "msconfig.authtoken")
public class MSAuthTokenPropertyConfig {

    private String claims_jwtsid;
    private String claims_subject;
    private String claims_audience;
    private String token_expire_time;//token 过期时间24小时
    private String token_secret;//密钥盐
}
