package com.veryitman.msblog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.veryitman.msblog.http.MZOkHTTPWrapper;
import com.veryitman.msblog.http.MZSignupHttp;
import com.veryitman.msblog.model.MZResponseModel;
import com.veryitman.msblog.model.MZUserModel;

import static com.veryitman.msblog.model.MZBaseModel.MZLogTag4Signup;

/**
 * 注册.
 */
public class MZSignupActivity extends MZBaseActiviy {

    private EditText userNameText;
    private EditText userPwdText;
    private EditText userPwdAgainText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        userNameText = this.findViewById(R.id.text_user_name);
        userPwdText = this.findViewById(R.id.text_user_pwd);
        userPwdAgainText = this.findViewById(R.id.text_user_pwd_again);
    }

    public void onGotoSigninClick(View view) {
        enterScene(MZSigninActivity.class);
    }

    public void onSignupClick(View view) {
        String userName = userNameText.getText().toString().trim();
        String userPwd = userPwdText.getText().toString().trim();
        String userPwdAgain = userPwdAgainText.getText().toString().trim();
        if (0 >= userName.length() || 0 >= userPwd.length() || 0 >= userPwdAgain.length()) {
            Toast.makeText(this, R.string.signup_input_info_wrong, Toast.LENGTH_LONG).show();
            return;
        }
        if (!userPwd.equals(userPwdAgain)) {
            Toast.makeText(this, R.string.signup_pwd_not_match, Toast.LENGTH_LONG).show();
            return;
        }
        MZSignupHttp.signupByUserNameRequest(userName, userPwd, new MZOkHTTPWrapper.HttpCallback() {
            @Override
            public void onSucess(Object model) {
                MZUserModel userModel = (MZUserModel) model;
                runOnUiThread(() -> {
                    Log.d(MZLogTag4Signup, "Signup success info: " + userModel.toString());
                    // 登录
                    enterScene(MZSigninActivity.class);
                });
            }

            @Override
            public void onFailure(int code, String msg) {
                runOnUiThread(() -> {
                    String tipMsg = msg;
                    if (null == msg || 0 >= msg.length()) {
                        tipMsg = getString(R.string.signup_failed);
                    }
                    Toast.makeText(MZSignupActivity.this, tipMsg, Toast.LENGTH_LONG).show();
                });
            }
        });
    }

    public void onEnterUseGuestClick(View view) {
        this.enterScene(MZMainActivity.class);
    }

    /** --------------------------------------private-------------------------------------------- */

}
