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
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Http;
import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Signup;
import static com.veryitman.msblog.model.MZConstantsModel.HTTPTimeout;
import static com.veryitman.msblog.model.MZHttpUrlModel.MZUserNameSignupURL;
import static com.veryitman.msblog.model.MZResponseModel.ResponseSuccessCode;

public class MZOkHTTPWrapper {

    public static MZOkHTTPWrapper getInstance() {
        return MZOkHTTPWrapperHolder.wrapper;
    }

    public OkHttpClient requestClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .writeTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .readTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .callTimeout(HTTPTimeout+5, TimeUnit.SECONDS) //如果超时（从enqueue开始计时）自动调用cancel取消网络请求
                .retryOnConnectionFailure(false) //不自动重试
                .build();
        // 当前实例最大并发数，默认值是64
        client.dispatcher().setMaxRequests(100);
        // 被请求的每个主机最大并发数，默认值是5
        client.dispatcher().setMaxRequestsPerHost(10);
        return client;
    }

    public Call getRequest(String url) {
        return null;
    }

    public Call postRequest(HashMap<String, String> paramsMap, String url, HttpCallback callback) {
        if (null == url || 0 >= url.length()) {
            Log.e(MZLogTag4Http, "Request's url is empty");
            return null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            builder.add(key, paramsMap.get(key));
        }

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = this.requestClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(MZLogTag4Http, "Request of post response: " + response.toString());

                if ((null != callback)) {
                    callback.onSucess(response);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(MZLogTag4Http, "Request of post failed: " + e.toString());
                if ((null != callback)) {
                    callback.onFailure(-2, "Request of post failed");
                }
            }
        });
        return call;
    }

    public static class MZOkHTTPWrapperHolder {
        private static final MZOkHTTPWrapper wrapper = new MZOkHTTPWrapper();
    }

    public interface HttpCallback<T> {
         void onSucess(T model);
         void onFailure(int code, String msg);
    }
}
