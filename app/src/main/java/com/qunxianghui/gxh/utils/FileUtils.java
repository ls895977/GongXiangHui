package com.qunxianghui.gxh.utils;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {

    public static String addSlash(String pPath) {
        if (!TextUtils.isEmpty(pPath)) {
            if (!pPath.endsWith(File.separator)) {
                pPath = pPath + File.separator;
            }
        }else {
            pPath = File.separator;
        }
        return pPath;
    }

    public static boolean isExist(String pFilePath) {
        try {
            File file = new File(pFilePath);
            return file.exists();
        } catch (Exception e) {
            return false;
        }
    }

    @NonNull
    public static File createTempImageFile() throws IOException {
        String imageFileName = "img_" + formatDate(System.currentTimeMillis())+ "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    /**
     * 格式成 yyyy-MM-dd HH:mm:ss
     *
     * @param pLongDate milliseconds
     * @return
     */
    public static String formatDate(long pLongDate) {
        String dateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());
        String resultString = "";
        try {
            resultString = simpleDateFormat.format(new Date(pLongDate));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultString;
    }
}
