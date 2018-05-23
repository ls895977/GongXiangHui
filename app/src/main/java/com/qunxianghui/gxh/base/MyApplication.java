package com.qunxianghui.gxh.base;


import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.SQLHelper;
import com.qunxianghui.gxh.utils.AppManager;
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
    public static String WeiXinAPP_ID = "wx8dd50e08a25101d7";
    //QQAppID
    public static String QQAPP_ID = "1106763297";
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
        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);


        /**
         * 创建日志
         */
        initLogger();
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
        final HttpParams params = new HttpParams();

        params.put("app_key",100);


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        OkGo.getInstance().init(this).setOkHttpClient(builder.build())
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3)
                .addCommonParams(params);


    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)         // (Optional) How many method line to show. Default 2
                .methodOffset(0)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("hzq")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true; //关闭打印日志设置为false
            }
        });
    }

}
