package com.qunxianghui.gxh.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SavePicByUrlUtils {

    private static HttpURLConnection urlConnection;
    public static boolean sIsSaving;

    public static void getBitmap(Context context, String imageUrl) {
        try {
            URL url1 = new URL(imageUrl);
            if(imageUrl.endsWith(".gif")){
                getImageGif(imageUrl,Environment.getExternalStorageDirectory()+"/dsh/"+System.currentTimeMillis() + ".gif",context);
            }else {
                urlConnection = (HttpURLConnection) url1.openConnection();
                urlConnection.setReadTimeout(5000);
                urlConnection.setConnectTimeout(5000);
                urlConnection.setRequestMethod("GET");

                if (urlConnection.getResponseCode() == 200) {
                    sIsSaving = true;
                    InputStream inputStream = urlConnection.getInputStream();
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //将图片保存到本地
                    savePic2Phone(context, bitmap);
                    bitmap.recycle();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            urlConnection.disconnect();
//        }
    }

    public static void savePic2Phone(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "dsh");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            sIsSaving = false;
        } catch (IOException e) {
            e.printStackTrace();
            sIsSaving = false;
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ToastUtils.showShort("图片保存成功");
    }

    //path为下载路径,saveName是保存名称可以是任何文件
    public static void getImageGif(String path, String saveName,Context context) throws Exception {
        URL url = new URL(path);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(1000 * 6);
        if (con.getResponseCode() == 200) {
            InputStream inputStream = con.getInputStream();
            byte[] b = getByte(inputStream);
            File file = new File(saveName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(b);
            fileOutputStream.close();

            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), saveName, null);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            ToastUtils.showShort("Gif图片保存成功");
        }
    }

    private static byte[] getByte(InputStream inputStream) throws Exception {
        byte[] b = new byte[1024];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int len = -1;
        while ((len = inputStream.read(b)) != -1) {
            byteArrayOutputStream.write(b, 0, len);
        }
        byteArrayOutputStream.close();
        inputStream.close();
        return byteArrayOutputStream.toByteArray();
    }
}
