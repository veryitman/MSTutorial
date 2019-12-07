package com.veryitman.springboot.util;

import com.veryitman.springboot.model.MSUser;

public final class MSUserUtil {

    public static MSUser createUser(String userName, String userPwd) {
        MSUser user = new MSUser();
        if (null == userName) {
            userName = "veryitman";
        }
        user.setAccountName(userName);
        user.setNickName(userName);
        if (null == userPwd) {
            userPwd = "123";
        }
        user.setAccountPwd(userPwd);
        user.setGender(1);
        user.setAge(20);
        user.setUserID(1);
        user.setMotto("");

        return user;
    }
}
