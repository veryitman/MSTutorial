package com.veryitman.msblog;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

public abstract class MZBaseActiviy extends Activity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected boolean isInvalideActivityContext() {
        return (isDestroyed() || isFinishing());
    }

    /** ----------------------------------------Dialog------------------------------------------- */

    protected void showLoading() {
        if (null == progressDialog) {
            progressDialog = ProgressDialog.show(this, "", "Wait while loading...");
        }
    }

    protected void showLoadingAndAutoDismiss() {
        showLoading();

        new Handler(getMainLooper()).postDelayed(() -> {
            dismissLoading();
        }, 2500);
    }

    protected void dismissLoading() {
        if (null != progressDialog && progressDialog.isShowing() && !isInvalideActivityContext()) {
            progressDialog.dismiss();
        }
    }

    protected void enterScene(Class activityClz) {
        Intent intent = new Intent(this, activityClz);
        this.startActivity(intent);
        this.finish();
    }
}
