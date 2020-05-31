package com.veryitman.msblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.veryitman.msblog.model.MZResponseModel;
import com.veryitman.msblog.model.MZUserModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.veryitman.msblog.model.MZBaseModel.MZBaseURL;
import static com.veryitman.msblog.model.MZBaseModel.MZLogTag;

/**
 * 登录.
 */
public class MZSigninActivity extends MZBaseActiviy {

    private EditText userNameText;
    private EditText userPwdText;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        userNameText = findViewById(R.id.text_user_name_sigin);
        userPwdText = findViewById(R.id.text_user_pwd_signin);
        // @For Test easy
        userNameText.setText("foobar");
        userPwdText.setText("foobar");
    }

    public void onSigninClick(View view) {

        String userName = userNameText.getText().toString().trim();
        if (null == userName || 0 >= userName.length()) {
            return;
        }
        String userPwd = userPwdText.getText().toString().trim();
        if (null == userPwd || 0 >= userPwd.length()) {
            return;
        }

        showLoadingAndAutoDismiss();

        OkHttpClient httpClient = new OkHttpClient();
        //MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        HttpUrl.Builder httpBuilder = HttpUrl.parse(MZBaseURL + "signin/name").newBuilder();
        HttpUrl httpUrl = httpBuilder.addQueryParameter("username", userName).addQueryParameter("userpwd", userPwd).build();
        Request request = new Request.Builder().url(httpUrl.url()).addHeader("content-type", "application/json").get().build();
        Call call = httpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(MZLogTag, "signin HTTP response: " + response.toString());
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    Gson gson = new Gson();
                    MZResponseModel<MZUserModel> responseModel = gson.fromJson(jsonObject.toString(), MZResponseModel.class);
                    if (null != responseModel && 0 == responseModel.code) {
                        //和下面代码同样效果
                        //JSONObject userModelJsonObj = new JSONObject(jsonObject.getString("results"));
                        JSONObject userModelJsonObj = new JSONObject((Map) responseModel.results);
                        MZUserModel userModel = new Gson().fromJson(userModelJsonObj.toString(), MZUserModel.class);
                        //Crash：com.google.gson.internal.LinkedTreeMap cannot be cast to *.MZUserModel
                        //userModel = responseModel.results;
                        Log.d(MZLogTag, "signin HTTP response successful: " + userModel.toString());
                        runOnUiThread(() -> {
                            Toast.makeText(MZSigninActivity.this, "Sign in successfully", Toast.LENGTH_SHORT);
                            MZSigninActivity.this.enterMainScene();
                        });
                    } else {
                        Log.d(MZLogTag, "signin HTTP response error: " + (null != responseModel ? responseModel.msg : "Unknown error."));
                        runOnUiThread(() -> {
                            Toast.makeText(MZSigninActivity.this, "Sign in failed", Toast.LENGTH_SHORT);
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(MZLogTag, "signin HTTP response parse error: " + e.toString());
                    runOnUiThread(() -> {
                        Toast.makeText(MZSigninActivity.this, "Sign in failed", Toast.LENGTH_SHORT);
                    });
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(MZLogTag, "signin HTTP response onFailure: " + e.toString());
                runOnUiThread(() -> {
                    Toast.makeText(MZSigninActivity.this, "Sign in failed", Toast.LENGTH_SHORT);
                });
            }
        });
    }

    /**
     * ----------------------------------------Private-------------------------------------------------------
     */

    private void enterMainScene() {
        Intent intent = new Intent(this, MZMainActivity.class);
        this.startActivity(intent);
        this.finish();
    }
}
