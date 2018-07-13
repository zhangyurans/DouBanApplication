package com.example.lenovo.doubanapplication.tool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 2018/2/13.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        LogUtil.d("BaseActivity", getClass().getSimpleName());
        Activitycollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Activitycollector.removeActivity(this);
    }
}