package com.veryitman.design.model;

import java.io.Serializable;

public class User implements Serializable {
    private String userName;
    private String userPwd;

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
