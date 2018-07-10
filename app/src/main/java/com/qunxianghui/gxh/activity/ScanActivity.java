package com.qunxianghui.gxh.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.ProtocolActivity;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import butterknife.ButterKnife;

/** 二维码扫描界面
 * Created by Administrator on 2018/3/10 0010.
 */

public class ScanActivity extends BaseActivity {


    private int REQUEST_CODE_SCAN = 111;
    private AlertDialog.Builder builder;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_sacn;
    }

    @Override
    protected void initViews() {
        requestCamera();
    }

    private void requestCamera() {

        if (PermissionsUtil.hasPermission(mContext, Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //有访问摄像头的权限
            Intent intent = new Intent(mContext, CaptureActivity.class);
            /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
             * 也可以不传这个参数
             * 不传的话  默认都为默认不震动  其他都为true
             * */
            ZxingConfig config = new ZxingConfig();
            config.setPlayBeep(true);
            config.setShake(true);
            intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);

            startActivityForResult(intent, REQUEST_CODE_SCAN);
        } else {
            PermissionsUtil.requestPermission(ScanActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permissions) {
                    //用户授予了访问摄像头的权限
                    Intent intent = new Intent(mContext, CaptureActivity.class);
                    /*ZxingConfig是配置类  可以设置是否显示底部布局，闪光灯，相册，是否播放提示音  震动等动能
                     * 也可以不传这个参数
                     * 不传的话  默认都为默认不震动  其他都为true
                     * */
                    ZxingConfig config = new ZxingConfig();
                    config.setPlayBeep(true);
                    config.setShake(true);
                    intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);

                    startActivityForResult(intent, REQUEST_CODE_SCAN);
                }

                @Override
                public void permissionDenied(@NonNull String[] permissions) {
                    //用户拒绝了访问摄像头的申请
                    Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);

                    Toast.makeText(mContext, "没有权限无法扫描呦", Toast.LENGTH_LONG).show();
                }
            }, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE});
        }
    }

    @Override
    protected void initDatas() {
//                    new TitleBuilder(ScanActivity.this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            finish();
//                        }
//                    }).setRightIco(R.mipmap.icon_share).setRightIcoListening(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(mContext, "分享界面", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onResume() {

        super.onResume();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data == null) {
                return;
            } else {
                final String content = data.getStringExtra(Constant.CODED_CONTENT);
//                result.setText("扫描结果为：" + content);
                builder = new AlertDialog.Builder(ScanActivity.this);

                builder.setTitle("跳转提示");
                builder.setMessage("您确定要跳转此链接么？");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext, ProtocolActivity.class);
                        intent.putExtra("url", content);
                        startActivity(intent);
                        finish();

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                builder.show();
            }


        }
        finish();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
