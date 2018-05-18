package com.qunxianghui.gxh.config;

import android.content.Context;
import android.text.TextUtils;

import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.utils.JsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;


/**
 * @author 小强
 * @time 2018/5/18  18:18
 * @desc SharedPreferences保存常量
 */
public class LoginMsgHelper {

    //是否登录
    public static boolean isLogin(Context mContext) {
        String result = SPUtils.getString(mContext, SpConstant.LOGIN_MSG, null);
        if (!TextUtils.isEmpty(result)) {
            return true;
        }
        return false;
    }

    //登录之后返回的结果
    public static LoginBean getResult(Context mContext) {
        String result = SPUtils.getString(mContext, SpConstant.LOGIN_MSG, null);

        if (result != null)
            if (!TextUtils.isEmpty(result)) {
                LoginBean loginBean = JsonUtil.fromJson(result, LoginBean.class);
                return loginBean;

            }
        return null;
    }

    //登录退出处理
    public static void exitLogin(Context mContext) {
        SPUtils.removePreference(mContext, SpConstant.LOGIN_MSG);
        SPUtils.removePreference(mContext, SpConstant.ACCESS_TOKEN);
    }

}
