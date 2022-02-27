package com.veryitman.design.mvp;

import com.veryitman.design.model.User;

public class LoginServiceImpl implements ILoginService {
    @Override
    public boolean login(String userName, String userPwd) {
        if (userName.equals("admin") && userPwd.equals("adminpwd")) {
            User user = new User();
            user.setUserName(userName);
            user.setUserPwd(userPwd);
            return true;
        }
        return false;
    }
}
