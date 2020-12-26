package com.veryitman.user.service;

import com.veryitman.user.model.MSUser;

import java.util.List;
import java.util.Map;

public interface MSUserDBService {

    void createUserTable();

    int addUser(MSUser user);

    int deleteByUid(Integer uid);

    List<Map> queryUserByUid(Integer uid);

    List<Map> queryUserByUserName(String userName);

    int deleteUserTable();
}
