package com.gongxianghui.interfaces;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public interface Delegate { //扫码成功返回的结果是result，打开相机失败的回调
    /**
     * 处理扫描结果
     * @param result
     */
    void onScanQRCodeSuccess(String result);

    /**
     * 处理打开相机出错
     */

    void onScanQRCodeOpenCameraError();
}
