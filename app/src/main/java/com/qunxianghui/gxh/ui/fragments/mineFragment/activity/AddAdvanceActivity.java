package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;

public class AddAdvanceActivity extends BaseActivity {

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
    @BindView(R.id.ll_load)
    LinearLayout mLlLoad;

    private int mId;
    private String mPath;
    private Dialog mSelectPhoto;
    private ArrayList<ImageItem> mImages;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_advance;
    }

    @Override
    protected void initViews() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(1);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(500);
        imagePicker.setOutPutY(500);

        mImages = new ArrayList<>();
        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent = new Intent(AddAdvanceActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                ImagePicker.getInstance().setSelectLimit(1);
                Intent intent1 = new Intent(AddAdvanceActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        AddAdvanceBean.DataBean info = (AddAdvanceBean.DataBean) intent.getSerializableExtra("info");
        if (info != null) {
            mTvAddAdvanceSave.setVisibility(View.GONE);
            rlAddAdvanceEdit.setVisibility(View.VISIBLE);
            mId = info.getAboutus_id();
            mEtAddAdvanceTitle.setText(info.getTitle());
            mEtAddAdvanceIntroduce.setText(info.getDescribe());
            mPath = info.getImage_array().get(0);
            Glide.with(mContext).load(mPath).apply(new RequestOptions()
                    .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(mIvAddAdvancePic);
        }
    }

    @OnClick({R.id.iv_add_advance_back, R.id.tv_add_advance_save, R.id.iv_add_advance_pic, R.id.tv_add_advance_delete, R.id.tv_add_advance_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_advance_back:
                finish();
                break;
            case R.id.tv_add_advance_save:
                saveAdvanceData();
                break;
            case R.id.iv_add_advance_pic:
                mSelectPhoto.show();
                break;
            case R.id.tv_add_advance_delete:
                deleteCompanyCardAdavance();
                break;
            case R.id.tv_add_advance_complete:
                String title = mEtAddAdvanceTitle.getText().toString().trim();
                String des = mEtAddAdvanceIntroduce.getText().toString().trim();
                if (TextUtils.isEmpty(mPath) || TextUtils.isEmpty(title) || TextUtils.isEmpty(des)) {
                    asyncShowToast("请检查一下,还有哪里没有填写");
                } else {
                    if (!mPath.startsWith("http")) {
                        upLoadPic();
                        return;
                    }
                    editCompanyCardAdvance();
                }
                break;
        }
    }

    /*保存成功*/
    private void saveAdvanceData() {
        String title = mEtAddAdvanceTitle.getText().toString().trim();
        String des = mEtAddAdvanceIntroduce.getText().toString().trim();
        if (TextUtils.isEmpty(mPath) || TextUtils.isEmpty(title) || TextUtils.isEmpty(des)) {
            asyncShowToast("标题或者简介为空,请检查一下");
        } else {
            upLoadPic();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<ImageItem> images;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            ImagePicker.getInstance().clear();
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null && !images.isEmpty()) {
                    mImages.clear();
                    mImages.addAll(images);
                    mPath = mImages.get(0).path;
                    Glide.with(mContext).load(mPath).apply(new RequestOptions().placeholder(R.mipmap.default_img)
                            .error(R.mipmap.default_img).centerCrop()).into(mIvAddAdvancePic);
                }
            }
        }
    }

    private void upLoadPic() {
        mLlLoad.setVisibility(View.VISIBLE);
        OkGo.<CommonResponse<ImageBean>>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(mPath))
                .execute(new DialogCallback<CommonResponse<ImageBean>>(this) {
                    @Override
                    public void onSuccess(Response<CommonResponse<ImageBean>> response) {
                        if (response.body().code == 0) {
                            mPath = response.body().data.getFile();
                            if (mId == 0) {
                                saveAdvance();
                            } else {
                                editCompanyCardAdvance();
                            }
                        }
                    }
                });
    }

    /*修改企业核心优势*/
    private void editCompanyCardAdvance() {
        OkGo.<CommonBean>post(Constant.EDIT_COMPANY_CENTER_ADVANCE).
                params("title", mEtAddAdvanceTitle.getText().toString().trim()).
                params("aboutus_id", mId).
                params("describe", mEtAddAdvanceIntroduce.getText().toString().trim()).
                params("image", mPath).
                params("datatype", 1).
                execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        mLlLoad.setVisibility(View.GONE);
                        int code = response.body().code;
                        if (code == 200) {
                            asyncShowToast("修改成功");
                            setResult(0x0022);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        mLlLoad.setVisibility(View.GONE);
                    }
                });
    }

    /*删除企业核心优势*/
    private void deleteCompanyCardAdavance() {
        OkGo.<CommonBean>post(Constant.DELETE_COMPANY_CENTER_ADVANCE).
                params("aboutus_id", mId)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 200) {
                            asyncShowToast("删除成功");
                            setResult(0x0022);
                            finish();
                        } else {
                            asyncShowToast("删除失败" + response.message());
                        }
                    }
                });
    }

    private void saveAdvance() {
        OkGo.<CommonBean>post(Constant.ADD_COMPANY_CENTER_ADVANCE).
                params("title", mEtAddAdvanceTitle.getText().toString().trim()).
                params("describe", mEtAddAdvanceIntroduce.getText().toString().trim()).
                params("image", mPath).
                params("datatype", 1).
                execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        mLlLoad.setVisibility(View.GONE);
                        int code = response.body().code;
                        if (code == 200) {
                            asyncShowToast("保存成功");
                            setResult(0x0022);
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        mLlLoad.setVisibility(View.GONE);
                        Logger.e("添加企业优势失败了" + response.message());
                    }
                });
    }
}
