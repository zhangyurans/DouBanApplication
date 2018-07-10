package com.example.lenovo.doubanapplication;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG="CrashHandler";
    private static final boolean DEBUG=true;

    private static final String PATH= Environment.getExternalStorageDirectory().getPath()+"/CrashTest/log/";
    private static final String FILE_NAME="crash";
    private static final String FILE_NAME_SUFFIX=".trace";

    private static CrashHandler sInstance=new CrashHandler();
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;
    private Context mContext;

    private CrashHandler(){
    };
    public static CrashHandler getsInstance(){
        return sInstance;
    }
    public void init(Context context){
        mDefaultCrashHandler=Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        mContext=context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread,Throwable ex){
        try{
            dumpExceptionToFile(ex);
        }catch (IOException e){
            e.printStackTrace();
        }
        ex.printStackTrace();
        if (mDefaultCrashHandler!=null){
            mDefaultCrashHandler.uncaughtException(thread,ex);
        }
        else {
            Process.killProcess(Process.myPid());
        }
    }
    private void dumpExceptionToFile(Throwable ex)throws IOException{
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            if(DEBUG){
                LogUtil.w(TAG,"sdcard unmonted,skip dump exception");
                return;
            }
        }
        File dir=new File(PATH);
        if (!dir.exists()){
            dir.mkdirs();
        }
        long current=System.currentTimeMillis();
        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(current));
        File file=new File(mContext.getCacheDir()+"crash.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        try{
            PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));
            pw.println(time);
            dumpPhoneInfo(pw);
            pw.println();
            ex.printStackTrace(pw);
            pw.close();
            LogUtil.d(TAG,"dump crash info success");
        }catch (Exception e){
            LogUtil.e(TAG,"dump crash info failed");
            LogUtil.e(TAG,e.toString());
        }
    }
    private void dumpPhoneInfo(PrintWriter pw)throws PackageManager.NameNotFoundException{
        PackageManager pm=mContext.getPackageManager();
        PackageInfo pi=pm.getPackageInfo(mContext.getPackageName(),PackageManager.GET_ACTIVITIES);
        pw.print("App Version: ");
        pw.print(pi.versionName);
        pw.print('_');
        pw.println(pi.versionCode);

        //Android版本号
        pw.print("OS Version: ");
        pw.print(Build.VERSION.RELEASE);
        pw.print("_");
        pw.println(Build.VERSION.SDK_INT);

        //手机制造商
        pw.print("Vendor: ");
        pw.println(Build.MANUFACTURER);

        //手机型号
        pw.print("Model: ");
        pw.println(Build.MODEL);

        //CPU架构
        pw.print("CPU ABI: ");
        pw.println(Build.CPU_ABI);
    }
}
