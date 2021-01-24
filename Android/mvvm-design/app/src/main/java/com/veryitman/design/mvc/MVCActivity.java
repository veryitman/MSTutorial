package com.veryitman.design.mvc;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.veryitman.design.R;
import com.veryitman.design.model.User;

// https://blog.csdn.net/jerechen/article/details/100058519
public class MVCActivity extends AppCompatActivity {
    private EditText userNameEd;
    private EditText userPedEd;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvc_login);

        userNameEd = findViewById(R.id.et_name);
        userPedEd = findViewById(R.id.et_pwd);
    }

    public void onLoginClick(View view) {
        String name = userNameEd.getText().toString().trim();
        String pwd = userPedEd.getText().toString().trim();
        if (name.equals("admin") && pwd.equals("adminpwd")) {
            user = new User();
            user.setUserName(name);
            user.setUserPwd(pwd);

            Toast.makeText(this, name + " Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
