package com.veryitman.springboot;

import com.veryitman.springboot.model.MSUser;
import com.veryitman.springboot.service.MSUserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * Create db： create database mzc_user default character set utf8mb4 collate utf8mb4_unicode_ci;
 * <p>
 * delete db：drop database mzc_user;
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class MSDBTests {
    @Autowired
    MSUserService msUserService;

    private Logger logger = LoggerFactory.getLogger(SpringbootApplicationTests.class);

    @Before
    public void setup() {
        logger.info("MSBlog Test, Before");
    }

    @Test
    public void deleteUserById() {
        try {
            msUserService.deleteByUid(1);
        } catch (Exception e) {
            logger.error("MSBlog Test, Delete data happends errors:" + e.toString());
        }
    }

    @Test
    public void dropTable() {
        msUserService.deleteUserTable();
    }

    @Test
    public void createUserSQLTest() {
        // 增加user数据
        msUserService.createUserTable();
    }

    @Test
    public void addUser() {
        MSUser user = new MSUser();
        user.setUserID(1);
        user.setAccountName("mzc");
        user.setAccountPwd("123");
        user.setNickName("veryitman");
        user.setMotto("foo");
        user.setAge(25);
        user.setGender(MSUser.GENDER_MALE);
        user.setPhone("17122036530");
        msUserService.addUser(user);
    }

    @Test
    public void queryUserBySQLTest() {
        // 根据user_id查询
        List<Map> userJson = msUserService.queryUserByUid(1);
        logger.info("MSBlog Test, userJson by query user's id: " + userJson.get(0));

        // 根据user_name查询
        userJson = msUserService.queryUserByUserName("mzc");
        logger.info("MSBlog Test, userJson by query user's name: " + userJson.get(0));
    }

    @After
    public void testDBOVer() {
        logger.info("MSBlog Test, After");
    }
}
