package com.veryitman.msblog.model;

public class MZResponseModel<T> extends MZBaseModel {

    public static final int ResponseSuccessCode = 0;
    public static final int ResponseFailure = 0X10002;
    public static final int ResponseFailureOfGET = ResponseFailure + 1;
    public static final int ResponseFailureOfPOST = ResponseFailure + 2;

    public static final int ResponseFailureOfSignin = ResponseFailure + 3;
    public static final int ResponseFailureOfSignup = ResponseFailure + 4;

    public int code;
    public String msg;
    public T results;
}
