package com.qunxianghui.gxh.utils;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskUtil {

    private static TaskUtil mInstance = new TaskUtil();

    private ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    public static TaskUtil getInstance(){
        return mInstance;
    }

    private TaskUtil(){}

    public void runOnThread(Runnable runnable){
        singleThreadPool.execute(runnable);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());


    public Handler getHandler(){
        return mHandler;
    }

    public void runOnMainThread(Runnable runnable){
        runOnMainThread(runnable,0);
    }
    public void runOnMainThread(Runnable runnable, long delayed){
        if (delayed == 0){
            mHandler.post(runnable);
        }else {
            mHandler.postDelayed(runnable, delayed);
        }
    }

}
