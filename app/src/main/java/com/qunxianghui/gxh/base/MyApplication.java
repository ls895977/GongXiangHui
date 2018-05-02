package com.qunxianghui.gxh.base;


import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;



import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.SQLHelper;
import com.qunxianghui.gxh.utils.AppManager;

import com.lzy.okgo.OkGo;
import com.qunxianghui.gxh.utils.ScreenUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    private static MyApplication mAppApplication;
    private SQLHelper sqlHelper;

    public static Class<?> next = null;
    public static Bundle nextBundle = null;

    private static MyApplication SINSTANCE;
    public static AppManager appManager;

    public static MyApplication getMyApplicaiton() {

        return SINSTANCE;
    }

    //微信APPID
    public static String WeiXinAPP_ID="wx8dd50e08a25101d7";
    //QQAppID
    public static String QQAPP_ID="1106763297";
    public static IWXAPI api;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this;
        SINSTANCE = this;
        initOkGo();
        appManager = AppManager.getAppManager();
        //微信
        api = WXAPIFactory.createWXAPI(this, WeiXinAPP_ID, true);
        api.registerApp(WeiXinAPP_ID);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        ScreenUtils.init(this);
    }

    /**
     * 获取Application
     */
    public static MyApplication getApp() {
        return mAppApplication;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(mAppApplication);
        return sqlHelper;
    }

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        //整体摧毁的时候调用这个方法
    }
    
    private void initOkGo() {
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        OkGo.getInstance().setOkHttpClient(builder.build());
    }

    {


    }
}
