package com.veryitman.msblog;

import android.app.Application;
import android.content.Context;

import com.dianping.logan.Logan;
import com.dianping.logan.LoganConfig;

import java.io.File;
import java.util.Map;

public class MZApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LoganConfig config = new LoganConfig.Builder()
                .setCachePath(getApplicationContext().getFilesDir().getAbsolutePath())
                .setPath(getApplicationContext().getExternalFilesDir(null).getAbsolutePath()
                        + File.separator + "logan_v1")
                .setEncryptKey16("0123456789012345".getBytes())
                .setEncryptIV16("0123456789012345".getBytes())
                .build();
        Logan.init(config);
        Logan.setDebug(true);

        Logan.w("test logan", 1);
        Map map = Logan.getAllFilesInfo();
        System.out.println("logan map key: " + map.keySet());
        System.out.println("logan map value: " + map.values());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
