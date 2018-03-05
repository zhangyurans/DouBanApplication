package com.example.lenovo.doubanapplication;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2018/2/13.
 */

public class Activitycollector {//管理所有活动
    public static List<Activity> activities=new ArrayList<>();//暂存活动
    public static void addActivity(Activity activity){//添加一个活动
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){//去除一个活动
        activities.remove(activity);
    }
    public static void finishAll(){//销毁所有活动
        for (Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
