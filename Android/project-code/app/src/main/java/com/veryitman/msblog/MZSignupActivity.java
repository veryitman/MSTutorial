package com.veryitman.msblog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 注册.
 */
public class MZSignupActivity extends Activity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onGotoSigninClick(View view) {
        progressDialog = ProgressDialog.show(this, "", "Wait while loading...");
        progressDialog.dismiss();
        Intent intent = new Intent(this, MZSigninActivity.class);
        this.startActivity(intent);
        this.finish();
    }

    public void onSignupClick(View view) {
        progressDialog.dismiss();
        OkHttpClient client = new OkHttpClient();

//        HashMap<String, String> paramsMap = new HashMap<>();
//        paramsMap.put("name", "哈哈");
//        paramsMap.put("client", "Android");
//        paramsMap.put("id", "3243598");
//        FormBody.Builder builder = new FormBody.Builder();
//        for (String key : paramsMap.keySet()) {
//            //追加表单信息
//            builder.add(key, paramsMap.get(key));
//        }

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        JSONObject json = new JSONObject();
        try {
            json.put("", "");
            json.put("", "");
        } catch (JSONException e) {

        }
        RequestBody requestBody = RequestBody.create(String.valueOf(json), JSON);
        Request request = new Request.Builder().url("url").get().build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
            }

            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败的处理
            }
        });
    }
}
