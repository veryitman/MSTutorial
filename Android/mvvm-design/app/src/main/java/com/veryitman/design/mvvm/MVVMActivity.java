package com.veryitman.design.mvvm;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory;

import com.veryitman.design.R;

public class MVVMActivity extends AppCompatActivity {
    private EditText userMVVMNameEd;
    private EditText userMVVMPwdEd;

    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvvm_login);

        userMVVMNameEd = findViewById(R.id.et_mvvm_name);
        userMVVMPwdEd = findViewById(R.id.et_mvvm_pwd);

        AndroidViewModelFactory factory = new AndroidViewModelFactory(getApplication());
        ViewModelProvider provider = new ViewModelProvider(MVVMActivity.this, factory);
        loginViewModel = provider.get(LoginViewModel.class);
        loginViewModel.loginLiveData().observe(this, loginObserver);
    }

    private Observer<Boolean> loginObserver = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean aBoolean) {
            if (aBoolean) {
                String loginUserName = loginViewModel.getLoginUserName();
                String loginUserPwd = loginViewModel.getLoginUserPwd();
                String tipContent = loginUserName + " Login Successful" + " use " + loginUserPwd;
                Toast.makeText(MVVMActivity.this, tipContent, Toast.LENGTH_SHORT).show();
                MVVMActivity.this.finish();
            } else {
                Toast.makeText(MVVMActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void onMVVMLoginClick(View view) {
        String name = userMVVMNameEd.getText().toString().trim();
        String pwd = userMVVMPwdEd.getText().toString().trim();
        loginViewModel.login(name, pwd);
    }
}
