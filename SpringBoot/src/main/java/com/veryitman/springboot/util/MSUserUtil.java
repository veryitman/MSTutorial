package com.veryitman.springboot.util;

import com.veryitman.springboot.model.MSUser;

public final class MZUserUtil {

    public static MSUser createDefaultUser(String userName, String userPwd) {
        MSUser user = new MSUser();
        user.setAccountName(userName);
        user.setNickName(userName);
        user.setAccountPwd(userPwd);
        user.setGender(1);
        user.setAge(20);
        user.setUserID("1");
        user.setMotto("");
        return user;
    }
}
