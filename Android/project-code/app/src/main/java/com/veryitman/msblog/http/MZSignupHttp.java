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

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Signup;
import static com.veryitman.msblog.model.MZHttpUrlModel.MZUserNameSignupURL;
import static com.veryitman.msblog.model.MZResponseModel.ResponseFailureOfSignup;
import static com.veryitman.msblog.model.MZResponseModel.ResponseSuccessCode;

public class MZSignupHttp {

    public static Call signupByUserNameRequest(String userName, String userPwd, MZOkHTTPWrapper.HttpCallback callback) {
        HashMap params = new HashMap(2);
        params.put("username", userName);
        params.put("userpwd", userPwd);

        Call call = MZOkHTTPWrapper.getInstance().postRequest(MZUserNameSignupURL, params, new MZOkHTTPWrapper.HttpCallback() {
            @Override
            public void onSucess(Object model) {
                Response response = (Response) model;
                Log.d(MZLogTag4Signup, "Signup use user_name response: " + response.toString());
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
                                Log.e(MZLogTag4Signup, "Signup Failed. code: " + responseModel.code + ", msg: " + responseModel.msg);
                                callback.onFailure(responseModel.code, responseModel.msg);//应放到主线程
                            }
                        }
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                        Log.e(MZLogTag4Signup, "Signup exception: " + e.toString());
                        if (null != callback) {
                            callback.onFailure(ResponseFailureOfSignup, "Signup failed that parse data exception");//应放到主线程
                        }
                    }
                } else {
                    if (null != callback) {
                        Log.e(MZLogTag4Signup, "Signup fail: " + response.message());
                        callback.onFailure(ResponseFailureOfSignup, response.message());//应放到主线程
                    }
                }
            }

            @Override
            public void onFailure(int code, String msg) {
                //请求失败的处理
                Log.e(MZLogTag4Signup, "Signup Failed. code: " + code + ", msg: " + msg);
                if (null != callback) {
                    callback.onFailure(ResponseFailureOfSignup, "Signup failed");//应放到主线程
                }
            }
        });

        return call;
    }
}
