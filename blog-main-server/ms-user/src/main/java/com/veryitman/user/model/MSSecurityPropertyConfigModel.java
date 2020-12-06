package com.veryitman.user.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Setter
@Getter
@ConfigurationProperties(prefix = "msconfig.security")
public class MSSecurityPropertyConfigModel {
    private boolean enableCSRF;

    private String defToken;

    private List testList;

    private Map testMap;

    // 和 ConfigurationProperties 一起使用
    @Value("msconfig.security.alias")
    private String aliasPlayGame;
}
