package com.veryitman.springboot.model;

import java.io.Serializable;
import java.util.Objects;

public class MSUser implements Serializable {

    private String userID;

    // 账号名称，用于登录，不可以修改
    private String accountName;

    // 账号密码
    private String accountPwd;

    // 昵称默认和accountName一致，可以修改
    private String nickName;

    private Integer age;

    private Integer gender;

    // 座右铭；格言；箴言
    private String motto;

    public String getUserID() {
        return userID;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getNickName() {
        if (null == this.nickName) {
            this.nickName = "";
        }
        return nickName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public Integer getAge() {
        return age;
    }

    public Integer getGender() {
        return gender;
    }

    public String getMotto() {
        if (null == this.motto) {
            this.motto = "";
        }
        return motto;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setNickName(String nickName) {
        if (null == nickName) {
            this.nickName = this.accountName;
        } else {
            this.nickName = nickName;
        }
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public void setMotto(String motto) {
        if (null == motto) {
            this.motto = "";
        } else {
            this.motto = motto;
        }
    }

    @Override
    public String toString() {
        return "user info. " + "uid: " + this.userID;
    }

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }

        if (obj instanceof MSUser) {
            MSUser user = (MSUser) obj;
            return this.userID.equals(user.getUserID());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, accountName, age, gender, accountPwd, nickName);
    }
}
