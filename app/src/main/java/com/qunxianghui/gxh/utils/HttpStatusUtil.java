package com.qunxianghui.gxh.utils;



import android.text.TextUtils;

import org.json.JSONObject;

/**
 * @author 小强
 * @time 2018/5/18  14:38
 * @desc 获取网络状态
 */
public class HttpStatusUtil {

    // 得到状态码
    public static boolean getStatus(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            int statusCode = jsonObject.getInt("code");
            if (statusCode == 0) {
                return true;
            }
        } catch (Exception ex) {
        }
        return false;
    }

    /**
     * 得到状态提示
     *
     * @param json
     * @return
     */
    public static String getStatusMsg(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            String message = jsonObject.getString("message");
            if (!TextUtils.isEmpty(message)) {
                return message;
            }else {
                return json;
            }
        } catch (Exception ex) {

            return json;
        }
    }

    /**
     * 得到状态异常码
     *
     * @param json
     * @return
     */
    public static int getStatusError(String json) {

        try {
            JSONObject jsonObject = new JSONObject(json);
            int error = jsonObject.getInt("code");

            return error;
        } catch (Exception ex) {
            return 0;
        }
    }

}
