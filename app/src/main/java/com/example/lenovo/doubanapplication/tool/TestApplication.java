package com.example.lenovo.doubanapplication.tool;

import android.app.Application;

public class TestApplication extends Application {
    private static TestApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        CrashHandler crashHandler = CrashHandler.getsInstance();
        crashHandler.init(this);
        LogUtil.d("CrashHandler", "建立testApplication");
    }

    public static TestApplication getsInstance() {
        return sInstance;
    }
}
