package com.gongxianghui.interfaces;

import android.support.v4.app.ActivityCompat;

import java.util.List;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public interface PermissionCallbacks extends ActivityCompat.OnRequestPermissionsResultCallback {
    /**
     * 授权成功失败回调
     */

    void onPermissionsGranted(int requestCode, List<String> perms);

    void onPermissionsDenied(int requestCode, List<String> perms);

}
