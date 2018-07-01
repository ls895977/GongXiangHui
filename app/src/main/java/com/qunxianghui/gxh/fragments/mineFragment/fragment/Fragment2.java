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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class Fragment2 extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_address)
    EditText mEtAddress;
    @BindView(R.id.pv_add_image)
    PhotoView mPvAddImage;

    private ImagePicker imagePicker;
    private boolean isComingFromColum = false;
    private int ad_id;
    private String mImgUrl;
    private String mSelectImgUrl;
    private String mName;
    private String mMobile;
    private String mAddress;

    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_advertise2;
    }

    @Override
    public void initDatas() {
        Intent intent = getActivity().getIntent();
        isComingFromColum = intent.getBooleanExtra("isComingFromColum", false);
        if (isComingFromColum) {
            mImgUrl = intent.getStringExtra("imageUrl");
            ad_id = intent.getIntExtra("ad_id", 0);

            GlideApp.with(mActivity)
                    .load(mImgUrl)
                    .placeholder(R.mipmap.user_moren)
                    .error(R.mipmap.user_moren)
                    .into(mPvAddImage);
            mEtName.setText(intent.getStringExtra("name"));
            mEtPhone.setText(intent.getStringExtra("mobile"));
            mEtAddress.setText(intent.getStringExtra("address"));
        }
        imagePicker = new ImagePicker();
        imagePicker.setCropImage(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void commitData() {
        super.commitData();
        mName = mEtName.getText().toString().trim();
        mMobile = mEtPhone.getText().toString();
        mAddress = mEtAddress.getText().toString();
        if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mMobile) || TextUtils.isEmpty(mAddress) || TextUtils.isEmpty(mSelectImgUrl) && TextUtils.isEmpty(mImgUrl)) {
            asyncShowToast("请先完善信息");
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
                            } else {
                                asyncShowToast(check.getMessage());
                            }
                        }
                    });
        }
    }

    private void commit() {
        PostRequest<String> post = OkGo.post(isComingFromColum ? Constant.EDIT_AD : Constant.ADD_AD);
        if (isComingFromColum) {
            post.params("id", ad_id);
        }
        post.params("ad_type", 2)
                .params("images", mImgUrl)
                .params("settings[name]", mName)
                .params("settings[mobile]", mMobile)
                .params("settings[address]", mAddress)
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

    private void upLoadPic() {
        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(mSelectImgUrl))
                .execute(new DialogCallback<LzyResponse<ImageBean>>(mActivity) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code.equals("0")) {
                            mImgUrl = response.body().data.getFile();
                            commit();
                        }
                    }
                });
    }

    private void parseFragment1AdvData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            final int code = jsonObject.getInt("code");
            if (code == 0) {
                Intent intent = new Intent();
                intent.putExtra("imageUrl", mImgUrl);
                intent.putExtra("name", mName);
                intent.putExtra("mobile", mMobile);
                intent.putExtra("address", mAddress);
                mActivity.setResult(!isComingFromColum ? Activity.RESULT_OK : -2, intent);
                mActivity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.pv_add_image)
    public void onViewClicked() {
        openPhoto();
    }

    private void openPhoto() {
        imagePicker.startChooser(this, new ImagePicker.Callback() {
            @Override
            public void onPickImage(Uri imageUri) {
            }

            @Override
            public void onCropImage(Uri imageUri) {
                mSelectImgUrl = String.valueOf(imageUri).replace("file://", "");
                GlideApp.with(mActivity)
                        .load(imageUri)
                        .placeholder(R.mipmap.user_moren)
                        .error(R.mipmap.user_moren)
                        .into(mPvAddImage);
            }

            //自定义剪裁

            @Override
            public void cropConfig(CropImage.ActivityBuilder builder) {
                builder
                        .setMultiTouchEnabled(false)
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setCropShape(CropImageView.CropShape.RECTANGLE)
                        .setAutoZoomEnabled(false)
                        .setMinCropWindowSize(DisplayUtil.dip2px(mActivity, 80), DisplayUtil.dip2px(mActivity, 80))
                        .setRequestedSize(DisplayUtil.dip2px(mActivity, 80), DisplayUtil.dip2px(mActivity, 80))
                        .setAspectRatio(1, 1);
            }

            //用户拒绝授权回调

            @Override
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
