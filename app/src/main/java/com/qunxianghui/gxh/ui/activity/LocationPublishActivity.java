package com.qunxianghui.gxh.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePickerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.bean.home.HomeVideoSortBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_PREVIEW;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;

public class LocationPublishActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.ll_load)
    LinearLayout mLlLoad;

    private Dialog mSelectPhoto;
    private ImagePickerAdapter mAdapter;
    private ArrayList<ImageItem> mImages; //当前选择的所有图片
    private OptionsPickerView mChooseType;
    private int mTypeId;
    private boolean mIsUploadIng;
    private int mCount;
    private StringBuilder stringBuilder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_location_publish;
    }

    @Override
    protected void initViews() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());
        imagePicker.setShowCamera(true);
        imagePicker.setCrop(false);
        imagePicker.setSaveRectangle(true);
        imagePicker.setSelectLimit(3);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(1000);
        imagePicker.setOutPutY(1000);

        mImages = new ArrayList<>();
        mAdapter = new ImagePickerAdapter(this, mImages, 3);
        mAdapter.setOnItemClickListener(this);
        mRv.setAdapter(mAdapter);

        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent = new Intent(LocationPublishActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(3 - mImages.size());
                Intent intent1 = new Intent(LocationPublishActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<ImageItem> images = new ArrayList<>();
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

    @OnClick({R.id.tv_cancel, R.id.tv_upload, R.id.tv_type})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                onBackPressed();
                break;
            case R.id.tv_upload:
                if (TextUtils.isEmpty(mEtTitle.getText().toString().trim())) {
                    asyncShowToast("填写下介绍吧!");
                    return;
                }
                if (mImages.isEmpty()) {
                    asyncShowToast("请添加图片");
                    return;
                }
                if (mTypeId == 0) {
                    asyncShowToast("请选择分类");
                    return;
                }
                mIsUploadIng = true;
                stringBuilder = new StringBuilder();
                mLlLoad.setVisibility(View.VISIBLE);
                uploadImages(mImages.get(0).path);
                break;
            case R.id.tv_type:
                if (mChooseType != null) {
                    mChooseType.show();
                } else {
                    OkGo.<String>post(Constant.UPLOAD_LOCAL_POST_SORT_SUB_URL)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    parseVideoSortData(response.body());

                                }
                            });
                }
                break;
        }
    }

    private void uploadImages(String path) {
        OkGo.<String>post(Constant.UP_LOAD_PIC)
                .params("file", new File(path))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if ("0".equals(uploadImage.code)) {
                            mCount++;
                            stringBuilder.append(uploadImage.data.file).append(",");
                            if (mImages.size() == mCount) {
                                uploadInfo();
                            } else {
                                uploadImages(mImages.get(mCount).path);
                            }
                        } else {
                            onError(null);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        mIsUploadIng = false;
                        mLlLoad.setVisibility(View.GONE);
                        asyncShowToast("上传失败...");
                    }
                });
    }

    private void uploadInfo() {
        OkGo.<String>post(Constant.PUBLISH_ARTICLE)
                .params("content", mEtTitle.getText().toString().trim())
                .params("images", stringBuilder.toString())
                .params("posts_id", mTypeId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        CommonBean commonBean = GsonUtils.jsonFromJson(response.body(), CommonBean.class);
                        if (commonBean.code == 200) {
                            mIsUploadIng = false;
                            mLlLoad.setVisibility(View.GONE);
                            asyncShowToast("发布成功");
                            finish();
                        } else {
                            onError(null);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        mIsUploadIng = false;
                        mLlLoad.setVisibility(View.GONE);
                        asyncShowToast("上传失败...");
                    }
                });
    }

    @Override
    public void onBackPressed() {
        if (mIsUploadIng) {
            asyncShowToast("上传中，请稍等");
            return;
        }
        super.onBackPressed();
    }

    private void parseVideoSortData(String body) {
        HomeVideoSortBean homeVideoSortBean = GsonUtils.jsonFromJson(body, HomeVideoSortBean.class);
        int code = homeVideoSortBean.getCode();
        final List<HomeVideoSortBean.DataBean> videoSortBeanData = homeVideoSortBean.getData();
        if (code == 200) {
            final List<String> strings = new ArrayList<>();
            for (HomeVideoSortBean.DataBean dataBean : videoSortBeanData) {
                strings.add(dataBean.getCate_name());
            }
            mChooseType = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    mTypeId = videoSortBeanData.get(options1).getId();
                    mTvType.setText(strings.get(options1));
                }
            }).setCancelColor(Color.parseColor("#676767"))
                    .setSubmitColor(Color.parseColor("#D81717"))
                    .build();
            mChooseType.setNPicker(strings, null, null);
        }
        mChooseType.show();
    }
}