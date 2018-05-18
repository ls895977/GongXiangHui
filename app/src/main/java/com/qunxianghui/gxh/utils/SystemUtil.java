package com.qunxianghui.gxh.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.Locale;

/**
 * @author 小强
 * @time 2018/5/18  14:38
 * @desc 获取系统信息
 */
public class SystemUtil {
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return 语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

   /**
     * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
     *
     * @return 手机IMEI
     */
    @SuppressLint("MissingPermission")
    public static String getIMEI(final Activity mContext) {
        String imei = "";
        if (PermissionsUtil.hasPermission(mContext, Manifest.permission.READ_PHONE_STATE)) {
            try {
                //实例化TelephonyManager对象
                TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
                //获取IMEI号
                imei = telephonyManager.getDeviceId();
                if (imei == null) {
                    imei = "";
                }
                return imei;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }

        } else {
            PermissionsUtil.requestPermission(mContext, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                }

                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    Toast.makeText(mContext, "无权限将无法添加", Toast.LENGTH_LONG).show();
                }
            },  Manifest.permission.READ_PHONE_STATE);
        }

        return imei;

    }

}
