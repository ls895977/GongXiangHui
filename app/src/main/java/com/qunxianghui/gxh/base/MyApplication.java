package com.qunxianghui.gxh.base;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.multidex.MultiDexApplication;

import com.lljjcoder.style.citylist.utils.CityListLoader;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.qunxianghui.gxh.BuildConfig;
import com.qunxianghui.gxh.activity.WelcomActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.SQLHelper;
import com.qunxianghui.gxh.utils.AppManager;
import com.qunxianghui.gxh.utils.CityPickerutil;
import com.qunxianghui.gxh.utils.CrashHandler;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.ScreenUtils;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;
public class MyApplication extends MultiDexApplication {
    private SQLHelper sqlHelper;
    public static Class<?> next = null;
    public static Bundle nextBundle = null;
    private static MyApplication SINSTANCE;
    private static IWXAPI SWXAPI;
    public static AppManager appManager;
    private String mAccessToken;
    public static MyApplication getMyApplicaiton() {
        return SINSTANCE;
    }
    public static IWXAPI getWxApi() {
        return SWXAPI;
    }

    public static void setWxApi(IWXAPI api) {
        SWXAPI = api;
    }

    /**
     * 获取Application
     */
    public static MyApplication getApp() {
        return SINSTANCE;
    }

    /**
     * 获取数据库Helper
     */
    public SQLHelper getSQLHelper() {
        if (sqlHelper == null)
            sqlHelper = new SQLHelper(SINSTANCE);
        return sqlHelper;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if("debug".equals(BuildConfig.BUILD_TYPE)) {
            CrashHandler.getInstance().init(getApplicationContext());
        }else{
            Thread.setDefaultUncaughtExceptionHandler(restartHandler);
        }
        SINSTANCE = this;
        appManager = AppManager.getAppManager();

        Logger.d("onCreate-->:" + mAccessToken);

        initOkGo();
        initThirdLib();
    }

    private void initOkGo() {
        if (LoginMsgHelper.isLogin(this)) {
            mAccessToken = SPUtils.getString(this, SpConstant.ACCESS_TOKEN, "");
        }
        Logger.d("initOkGo-->:" + mAccessToken);
        //全局参数
//        final HttpParams params = new HttpParams();
//        params.put("app_key", 100);
//        params.put("access_token", mAccessToken);
        HttpHeaders header = new HttpHeaders();
        header.put("X-appkey", "100");
        header.put("X-accesstoken", mAccessToken);

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
                .addCommonHeaders(header);
    }

    private void initThirdLib() {
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
        PlatformConfig.setSinaWeibo("3748625219", "774db026cf85b9e14bcee6f822cc5d5d", "http://api.qunxianghui.com.cn/v1/user/callback/weibo");//微博

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

    private Thread.UncaughtExceptionHandler restartHandler = new Thread.UncaughtExceptionHandler() {
        public void uncaughtException(Thread thread, Throwable ex) {
            //发生崩溃异常时,重启应用
            Intent intent = new Intent(SINSTANCE, WelcomActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            SINSTANCE.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());  //结束进程之前可以把你程序的注销或者退出代码放在这段代码之前
        }
    };

    @Override
    public void onTerminate() {
        if (sqlHelper != null)
            sqlHelper.close();
        super.onTerminate();
        //整体摧毁的时候调用这个方法
    }

}
