package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.BigGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class BaoLiaoActivity extends BaseActivity implements View.OnClickListener, GvAdapter.DeletePicListener {
    @BindView(R.id.iv_baoliao_close)
    ImageView ivBaoliaoClose;

    @BindView(R.id.grid_view)
    BigGridView gridView;
    @BindView(R.id.ll_baoliao_remember)
    LinearLayout llBaoliaoRemember;
    @BindView(R.id.tv_home_baoliao_fabu)
    TextView tvHomeBaoliaoFabu;
    @BindView(R.id.et_baoliao_fabu_content)
    EditText etBaoliaoFabuContent;
    @BindView(R.id.et_baoliao_fabu_title)
    EditText etBaoliaoFabuTitle;
    @BindView(R.id.iv_baoliao_back)
    ImageView ivBaoliaoBack;
    private ImagePicker imagePicker;
    private List<String> list;
    private GvAdapter adapter;
    private List<String> upLoadPics = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baoliao;
    }

    @Override
    protected void initViews() {
        imagePicker = new ImagePicker();
        //设置标题
        imagePicker.setTitle("设置头像");
        //设置是否剪裁照片
        imagePicker.setCropImage(false);
        list = new ArrayList<>();
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
                    Toast.makeText(BaoLiaoActivity.this, "最多选择九张图片", Toast.LENGTH_LONG).show();
                } else {
                    list.add(String.valueOf(imageUri));
                }
                adapter.notifyDataSetChanged();
                String url = String.valueOf(imageUri).replace("file://", "");
                upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(url));
            }
            //剪裁图片回调file:///storage/emulated/0/DCIM/Camera/IMG_20180603_184632_HHT.jpg
            //file:///storage/emulated/0/Android/data/com.qunxianghui.gxh/cache/pickImageResult.jpeg

            @Override
            public void onCropImage(Uri imageUri) {
                if (list.size() >= 9) {
                    Toast.makeText(BaoLiaoActivity.this, "最多选择九张图片", Toast.LENGTH_LONG).show();
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
        imagePicker.onActivityResult(BaoLiaoActivity.this, requestCode, resultCode, data);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListeners() {
        ivBaoliaoClose.setOnClickListener(this);
        tvHomeBaoliaoFabu.setOnClickListener(this);
        ivBaoliaoBack.setOnClickListener(this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    public void onClick(View v) {
        final String faBuContent = etBaoliaoFabuContent.getText().toString().trim();
        final String faBuTitle = etBaoliaoFabuTitle.getText().toString().trim();
        switch (v.getId()) {
            case R.id.iv_baoliao_close:
                llBaoliaoRemember.setVisibility(View.GONE);
                break;
            case R.id.tv_home_baoliao_fabu:
                requestBaoLiaoFaBu(faBuTitle, faBuContent);
                break;
            case R.id.iv_baoliao_back:
                finish();
                break;
        }
    }

    private void upLoadPic(String urls) {

        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", urls)
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

    private void requestBaoLiaoFaBu(String faBuTitle, String faBuContent) {
        String imgUrl = Utils.listToString(upLoadPics);
        if (TextUtils.isEmpty(faBuContent) && TextUtils.isEmpty(faBuContent)) {
            asyncShowToast("标题和内容不能为空");
        } else {
            OkGo.<String>post(Constant.HOME_DISCLOSS_URL)
                    .params("title", faBuTitle)
                    .params("content", faBuContent)
                    .params("images", imgUrl)
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

    @Override
    public void deletePic(int position) {
        list.remove(position);
        upLoadPics.remove(position);
        adapter.notifyDataSetChanged();
    }
}
