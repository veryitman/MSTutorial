package com.veryitman.msblog.model;

public class MZResponseModel<T> extends MZBaseModel {
    public int code;
    public String msg;
    public T results;
}
