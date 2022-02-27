package com.veryitman.design;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.veryitman.design.mvc.MVCActivity;
import com.veryitman.design.mvp.MVPActivity;
import com.veryitman.design.mvvm.MVVMActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMVCClick(View view) {
        Intent intent = new Intent(MainActivity.this, MVCActivity.class);
        startActivity(intent);
    }

    public void onMVPClick(View view) {
        Intent intent = new Intent(MainActivity.this, MVPActivity.class);
        startActivity(intent);
    }

    public void onMVVMClick(View view) {
        Intent intent = new Intent(MainActivity.this, MVVMActivity.class);
        startActivity(intent);
    }
}