package com.veryitman.msblog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.veryitman.msblog.http.MZOkHTTPWrapper;
import com.veryitman.msblog.http.MZSigninHttp;
import com.veryitman.msblog.model.MZUserModel;

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Signin;

/**
 * 登录.
 */
public class MZSigninActivity extends MZBaseActiviy {

    private EditText userNameText;
    private EditText userPwdText;

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

        MZSigninHttp.signinByUserNameRequest(userName, userPwd, new MZOkHTTPWrapper.HttpCallback() {
            @Override
            public void onSucess(Object model) {
                MZUserModel userModel = (MZUserModel) model;
                runOnUiThread(() -> {
                    Log.d(MZLogTag4Signin, "Signin success info: " + userModel.toString());
                    // 登录
                    enterScene(MZMainActivity.class);
                });
            }

            @Override
            public void onFailure(int code, String msg) {
                runOnUiThread(() -> {
                    String tipMsg = msg;
                    if (null == msg || 0 >= msg.length()) {
                        tipMsg = getString(R.string.signin_failed);
                    }
                    Toast.makeText(MZSigninActivity.this, tipMsg, Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    /**
     * ----------------------------------------Private-------------------------------------------------------
     */


}
