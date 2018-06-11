package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bm.library.PhotoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.DisplayUtil;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.ImageUtils;
import com.qunxianghui.gxh.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment1 extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.iv_mine_addFragment1BigAdver)
    PhotoView ivMineAddFragment1BigAdver;
    @BindView(R.id.et_fragment_bigpic_link)
    EditText etFragmentBigpicLink;


    Unbinder unbinder;


    private ImagePicker imagePicker;
    private List<String> upLoadPics = new ArrayList<>();

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise1;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
        imagePicker = new ImagePicker();
        imagePicker.setCropImage(true);


    }

    @Override
    protected void initListeners() {


        ivMineAddFragment1BigAdver.setOnClickListener(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.iv_mine_addFragment1BigAdver:
                openPhoto();

                break;
        }

    }

    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {
//                ivMineAddFragment1BigAdver.setImageURI(imageUri);
//                ivMineAddFragment1BigAdver.enable();


//        Glide.with(this)
//                .load(gif)
//                .crossFade()
//                .placeholder(R.mipmap.bbb)
//                .into(((PhotoView) findViewById(R.id.img1)));
            }

            //剪裁图片回调
            @Override
            public void onCropImage(Uri imageUri) {

                final String url = String.valueOf(imageUri).replace("file://", "");
                upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(url));

//                ivMineAddFragment1BigAdver.setImageURI(imageUri);
                //      使用Glide加载的gif图片同样支持缩放功能
                GlideApp.with(mActivity)
                        .load(imageUri)

                        .placeholder(R.mipmap.user_moren)
                        .error(R.mipmap.user_moren)
                        .into(ivMineAddFragment1BigAdver);
            }

            //自定义剪裁

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        // 是否启动多点触摸
                        .setMultiTouchEnabled(false)
                        // 设置网格显示模式
                        .setGuidelines(CropImageView.Guidelines.ON)
                        // 圆形/矩形
                        .setCropShape(CropImageView.CropShape
                                .RECTANGLE)
                        // 调整裁剪后的图片最终大小
                        .setRequestedSize(DisplayUtil.dip2px(mActivity, 375), DisplayUtil.dip2px(mActivity, 183))
                        // 宽高比
                        .setAspectRatio(2, 1);
            }

            //用户拒绝授权回调

            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
            }
        });

    }

    private void upLoadPic(String s) {
        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", s)
                .execute(new DialogCallback<LzyResponse<ImageBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code.equals("0")) {
                            upLoadPics.add(response.body().data.getFile());
                            Toast.makeText(mActivity, "上传图片成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void commitData() {
        super.commitData();
        if (upLoadPics.size() == 0) {
            ToastUtils.showShortToast(mActivity, "请先上传图片");
        } else {

            //
            OkGo.<LzyResponse<String>>post(Constant.CHECK_ADD)
                    .execute(new DialogCallback<LzyResponse<String>>(mActivity) {
                        @Override
                        public void onSuccess(Response<LzyResponse<String>> response) {
                            if (response.body().code.equals("0")) {
                                commit();
                            } else {
                                ToastUtils.showShortToast(mActivity, response.body().message);
                            }
                        }
                    });
        }
    }

    private void commit() {
        String imagUrl = Utils.listToString(upLoadPics);
        String trim = etFragmentBigpicLink.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            ToastUtils.showShortToast(mActivity, "链接不能为空");
            return;
        }
        OkGo.<LzyResponse<String>>post(Constant.ADD_AD)
                .params("ad_type", 1)
                .params("images", imagUrl)
                .params("link", trim)
                .execute(new DialogCallback<LzyResponse<String>>(mActivity) {
                    @Override
                    public void onSuccess(Response<LzyResponse<String>> response) {
                        if (response.body().code.equals("0")) {
                            Intent intent = new Intent();
                            intent.putExtra("index", 0);
                            mActivity.setResult(Activity.RESULT_OK, intent);
                            mActivity.finish();
                        }
                    }
                });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }
}


