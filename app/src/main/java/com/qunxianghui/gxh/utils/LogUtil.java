package com.qunxianghui.gxh.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gs on 2018/9/28.
 */

public class LogUtil {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
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

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }

}
