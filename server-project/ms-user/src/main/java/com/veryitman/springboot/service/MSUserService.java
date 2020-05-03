package com.veryitman.springboot.service;

import com.veryitman.springboot.model.MSUser;

import java.util.List;
import java.util.Map;

public interface MSUserService {

    void createUserTable();

    int addUser(MSUser user);

    int deleteByUid(Integer uid);

    List<Map> queryUserByUid(Integer uid);

    List<Map> queryUserByUserName(String userName);

    int deleteUserTable();
}
