package com.qunxianghui.gxh.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.qunxianghui.gxh.bean.SigninBean;

import static com.qunxianghui.gxh.base.MyApplication.SINSTANCE;


public final class SPUtils {

    private final static String name = "config";
    private final static int mode = Context.MODE_PRIVATE;

    public static SharedPreferences getSp() {
        return SINSTANCE.getSharedPreferences(name, mode);
    }

    /**
     * 保存首选项
     */
    public static void saveBoolean(String key, boolean value) {
        getSp().edit().putBoolean(key, value).apply();
    }

    public static void saveInt(String key, int value) {
        getSp().edit().putInt(key, value).apply();
    }

    public static void saveString(String key, String value) {
        getSp().edit().putString(key, value).apply();
    }

    /**
     * 获取首选项
     */
    public static boolean getBoolean(String key, boolean defValue) {
        return getSp().getBoolean(key, defValue);
    }

    public static int getInt(String key, int defValue) {
        return getSp().getInt(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return getSp().getString(key, defValue);
    }


    private static final String SPNAME = "gongxianghui";
    private static SharedPreferences sp;

    public static void putBoolean(String key, boolean value) {
        if (sp == null) {
            sp = SINSTANCE.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(String key) {
        if (sp == null) {
            sp = SINSTANCE.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        boolean b = sp.getBoolean(key, false);
        return b;
    }


    //    /**
    //     * 获取登录信息
    //     */
    public static SigninBean.DataBean.MemberBean getSignInfo() {
        final String user = getString("User", "");
        if (!user.equals("")) {
            final SigninBean signinBean = GsonUtil.parseJsonWithGson(user, SigninBean.class);
            final int errno = signinBean.getErrno();
            if (errno == 0) {
                return signinBean.getData().getMember();
            }
            return null;
        } else {
            return null;
        }
    }

    //    /**
    //     * 修改用户登录信息
    //     */
    public static void putSignInfo(SigninBean.DataBean.MemberBean signInfo) {
        final String user = getString("User", "");
        if (!user.equals("")) {
            final SigninBean signinBean = GsonUtil.parseJsonWithGson(user, SigninBean.class);
            final int errno = signinBean.getErrno();
            if (errno == 0) {
                signinBean.getData().setMember(signInfo);
                saveString("User", new Gson().toJson(signinBean));
            }
        }
    }

    /*获取登录后的Token*/

    public static String getToken() {
        final String user = getString("User", "");
        if (!user.equals("")) {
            final SigninBean signinBean = GsonUtil.parseJsonWithGson(user, SigninBean.class);
            final int errno = signinBean.getErrno();
            if (errno == 0) {
                return signinBean.getData().getToken();
            }
            return null;
        } else {
            return null;
        }
    }

    /**
     * ==================清除保存的数据=====================
     */
    public static void removePreference(String key) {
        SharedPreferences settings = getSp();
        if (settings.contains(key)) {
            settings.edit().remove(key).apply();
        }
    }
}
