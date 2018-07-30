package com.qunxianghui.gxh.ui.fragments.locationFragment.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.utils.PhotoUtil;
import com.qunxianghui.gxh.utils.UriPathUtils;
import com.qunxianghui.gxh.widget.ActionSheetCommonDialog;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/21 0021.
 */

public class PublishStateActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_publish_content)
    EditText etPublishContent;
    @BindView(R.id.iv_publish_image1)
    ImageView ivPublishImage1;
    @BindView(R.id.iv_publish_image2)
    ImageView ivPublishImage2;
    @BindView(R.id.iv_publish_image3)
    ImageView ivPublishImage3;
    @BindView(R.id.iv_publish_image4)
    ImageView ivPublishImage4;
    @BindView(R.id.iv_publish_addImage)
    ImageView ivPublishAddImage;
    @BindView(R.id.iv_publish_back)
    ImageView ivPublishBack;
    @BindView(R.id.iv_publish_fabu)
    TextView ivPublishFabu;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_publish;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initListeners() {
        ivPublishBack.setOnClickListener(this);
        ivPublishAddImage.setOnClickListener(this);

        ivPublishFabu.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_publish_addImage:
                options();
                break;
        }
    }

    private String path;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == PhotoUtil.NONE) {
            return;
        }
// 拍照
        if (requestCode == PhotoUtil.PHOTOGRAPH) {
            // 设置文件保存路径这里放在跟文件夹下
            File picture = null;
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(Environment.getExternalStorageDirectory() + PhotoUtil.imageName);
                }
            } else {
                picture = new File(this.getFilesDir() + PhotoUtil.imageName);
                if (!picture.exists()) {
                    picture = new File(PublishStateActivity.this.getFilesDir() + PhotoUtil.imageName);
                }
            }
            path = PhotoUtil.getPath(this);// 生成一个地址用于存放剪辑后的图片
            if (TextUtils.isEmpty(path)) {
                Log.e(TAG, "随机生成的用于存放剪辑后的图片的地址失败");
                return;
            }

            Uri imageUri = UriPathUtils.getUri(this, path);
            PhotoUtil.startPhotoZoom(PublishStateActivity.this, Uri.fromFile(picture), PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH, imageUri);
        }
        if (data == null) {
            return;
        }
        // 读取相冊缩放图片

        if (requestCode == PhotoUtil.PHOTOZOOM) {

            path = PhotoUtil.getPath(this);// 生成一个地址用于存放剪辑后的图片
            if (TextUtils.isEmpty(path)) {
                Log.e(TAG, "随机生成的用于存放剪辑后的图片的地址失败");
                return;
            }
            Uri imageUri = UriPathUtils.getUri(this, path);
            PhotoUtil.startPhotoZoom(PublishStateActivity.this, data.getData(), PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH, imageUri);
        }
        // 处理结果
        if (requestCode == PhotoUtil.PHOTORESOULT) {
            /**
             * 在这里处理剪辑结果。能够获取缩略图，获取剪辑图片的地址。得到这些信息能够选则用于上传图片等等操作
             * */

//            /**
//             * 如。依据path获取剪辑后的图片
//             */
//            Bitmap bitmap = PhotoUtil.convertToBitmap(path, PhotoUtil.PICTURE_HEIGHT, PhotoUtil.PICTURE_WIDTH);
//            if (bitmap != null) {
//                tv2.setText(bitmap.getHeight() + "x" + bitmap.getWidth() + "图");
//                clipImg.setImageBitmap(bitmap);
//            }

            Bitmap bitmap2 = PhotoUtil.convertToBitmap(path, 200, 200);
            if (bitmap2 != null) {

                ivPublishImage1.setImageBitmap(bitmap2);
            }


        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private void options() {
        ActionSheetCommonDialog mDialog = new ActionSheetCommonDialog(this).builder();
        mDialog.setTitle("选择");
        mDialog.setCancelable(false);
        mDialog.addSheetItem("拍照", ActionSheetCommonDialog.SheetItemColor.Blue, new ActionSheetCommonDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                PhotoUtil.photograph(PublishStateActivity.this);
            }
        }).addSheetItem("从相冊选取", ActionSheetCommonDialog.SheetItemColor.Blue, new ActionSheetCommonDialog.OnSheetItemClickListener() {
            @Override
            public void onClick(int which) {
                PhotoUtil.selectPictureFromAlbum(PublishStateActivity.this);
            }
        }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
