package com.qunxianghui.gxh.base;


import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.SQLHelper;
import com.qunxianghui.gxh.utils.AppManager;
import com.qunxianghui.gxh.utils.CityPickerutil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.ScreenUtils;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;


public class MyApplication extends Application {
    private static MyApplication mAppApplication;
    private SQLHelper sqlHelper;


    public static Class<?> next = null;
    public static Bundle nextBundle = null;

    private static MyApplication SINSTANCE;
    public static AppManager appManager;
    private String mAccessToken;

    public static MyApplication getMyApplicaiton() {

        return SINSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mAppApplication = this;
        SINSTANCE = this;
        Logger.d("onCreate-->:" + mAccessToken);

        initOkGo();
        appManager = AppManager.getAppManager();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }

        ScreenUtils.init(this);
        /**
         * 预先加载三级列表显示省市区的数据
         */
        CityListLoader.getInstance().loadProData(this);
        //重复 ？
        CityPickerutil.initDatas(this);

        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, null); //  友盟初始化
        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL); // 友盟统计场景：普通统计场景类型
        PlatformConfig.setWeixin("wx8dd50e08a25101d7", "b84c68b1fc941afec1aabda8360e34e1");//微信APPID和AppSecret
        PlatformConfig.setQQZone("1106763297", "KEYMQmvgOw2V73MXMZF");//QQAPPID和AppSecret
        PlatformConfig.setSinaWeibo("1367342454", "774db026cf85b9e14bcee6f822cc5d5d","http://api.qunxianghui.com.cn/v1/user/callback/weibo");//微博
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
        if (LoginMsgHelper.isLogin(this)) {
            mAccessToken = SPUtils.getString(this, SpConstant.ACCESS_TOKEN, "");
        }
        Logger.d("initOkGo-->:" + mAccessToken);
        //全局参数
        final HttpParams params = new HttpParams();
        params.put("app_key", 100);
        params.put("access_token", mAccessToken);
        HttpHeaders header = new HttpHeaders();
        header.put("X-appkey","100");
        header.put("X-accesstoken",mAccessToken);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志

        builder.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        OkGo.getInstance().init(this).setOkHttpClient(builder.build()).
                setCacheMode(CacheMode.NO_CACHE).
                setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE).
                setRetryCount(3)
//                .addCommonParams(params)
        .addCommonHeaders(header);


    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder().showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(1)
                .methodOffset(0)
                .tag("hzq")
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return true; //关闭打印日志设置为false
            }
        });
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
        initOkGo();
    }
}
