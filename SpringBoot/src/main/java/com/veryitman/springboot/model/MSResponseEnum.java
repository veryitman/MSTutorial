package com.veryitman.springboot.model;

public enum MSResponseEnum {

    /**
     * Global code.
     */
    SUCCESS(0, "Success."),
    UNKNOWN_ERROR(-1, "Unknown error."),

    /**
     * Login error.
     */
    Login4SiginInvalidInfo(100001, "User's name or password is invalid."),

    LoginNoSuchUser(100002, "No such user."),

    /**
     * Signup error.
     */
    SignupInvalidInfo(110005, "User's name or password is invalid"),

    SignupHasExistUser(110006, "User has existed and go to siguin");

    private int code;
    private String msg;

    MSResponseEnum(int code, String msg) {
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
