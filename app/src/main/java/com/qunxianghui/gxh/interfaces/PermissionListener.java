package com.qunxianghui.gxh.interfaces;

import java.util.List;

/**
 * Created by Administrator on 2018/3/29 0029.
 */

public interface PermissionListener {
    //授权成功
    void onGranted();

    //授权部分
    void onGranted(List<String> grantedPermission);

    //拒绝授权
    void onDenied(List<String> deniedPermission);
}
