package com.veryitman.msblog.http;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Http;
import static com.veryitman.msblog.model.MZResponseModel.ResponseFailureOfGET;
import static com.veryitman.msblog.model.MZResponseModel.ResponseFailureOfPOST;

public class MZOkHTTPWrapper {
    // 最大并发数
    private static final int MaxRequestNum = 100;
    // 最大请求的主机数
    private static final int MaxRequestsPerHost = 10;

    // 超时时间为 10s
    public static final int HTTPTimeout = 10;

    private static final String ContentTypeValueOfJSON= "application/json";

    public static MZOkHTTPWrapper getInstance() {
        return MZOkHTTPWrapperHolder.wrapper;
    }

    public Call getRequest(String url, HashMap<String, String> paramsMap, HttpCallback callback) {
        if (null == url || 0 >= url.length()) {
            Log.e(MZLogTag4Http, "Request's url is empty for GET");
            return null;
        }

        HttpUrl.Builder httpBuilder = HttpUrl.parse(url).newBuilder();
        for (String key : paramsMap.keySet()) {
            httpBuilder.addQueryParameter(key, paramsMap.get(key));
        }
        HttpUrl httpUrl = httpBuilder.build();
        URL finalURL = httpUrl.url();
        Log.e(MZLogTag4Http, "Request\'s url of GET: " + finalURL.toString());
        Request request = new Request.Builder()
                .url(finalURL)
                .addHeader("content-type", ContentTypeValueOfJSON)
                .get()
                .build();
        Call call = this.requestClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e(MZLogTag4Http, "Request of GET failed: " + e.toString());
                if ((null != callback)) {
                    callback.onFailure(ResponseFailureOfGET, "Request of POST failed");
                }
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                Log.d(MZLogTag4Http, "Request of GET response: " + response.toString());

                if ((null != callback)) {
                    callback.onSucess(response);
                }
            }
        });

        return call;
    }

    public Call postRequest(String url, HashMap<String, String> paramsMap, HttpCallback callback) {
        if (null == url || 0 >= url.length()) {
            Log.e(MZLogTag4Http, "Request's url is empty for POST");
            return null;
        }
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : paramsMap.keySet()) {
            builder.add(key, paramsMap.get(key));
        }

        RequestBody requestBody = builder.build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("content-type", ContentTypeValueOfJSON)
                .post(requestBody)
                .build();
        Call call = this.requestClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(MZLogTag4Http, "Request of POST response: " + response.toString());

                if ((null != callback)) {
                    callback.onSucess(response);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(MZLogTag4Http, "Request of POST failed: " + e.toString());
                if ((null != callback)) {
                    callback.onFailure(ResponseFailureOfPOST, "Request of POST failed");
                }
            }
        });
        return call;
    }

    private OkHttpClient requestClient() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .writeTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .readTimeout(HTTPTimeout, TimeUnit.SECONDS)
                .callTimeout(HTTPTimeout + 5, TimeUnit.SECONDS) //如果超时（从enqueue开始计时）自动调用cancel取消网络请求
                .retryOnConnectionFailure(false) //不自动重试
                .build();
        // 当前实例最大并发数，默认值是64
        client.dispatcher().setMaxRequests(MaxRequestNum);
        // 被请求的每个主机最大并发数，默认值是5
        client.dispatcher().setMaxRequestsPerHost(MaxRequestsPerHost);
        return client;
    }

    public static class MZOkHTTPWrapperHolder {
        private static final MZOkHTTPWrapper wrapper = new MZOkHTTPWrapper();
    }

    public interface HttpCallback<T> {
        void onSucess(T model);

        void onFailure(int code, String msg);
    }
}
