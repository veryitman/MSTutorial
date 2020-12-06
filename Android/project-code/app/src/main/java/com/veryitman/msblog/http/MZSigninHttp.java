package com.veryitman.msblog.http;

import android.util.Log;

import com.google.gson.Gson;
import com.veryitman.msblog.model.MZResponseModel;
import com.veryitman.msblog.model.MZUserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Response;

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Signin;
import static com.veryitman.msblog.model.MZHttpUrlModel.MZUserNameSigninURL;
import static com.veryitman.msblog.model.MZResponseModel.ResponseFailureOfSignin;
import static com.veryitman.msblog.model.MZResponseModel.ResponseFailureOfSignup;
import static com.veryitman.msblog.model.MZResponseModel.ResponseSuccessCode;

public class MZSigninHttp {
    public static Call signinByUserNameRequest(String userName, String userPwd, MZOkHTTPWrapper.HttpCallback callback) {
        HashMap params = new HashMap(2);
        params.put("username", userName);
        params.put("userpwd", userPwd);

        Call call = MZOkHTTPWrapper.getInstance().getRequest(MZUserNameSigninURL, params, new MZOkHTTPWrapper.HttpCallback() {
            @Override
            public void onSucess(Object model) {
                Response response = (Response) model;
                Log.d(MZLogTag4Signin, "Signin use user_name response: " + response.toString());
                if (response.isSuccessful()) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        Gson gson = new Gson();
                        MZResponseModel<MZUserModel> responseModel = gson.fromJson(jsonObject.toString(), MZResponseModel.class);
                        if (ResponseSuccessCode == responseModel.code) {
                            JSONObject userModelJsonObj = new JSONObject((Map) responseModel.results);
                            MZUserModel userModel = new Gson().fromJson(userModelJsonObj.toString(), MZUserModel.class);
                            if (null != callback) {
                                callback.onSucess(userModel); //应放到主线程
                            }
                        } else {
                            if (null != callback) {
                                Log.e(MZLogTag4Signin, "Signin Failed. code: " + responseModel.code + ", msg: " + responseModel.msg);
                                callback.onFailure(responseModel.code, responseModel.msg);//应放到主线程
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.e(MZLogTag4Signin, "Signin exception: " + e.toString());
                        if (null != callback) {
                            callback.onFailure(ResponseFailureOfSignin, "Signin failed that parse data exception");//应放到主线程
                        }
                    }
                } else {
                    if (null != callback) {
                        Log.e(MZLogTag4Signin, "Signin fail: " + response.message());
                        callback.onFailure(ResponseFailureOfSignin, response.message());//应放到主线程
                    }
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                Log.e(MZLogTag4Signin, "Signup Failed. code: " + code + ", msg: " + msg);
                if (null != callback) {
                    callback.onFailure(ResponseFailureOfSignin, "Signup failed");//应放到主线程
                }
            }
        });

        return call;
    }
}
