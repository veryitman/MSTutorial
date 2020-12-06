package com.veryitman.user.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class MSUser implements Serializable {

    public static final int GENDER_MALE = 1; //男
    public static final int GENDER_FEMALE = 2; //女

    private Integer userID;

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

    // 手机号码
    private String phone;
}
