package com.veryitman.springboot;

import com.veryitman.springboot.model.MSSecurityPropertyConfigModel;
import com.veryitman.springboot.model.MSSwaggerPropertyConfigModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MSPropertyConfigTests {

    @Autowired
    MSSwaggerPropertyConfigModel swaggerPropertyConfigModel;

    @Autowired
    MSSecurityPropertyConfigModel securityPropertyConfigModel;

    private Logger logger = LoggerFactory.getLogger(MSPropertyConfigTests.class);

    @Value("${msconfig.security.enableCSRF}")
    private boolean geEnableCSRF;

    @Value("${msconfig.security.defToken}")
    private String defUserToken;

    @Value("#{'${msconfig.security.testList1}'.split(',')}")
    private List list1;

    @Value("#{${msconfig.security.testMap1}}")
    private Map map1;


    @Test
    public void primitiveDataConfig() {
        {
            boolean enableSwagger = swaggerPropertyConfigModel.isEnableSwagger();
            boolean enableCSRF = securityPropertyConfigModel.isEnableCSRF();

            logger.info("primitiveDataConfig-enableSwagger: " + enableSwagger + ", enableCSRF: " + enableCSRF);

            String defToken = securityPropertyConfigModel.getDefToken();
            logger.info("securityPropertyConfigModel deftoken: " + defToken);

            List list = securityPropertyConfigModel.getTestList();
            for (Object v : list) {
                logger.info("securityPropertyConfigModel value: " + v);
            }

            Map map = securityPropertyConfigModel.getTestMap();
            logger.info("securityPropertyConfigModel map: " + map);

            logger.info("securityPropertyConfigModel alias: " + securityPropertyConfigModel.getAliasPlayGame());
        }

        logger.info("-----------------------------------------------");

        {
            logger.info("annotation value. geEnableCSRF: " + geEnableCSRF);
            logger.info("annotation value. defUserToken: " + defUserToken);

            for (Object v : list1) {
                logger.info("annotation value. list: " + v);
            }

            logger.info("annotation value. map: " + map1);
        }
    }
}
