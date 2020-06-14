package com.veryitman.msblog.model;

public class MZResponseModel<T> extends MZBaseModel {
    public static final int ResponseSuccessCode = 0;
    public int code;
    public String msg;
    public T results;
}
