package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;

import com.bm.library.PhotoView;
import com.linchaolong.android.imagepicker.ImagePicker;
import com.linchaolong.android.imagepicker.cropper.CropImage;
import com.linchaolong.android.imagepicker.cropper.CropImageView;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.bean.location.MyCollectBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.DisplayUtil;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.Utils;

import org.json.JSONObject;

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
    private ImagePicker imagePicker;
    private List<String> upLoadPics;
    private boolean isComingFromColum = false;
    private int ad_id;
    Unbinder unbinder;
    private String mImgUrl;
    private String mSelectImgUrl;

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
        Intent intent = getActivity().getIntent();
        isComingFromColum = intent.getBooleanExtra("isComingFromColum", false);
        if (isComingFromColum) {
            mImgUrl = intent.getStringExtra("imgUrl");
            String link = intent.getStringExtra("link");
            ad_id = intent.getIntExtra("ad_id", 0);

            GlideApp.with(mActivity)
                    .load(mImgUrl)
                    .placeholder(R.mipmap.user_moren)
                    .error(R.mipmap.user_moren)
                    .into(ivMineAddFragment1BigAdver);
            etFragmentBigpicLink.setText(link);
        }
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
                mSelectImgUrl = String.valueOf(imageUri).replace("file://", "");

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
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setAutoZoomEnabled(false)
                        .setMinCropWindowSize(DisplayUtil.dip2px(mActivity, 375), DisplayUtil.dip2px(mActivity, 183))
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

    @Override
    public void commitData() {
        super.commitData();
        if (TextUtils.isEmpty(mSelectImgUrl) && TextUtils.isEmpty(mImgUrl)) {
            ToastUtils.showShortToast(mActivity, "请先添加图片");
        } else {
            OkGo.<String>post(Constant.CHECK_ADD)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            MyCollectBean check = GsonUtil.parseJsonWithGson(response.body(), MyCollectBean.class);
                            if (check.getCode() == 0) {
                                if (TextUtils.isEmpty(mSelectImgUrl)) {
                                    commit();
                                } else {
                                    upLoadPic();
                                }
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            Log.e(TAG, "onSuccess: -----------------------" + response.body());
                        }
                    });
        }
    }

    private void upLoadPic() {
        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(mSelectImgUrl))
                .execute(new DialogCallback<LzyResponse<ImageBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code.equals("0")) {
                            upLoadPics = new ArrayList<>();
                            upLoadPics.add(response.body().data.getFile());
                            commit();
                        }
                    }
                });
    }

    private void commit() {
        if (!TextUtils.isEmpty(mSelectImgUrl)) {
            mImgUrl = Utils.listToString(upLoadPics);
        }
        String link = etFragmentBigpicLink.getText().toString().trim();
        PostRequest<String> post = OkGo.post(isComingFromColum ? Constant.EDIT_AD : Constant.ADD_AD);
        if (isComingFromColum) {
            post.params("id", ad_id);
        }
        post.params("ad_type", 1)
                .params("images", mImgUrl)
                .params("link", link)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseFragment1AdvData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        Log.v("ad_add", response.toString());
                    }
                });
    }

    private void parseFragment1AdvData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            final int code = jsonObject.getInt("code");
            if (code == 0) {
                String link = etFragmentBigpicLink.getText().toString().trim();
                Intent intent = new Intent();
                intent.putExtra("type", 1);
                intent.putExtra("position", getActivity().getIntent().getStringExtra("position"));
                intent.putExtra("url", mImgUrl);
                intent.putExtra("title", link);
                mActivity.setResult(!isComingFromColum ? Activity.RESULT_OK : -2, intent);
                mActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }
}


