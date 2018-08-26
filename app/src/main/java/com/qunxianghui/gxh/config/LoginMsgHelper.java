package com.qunxianghui.gxh.config;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.qunxianghui.gxh.utils.SPUtils;


/**
 * @author 小强
 * @time 2018/5/18  18:18
 * @desc SharedPreferences保存常量
 */
public class LoginMsgHelper {

    //是否登录
    public static boolean isLogin() {
        String result = SPUtils.getString(SpConstant.ACCESS_TOKEN, "");
        return !TextUtils.isEmpty(result);
    }

    //登录退出处理
    public static void exitLogin() {
        SPUtils.removePreference(SpConstant.ACCESS_TOKEN);
        OkGo.getInstance().getCommonHeaders().remove("X-accesstoken");
    }


}
