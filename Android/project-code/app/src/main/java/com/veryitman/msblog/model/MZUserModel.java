package com.veryitman.msblog.model;

import androidx.annotation.NonNull;

public class MZUserModel extends MZBaseModel {
    public Integer userID;

    // 账号名称，用于登录，不可以修改
    public String accountName;

    // 账号密码
    public String accountPwd;

    // 昵称默认和accountName一致，可以修改
    public String nickName;

    public Integer age;

    public Integer gender;

    // 座右铭；格言；箴言
    public String motto;

    // 手机号码
    public String phone;

    @NonNull
    @Override
    public String toString() {
        return "[" + userID + "-" + accountName +
                "-" + accountPwd + "-" + nickName +
                "-" + age + "-" + gender + "-" +
                motto + "-" + phone + "]";
    }
}
