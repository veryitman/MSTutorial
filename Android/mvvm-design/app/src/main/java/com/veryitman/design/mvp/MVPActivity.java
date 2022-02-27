package com.veryitman.design.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.veryitman.design.R;

public class MVPActivity extends AppCompatActivity implements ILoginView {
    private EditText userMVPNameEd;
    private EditText userMVPPwdEd;

    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_login);

        userMVPNameEd = findViewById(R.id.et_mvp_name);
        userMVPPwdEd = findViewById(R.id.et_mvp_pwd);

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public String getUserName() {
        return userMVPNameEd.getText().toString().trim();
    }

    @Override
    public String getUserPwd() {
        return userMVPPwdEd.getText().toString().trim();
    }

    @Override
    public void loginResult(boolean success) {
        if (success) {
            String tipContent = getUserName() + " Login Successful" + " use " + getUserPwd();
            Toast.makeText(this, tipContent, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }

    public void onMVPLoginClick(View view) {
        loginPresenter.login();
    }
}
