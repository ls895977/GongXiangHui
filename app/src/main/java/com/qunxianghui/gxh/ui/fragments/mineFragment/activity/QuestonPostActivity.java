package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePicker2Adapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_PREVIEW;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;

public class QuestonPostActivity extends BaseActivity implements ImagePicker2Adapter.OnRecyclerViewItemClickListener, View.OnClickListener {
    @BindView(R.id.tv_question_exit)
    CheckBox tvQuestionExit;
    @BindView(R.id.tv_question_warn_text_error)
    CheckBox tvQuestionWarnTextError;
    @BindView(R.id.rv_setting_question)
    RecyclerView rvSettingQuestion;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.tv_setting_quest_commit)
    TextView tvSettingQuestCommit;
    @BindView(R.id.tv_question_post_back)
    ImageView tvQuestionPostBack;
    @BindView(R.id.tv_question_adver_add_error)
    CheckBox tvQuestionAdverAddError;
    @BindView(R.id.tv_question_fun_notuse_error)
    CheckBox tvQuestionFunNotuseError;
    @BindView(R.id.tv_question_accunt_login_error)
    CheckBox tvQuestionAccuntLoginError;
    @BindView(R.id.tv_question_other_error)
    CheckBox tvQuestionOtherError;
    @BindView(R.id.et_setting_questiontioncontent)
    EditText etSettingQuestiontioncontent;
    @BindView(R.id.ll_load)
    LinearLayout mLlLoad;
    @BindView(R.id.tv_question_adver_siji)
    CheckBox tvQuestionAdverSiji;
    private ArrayList<ImageItem> mImages; //当前选择的所有图片
    private ImagePicker2Adapter mAdapter;
    private Dialog mSelectPhoto;
    private List<CheckBox> checkBoxList = new ArrayList<CheckBox>();
    private String mQuestionWarnTextError;
    private String mQuestionAdverAddError;
    private String mQuestionFunNotuseError;
    private String mQuestionAccuntLoginError;
    private String mQuestionOtherError;
    private String mQuestionExit;
    private String mQuestionPostContent;
    private int mIndex;
    private boolean mIsUploadIng;
    private StringBuilder mSb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_question_post;
    }

    @Override
    protected void initViews() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(3);
        imagePicker.setMultiMode(true);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(500);
        imagePicker.setOutPutY(500);

        mImages = new ArrayList<>();
        mAdapter = new ImagePicker2Adapter(this, mImages, 3);
        mAdapter.setOnItemClickListener(this);
        rvSettingQuestion.setAdapter(mAdapter);

        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent = new Intent(QuestonPostActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent1 = new Intent(QuestonPostActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    @Override
    protected void initData() {
        checkBoxList.add(tvQuestionExit);
        checkBoxList.add(tvQuestionWarnTextError);
        checkBoxList.add(tvQuestionAdverAddError);
        checkBoxList.add(tvQuestionAdverSiji);
        checkBoxList.add(tvQuestionFunNotuseError);
        checkBoxList.add(tvQuestionAccuntLoginError);
        checkBoxList.add(tvQuestionOtherError);
        mSb = new StringBuilder();
    }

    @Override
    protected void initListeners() {
        tvQuestionPostBack.setOnClickListener(this);
        tvQuestionExit.setOnClickListener(this);
        tvQuestionWarnTextError.setOnClickListener(this);
        tvSettingQuestCommit.setOnClickListener(this);
        tvQuestionAdverAddError.setOnClickListener(this);
        tvQuestionFunNotuseError.setOnClickListener(this);
        tvQuestionAccuntLoginError.setOnClickListener(this);
        tvQuestionOtherError.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                mSelectPhoto.show();
                break;
            default:
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) mAdapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_question_post_back:
                finish();
                break;
            case R.id.tv_setting_quest_commit:
                CommitQuestionData();
                break;
            case R.id.tv_question_exit:
                mQuestionExit = tvQuestionExit.getText().toString().trim();
                break;
            case R.id.tv_question_warn_text_error:
                mQuestionWarnTextError = tvQuestionWarnTextError.getText().toString().trim();
                break;
            case R.id.tv_question_adver_add_error:
                mQuestionAdverAddError = tvQuestionAdverAddError.getText().toString().trim();
                break;
            case R.id.tv_question_fun_notuse_error:
                mQuestionFunNotuseError = tvQuestionFunNotuseError.getText().toString().trim();
                break;
            case R.id.tv_question_accunt_login_error:
                mQuestionAccuntLoginError = tvQuestionAccuntLoginError.getText().toString().trim();
                break;
            case R.id.tv_question_other_error:
                mQuestionOtherError = tvQuestionOtherError.getText().toString().trim();
                break;
        }
    }

    /*提交意见反馈数据*/
    private void CommitQuestionData() {
        mQuestionPostContent = etSettingQuestiontioncontent.getText().toString().trim();
        StringBuffer sb = new StringBuffer();
        //遍历checkbox  判断是否选择  获取选中的文本
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                sb.append(checkBox.getText().toString() + "");
            }
        }
        if (sb != null && "".equals(sb.toString())) {
            asyncShowToast("请至少选择一个");
        } else if (TextUtils.isEmpty(mQuestionPostContent)) {
            asyncShowToast("描述内容不能为空");
        } else if (mImages.isEmpty()) {
            asyncShowToast("图片不能为空");
        } else {
            mIsUploadIng = true;
            mLlLoad.setVisibility(View.VISIBLE);
            upLoadPic(mAdapter.getImages().get(0).path, sb.toString(), mQuestionPostContent);

        }
    }

    /*上传图片*/
    private void upLoadPic(String urls, final String questiponTitle, final String questionContent) {
        mIndex++;
        if (urls.startsWith("http")) {
            next(urls, questiponTitle, questionContent);
            return;
        }
        OkGo.<UploadImage>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(urls))
                .execute(new JsonCallback<UploadImage>() {
                    @Override
                    public void onSuccess(Response<UploadImage> response) {
                        UploadImage uploadImage = response.body();
                        if ("0".equals(uploadImage.code)) {
                            next(uploadImage.data.file, questiponTitle, questionContent);
                        } else {
                            uploadFail(uploadImage);
                        }
                    }

                    @Override
                    public void onError(Response<UploadImage> response) {
                        uploadFail(response.body());
                    }
                });
    }

    private void uploadFail(UploadImage response) {
        mIsUploadIng = false;
        mLlLoad.setVisibility(View.GONE);
        if (TextUtils.isEmpty(response.message)) {
            asyncShowToast("上传失败");
        } else {
            asyncShowToast(response.message);
        }
    }

    private void next(String img, String questiponTitle, String questionContent) {
        mSb.append(img).append(",");
        if (mIndex == mAdapter.getImages().size()) {
            CommintData(img, questiponTitle, questionContent);
        } else {
            upLoadPic(mAdapter.getImages().get(mIndex).path, questiponTitle, questionContent);
        }
    }

    private void CommintData(String img, String questiponTitle, String questionContent) {
        OkGo.<CommonBean>post(Constant.QUESTION_POST_URL)
                .params("question", questiponTitle)
                .params("content", questionContent)
                .params("images", mSb.toString()).execute(new JsonCallback<CommonBean>() {
            @Override
            public void onSuccess(Response<CommonBean> response) {
                if (response.body().code == 200) {
                    asyncShowToast(response.body().message);
                    mLlLoad.setVisibility(View.GONE);
                    finish();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<ImageItem> images;
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    mImages.addAll(images);
                    mAdapter.setImages(mImages);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    mImages.clear();
                    mImages.addAll(images);
                    mAdapter.setImages(mImages);
                }
            }
        }
    }
}
