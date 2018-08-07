package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.RxPermissions;
import com.luck.picture.lib.tools.PictureFileUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class AddAdvanceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_add_advance_back)
    ImageView mIvAddAdvanceBack;
    @BindView(R.id.tv_add_advance_save)
    TextView mTvAddAdvanceSave;
    @BindView(R.id.et_add_advance_title)
    EditText mEtAddAdvanceTitle;
    @BindView(R.id.et_add_advance_introduce)
    TextView mEtAddAdvanceIntroduce;
    @BindView(R.id.iv_add_advance_pic)
    ImageView mIvAddAdvancePic;
    @BindView(R.id.rl_add_advance_edit)
    RelativeLayout rlAddAdvanceEdit;
    private String mPath;
    private List<String> upLoadPics = new ArrayList<>();
    private String mTitle;
    private String mDescribe;
    private String[] mImage_arrays;
    private int mViewTag;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_advance;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        mTitle = intent.getStringExtra("title");
        mViewTag = intent.getIntExtra("viewTag", 0);
        mDescribe = intent.getStringExtra("describe");
        mImage_arrays = intent.getStringArrayExtra("image_array");
    }

    @Override
    protected void initData() {
        mEtAddAdvanceTitle.setText(mTitle);
        mEtAddAdvanceIntroduce.setText(mDescribe);

        if (mViewTag == 1) {
            mTvAddAdvanceSave.setVisibility(View.GONE);
            rlAddAdvanceEdit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FetchAddAdvanceData();
    }

    /*获取传进来的数据*/
    private void FetchAddAdvanceData() {


    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mIvAddAdvanceBack.setOnClickListener(this);
        mTvAddAdvanceSave.setOnClickListener(this);
        mIvAddAdvancePic.setOnClickListener(this);
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
            case R.id.iv_add_advance_back:
                finish();
                break;
            case R.id.tv_add_advance_save:
                asyncShowToast("保存");
                saveAdvanceData();
                break;
            case R.id.iv_add_advance_pic:
                openAdvancePhoto();
                break;

        }
    }

    private void saveAdvanceData() {
        String imageUrl = Utils.listToString(upLoadPics);
        String mAddAdvanceTitle = mEtAddAdvanceTitle.getText().toString().trim();
        String mAddAdvanceIntroduce = mEtAddAdvanceIntroduce.getText().toString().trim();
        if (TextUtils.isEmpty(mAddAdvanceTitle) || TextUtils.isEmpty(mAddAdvanceIntroduce)) {
            asyncShowToast("标题或者简介为空,请检查一下");
        } else {
            RequestAddAdvanceData(mAddAdvanceTitle, mAddAdvanceIntroduce, imageUrl);
        }
    }

    private void RequestAddAdvanceData(String mAddAdvanceTitle, String mAddAdvanceIntroduce, String imageUrl) {
        OkGo.<String>post(Constant.ADD_COMPANY_CENTER_ADVANCE).
                params("title", mAddAdvanceTitle).
                params("describe", mAddAdvanceIntroduce).
                params("image", imageUrl).
                params("datatype", "1").
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                asyncShowToast("保存成功");
                                finish();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        com.orhanobut.logger.Logger.e("添加企业优势失败了" + response.message());
                    }
                });
    }

    private void openAdvancePhoto() {
        PictureSelector.create(AddAdvanceActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .previewImage(true)
                .isCamera(true)// 是否显示拍照按钮
                .enableCrop(true)// 是否裁剪 true or false
                .compress(true)// 是否压缩 true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .forResult(PictureConfig.CHOOSE_REQUEST);

        // 清空图片缓存，包括裁剪、压缩后的图片 注意:必须要在上传完成后调用 必须要获取权限
        RxPermissions permissions = new RxPermissions(this);
        permissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Observer<Boolean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(Boolean aBoolean) {
                if (aBoolean) {
                    PictureFileUtils.deleteCacheDirFile(AddAdvanceActivity.this);
                } else {
                    Toast.makeText(AddAdvanceActivity.this,
                            getString(R.string.picture_jurisdiction), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                // 图片、视频、音频选择结果回调
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                mPath = selectList.get(0).getPath();
                FetchAdvancePic(mPath);
                break;
        }
    }

    private void FetchAdvancePic(String path) {
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(path).apply(options).into(mIvAddAdvancePic);
        upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(path));
    }

    private void upLoadPic(String urls) {
        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", urls)
                .execute(new DialogCallback<LzyResponse<ImageBean>>(this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code == 0) {
                            upLoadPics.add(response.body().data.getFile());
                            Toast.makeText(mContext, "上传图片成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
