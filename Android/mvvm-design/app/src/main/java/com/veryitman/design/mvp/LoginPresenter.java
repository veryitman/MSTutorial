/**
 * P 层：
 * 1、对 Model 层不感知。将 View与 Model解耦，方便进行单元测试；
 * 2、通过 Interface 与 View 建立通信；
 */
package com.veryitman.design.mvp;

public class LoginPresenter {
    private ILoginView loginView;
    private LoginServiceImpl loginService;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        this.loginService = new LoginServiceImpl();
    }

    public void login() {
        String name = loginView.getUserName();
        String pwd = loginView.getUserPwd();
        boolean success = loginService.login(name, pwd);
        loginView.loginResult(success);
    }
}
