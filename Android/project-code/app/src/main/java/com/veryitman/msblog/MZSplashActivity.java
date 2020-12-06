package com.veryitman.msblog;

import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

public class MZSplashActivity extends MZBaseActiviy {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler(getMainLooper()).postDelayed(() -> {
            MZSplashActivity.this.enterSignupScene();
        }, 3000);
    }

    private void enterSignupScene() {
        this.enterScene(MZSignupActivity.class);
    }
}
