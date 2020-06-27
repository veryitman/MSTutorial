package com.veryitman.springboot;

import com.veryitman.springboot.model.MSPropertyConfigModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MSPropertyConfigTests {

    @Autowired
    MSPropertyConfigModel propertyConfigModel;

    private Logger logger = LoggerFactory.getLogger(MSPropertyConfigTests.class);

    @Test
    public void primitiveDataConfig() {

        boolean enableSwagger = propertyConfigModel.isEnableSwagger();

        boolean enableCSRF = propertyConfigModel.isEnableSecurityCSRF();

        logger.debug("primitiveDataConfig-enableSwagger: " + enableSwagger + ", enableCSRF: " + enableCSRF);

        System.out.println("primitiveDataConfig-enableSwagger: " + enableSwagger + ", enableCSRF: " + enableCSRF);
    }
}
