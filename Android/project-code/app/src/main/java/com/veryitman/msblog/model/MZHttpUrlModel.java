package com.veryitman.msblog.model;

public class MZHttpUrlModel extends MZBaseModel {

    // 开发环境：本机环境（IP:Port方式访问SpringBoot服务）
    public static final String MZBaseURL = "http://172.20.10.2:8080";

    public static final String MZUserNameSignupURL = MZBaseURL + "/signup/name";
    public static final String MZUserNameSigninURL = MZBaseURL + "/signin/name";
}
