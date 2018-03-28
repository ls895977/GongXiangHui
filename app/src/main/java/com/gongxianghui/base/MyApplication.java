package com.gongxianghui.base;


import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;


import com.gongxianghui.config.Constant;
import com.gongxianghui.utils.AppManager;
import com.lzy.okgo.OkGo;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;




public class MyApplication extends Application {

    public static Class<?> next = null;
    public static Bundle nextBundle = null;
//    private static IWXAPI SWXAPI;
    private static MyApplication SINSTANCE;
    public static AppManager appManager;

    public static MyApplication getMyApplicaiton() {

        return SINSTANCE;
    }

//    public static IWXAPI getWxApi() {
//        return SWXAPI;
//    }
//
//    public static void setWxApi(IWXAPI api) {
//        SWXAPI = api;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        SINSTANCE = this;
//        UMShareAPI.get(this);
//          Config.DEBUG=true;
        initOkGo();
//        api.registerApp(Constant.WEIXIN_APP_ID);
//        PayReq req = new PayReq();
////....拼接req参数
//        api.sendReq(req);// 调用支付
//        JPushInterface.setDebugMode(true);
//        JPushInterface.init(this);

        appManager = AppManager.getAppManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
    }

    private void initOkGo() {
        OkGo.getInstance().init(this);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(Constant.TIME_OUT, TimeUnit.SECONDS);
        OkGo.getInstance().setOkHttpClient(builder.build());
    }

    {
//        PlatformConfig.setWeixin(Constant.WEIXIN_APP_ID, Constant.WEIXIN_APP_SECRET);
//        PlatformConfig.setQQZone(Constant.QQZONE_APP_ID, Constant.QQZONE_APP_KEY);
//        PlatformConfig.setSinaWeibo(Constant.SINA_APP_KEY,
//                Constant.SINA_APP_SERCET, Constant.SINA_APP_URL);
    }
}
