package com.qunxianghui.gxh.activity;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.GvAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.BigGridView;

import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends BaseActivity implements GvAdapter.DeletePicListener {

    private EditText et_baoliao_fabu_content;
    private TextView tv_home_baoliao_fabu;
    private ImageView back;
    private BigGridView gridView;
    private ImagePicker imagePicker;
    private List<String> list=new ArrayList<>();
    private GvAdapter adapter;
    private List<String> upLoadPics=new ArrayList<>();

    @Override
    public void deletePic(int position) {
        list.remove(position);
        upLoadPics.remove(position);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initViews() {
        et_baoliao_fabu_content=findViewById(R.id.et_baoliao_fabu_content);
        gridView=findViewById(R.id.grid_view);
        back=findViewById(R.id.back);
        tv_home_baoliao_fabu=findViewById(R.id.tv_home_baoliao_fabu);
    }

    @Override
    protected void initDatas() {
        imagePicker = new ImagePicker();
        //设置标题
        imagePicker.setTitle("设置头像");
        //设置是否剪裁照片
        imagePicker.setCropImage(false);
        adapter = new GvAdapter(this, list);
        adapter.setDeletePicListener(this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //判断是否是最后一个
                if (position == parent.getChildCount() - 1) {
                    if (position == 9) {
                        //不能点击了
                    } else {
                        openPhoto();
                    }
                } else {
                    //可以添加预览功能

                }
            }
        });
    }

    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {
                if (list.size() >= 9) {
                    Toast.makeText(PublishActivity.this, "最多选择九张图片", Toast.LENGTH_LONG).show();
                } else {
                    list.add(String.valueOf(imageUri));
                }
                adapter.notifyDataSetChanged();
                String url=String.valueOf(imageUri).replace("file://","");
                upLoadPic("data:image/jpeg;base64,"+ Utils.imageToBase64(url));
            }
            //剪裁图片回调file:///storage/emulated/0/DCIM/Camera/IMG_20180603_184632_HHT.jpg
            //file:///storage/emulated/0/Android/data/com.qunxianghui.gxh/cache/pickImageResult.jpeg

            @Override
            public void onCropImage(Uri imageUri) {
                if (list.size() >= 9) {
                    Toast.makeText(PublishActivity.this, "最多选择九张图片", Toast.LENGTH_LONG).show();
                } else {
                    list.add(String.valueOf(imageUri));
                }
                adapter.notifyDataSetChanged();
            }


            // 自定义裁剪配置file:///data/user/0/com.qunxianghui.gxh/cache/cropped1992674114.jpg

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape
                                .RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(400, 800)
                        // 宽高比
                        .setAspectRatio(3, 4);
            }

            // 用户拒绝授权回调
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        imagePicker.onActivityResult(PublishActivity.this, requestCode, resultCode, data);
    }

    @Override
    protected void initListeners() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_home_baoliao_fabu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestBaoLiaoFaBu();
            }
        });
    }

    private void upLoadPic(String urls) {

        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64",  urls)
                .execute(new DialogCallback<LzyResponse<ImageBean>>(this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code.equals("0")) {
                            upLoadPics.add(response.body().data.getFile());
                            Toast.makeText(mContext, "上传图片成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void requestBaoLiaoFaBu() {
        String faBuContent=et_baoliao_fabu_content.getText().toString();
        String imgUrl=Utils.listToString(upLoadPics);
        if ( TextUtils.isEmpty(faBuContent)) {
            asyncShowToast("内容不能为空");
        } else {
            OkGo.<String>post(Constant.PUBLISH_ARTICLE)
                    .params("content", faBuContent)
                    .params("images",imgUrl)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            parseBaoLiaoData(response.body());
                        }
                    });
        }


    }

    private void parseBaoLiaoData(String body) {
        asyncShowToast("爆料成功");
        Logger.i("爆料的数据------" + body.toString());
        finish();

    }


}
