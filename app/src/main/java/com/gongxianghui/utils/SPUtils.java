package com.gongxianghui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.bean.SigninBean;
import com.google.gson.Gson;



public final class SPUtils {

    private final static String name = "config";
    private final static int mode = Context.MODE_PRIVATE;

    /**
     * 保存首选项
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    public static void saveInt(Context context, String key, int value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public static void saveString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        Editor edit = sp.edit();
        edit.putString(key, value);
        edit.commit();
    }


    /**
     * 获取首选项
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key, boolean defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getBoolean(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getInt(key, defValue);
    }

    public static String getString(Context context, String key, String defValue) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp.getString(key, defValue);
    }


    private static final String SPNAME = "gongxianghui";
    private static SharedPreferences sp;

    public static void putBoolean(String key, boolean value, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, Context context) {
        if (sp == null) {
            sp = context.getSharedPreferences(SPNAME, Context.MODE_PRIVATE);
        }
        boolean b = sp.getBoolean(key, false);
        return b;
    }


//    /**
//     * 获取登录信息
//     */
    public static SigninBean.DataBean.MemberBean getSignInfo(Context context) {
        final String user = getString(context, "User", "");
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
    public static void putSignInfo(Context context, SigninBean.DataBean.MemberBean signInfo) {
        final String user = getString(context, "User", "");
        if (!user.equals("")) {
            final SigninBean signinBean = GsonUtil.parseJsonWithGson(user, SigninBean.class);
            final int errno = signinBean.getErrno();
            if (errno == 0) {
                signinBean.getData().setMember(signInfo);
                saveString(context, "User", new Gson().toJson(signinBean));
            }
        }
    }

    /*获取登录后的Token*/

    public static String getToken(Context context) {
        final String user = getString(context, "User", "");
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
}
