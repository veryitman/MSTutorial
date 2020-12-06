package com.veryitman.user.model;

public enum MSUserResponseEnum {

    /**
     * Global error code and message.
     */
    SUCCESS(0, "Success."),
    UNKNOWN_ERROR(-1, "Unknown error."),

    /**
     * Login error code and message.
     */
    Login4SiginInvalidInfo(100001, "User\'s name or password is invalid."),

    LoginNoSuchUser(100002, "No such user."),

    LoginUserPwdError(100003, "User\'s password is error."),

    /**
     * Signup error code and message.
     */
    SignupInvalidInfo(110005, "User\'s name or password is invalid."),

    SignupHasExistUser(110006, "User has existed and go to siguin.");

    private int code;
    private String msg;

    MSUserResponseEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
