/*
 * 2017.
 * Huida.Burt
 * CopyRight
 *
 *
 *
 */

package com.qunxianghui.gxh.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Burt on 2017/7/11 0011.
 */

public class REGutil {

        //手机号的正则表达式
    public static boolean checkCellphone(String mobiles) {
        Pattern p = Pattern.compile("^((17[0-9])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
    //身份证的正则表达式
//    public static boolean checkIDcardNumber(String idcard){
//        Pattern p = Pattern.compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\[1-9]))|((1[0-2]))(([0\\[1-9]|1\\d|2\\d])|3[0-1])\\d{3}([0-9]|x|X){1}$");
//        Matcher m = p.matcher(idcard);
//        return m.matches();
//    }
    public static boolean checkIDcardNumber(String idcard){
        Pattern p = Pattern.compile("^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");
        Matcher m = p.matcher(idcard);
        return m.matches();
    }


}
