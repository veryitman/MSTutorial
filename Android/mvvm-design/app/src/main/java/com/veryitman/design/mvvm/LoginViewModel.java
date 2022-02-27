/**
 * ViewModel 层：
 * 1、持有 Model 层的信息；
 * 2、对 View 层不感知，但是 View 层是感知 ViewModel 层的；一个View可以对应多个ViewModel；
 */
package com.veryitman.design.mvvm;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.veryitman.design.model.User;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Boolean> loginLiveData;

    private User user;

    public LoginViewModel() {
        this.loginLiveData = new MutableLiveData<>();
        user = new User();
    }

    public MutableLiveData loginLiveData() {
        return loginLiveData;
    }

    public void setLoginLiveData(boolean success) {
        this.loginLiveData.postValue(success);
    }

    public void login(String name, String pwd) {
        if (name.equals("admin") && pwd.equals("adminpwd")) {
            user.setUserName(name);
            user.setUserPwd(pwd);
            setLoginLiveData(true);
        } else {
            setLoginLiveData(false);
        }
    }

    public String getLoginUserName() {
        return user.getUserName();
    }

    public String getLoginUserPwd() {
        return user.getUserPwd();
    }
}
