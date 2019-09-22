package com.veryitman.springboot.model;

import lombok.Data;

import java.io.Serializable;

@Data
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
}
