package com.veryitman.springboot.util;

import com.veryitman.springboot.model.MSUser;

public final class MSUserUtil {

    public static MSUser createUser(String userName, String userPwd) {
        MSUser user = new MSUser();
        if (null == userName) {
            userName = "admin";
        }
        user.setAccountName(userName);
        user.setNickName(userName);
        if (null == userPwd) {
            userPwd = "admin";
        }
        user.setAccountPwd(userPwd);
        user.setGender(1);
        user.setAge(20);
        // 暂时先用随机数，区间是[1, Integer.MAX_VALUE]，后续需要优化
        int num = 1 + (int) (Math.random() * (Integer.MAX_VALUE - 1 + 1));
        user.setUserID(num);
        user.setMotto("");
        user.setPhone("");

        return user;
    }
}
