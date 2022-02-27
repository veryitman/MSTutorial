package com.veryitman.design.mvp;

public interface ILoginView {
    String getUserName();
    String getUserPwd();
    void loginResult(boolean success);
}
