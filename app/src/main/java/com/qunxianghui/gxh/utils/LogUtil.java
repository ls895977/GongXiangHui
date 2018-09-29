package com.qunxianghui.gxh.utils;

import android.util.Log;

/**
 * Created by gs on 2018/9/28.
 */

public class LogUtil {
    public static void loge(String tag, String result) {
        if (result.length() > 3000) {
            for (int i = 0; i < result.length(); i += 3000) {
                if (i + 3000 < result.length())
                    Log.e(tag, "result第一段log===" + result.substring(i, i + 3000));
                else {
                    Log.e(tag, "result第二段log===" + result.substring(i, result.length()));
                }
            }
        } else {
            Log.e(tag, "result=" + result);
        }
    }
}
