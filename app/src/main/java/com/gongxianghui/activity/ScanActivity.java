package com.gongxianghui.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import kr.co.namee.permissiongen.PermissionGen;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class ScanActivity extends BaseActivity implements EasyPermissions.PermissionCallbacks, QRCodeView.Delegate {
    private static final String TAG = "ScanActivity";
    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;
    private long clickTime = 0; //记录第一次点击的时间
    @BindView(R.id.zxingview)
    ZXingView zxingview;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sacn;
    }



    @Override
    protected void initViews() {
        zxingview.setDelegate(this);

        PermissionGen.with(ScanActivity.this)
                .addRequestCode(105)
                .permissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .request();


    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();
        zxingview.startCamera();
        zxingview.startSpot();
    }
    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA};
                if (!EasyPermissions.hasPermissions(this, perms)) {
                         EasyPermissions.requestPermissions(this, getResources().getString(R.string.qrcode_permission), REQUEST_CODE_QRCODE_PERMISSIONS, perms);
                     }
    }

    @Override
    protected void onResume() {
        zxingview.showScanRect();
        super.onResume();
    }

    @Override
    protected void onStop() {
        zxingview.stopCamera();
        super.onStop();
    }
    @Override
    public void onScanQRCodeSuccess(String result) {
        Toast.makeText(this, "相机打开成功", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onScanQRCodeOpenCameraError() {
        Toast.makeText(this, "打开相机出错", Toast.LENGTH_SHORT).show();
        Log.e(TAG, "打开相机出错");
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if(keyCode==KeyEvent.KEYCODE_BACK){
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);


    }

    private void exit() {
        if ((System.currentTimeMillis() - clickTime) > 2000) {
                         Toast.makeText(getApplicationContext(), getResources().getString(R.string.exit_message),
                                       Toast.LENGTH_SHORT).show();
                         clickTime = System.currentTimeMillis();
                   } else {
                        Log.e(TAG, "退出应用");
                        this.finish();
                     }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
