package com.veryitman.msblog.ui.category;

import android.os.Handler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CategoryViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CategoryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category fragment");

        // 模拟model变化，此时UI不需要做任何处理（MVVM模式）
        new Handler().postDelayed(()->{
            mText.setValue("This is a changed text.");
        }, 5000);
    }

    public LiveData<String> getText() {
        return mText;
    }
}