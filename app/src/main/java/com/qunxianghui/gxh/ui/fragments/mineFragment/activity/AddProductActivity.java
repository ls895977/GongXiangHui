package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePickerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;
import static com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanySetActivity.REQUEST_CODE_PREVIEW;

public class AddProductActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.tv_add_product_save)
    TextView mTvAddProductSave;
    @BindView(R.id.et_add_product_title)
    EditText mEtAddAProductTitle;
    @BindView(R.id.et_add_product__introduce)
    EditText mEtAddProductIntroduce;
    @BindView(R.id.rv)
    RecyclerView mRecyclerViewAddProductPic;
    @BindView(R.id.rl_add_product_edit)
    RelativeLayout rlAddProductEdit;
    @BindView(R.id.ll_load)
    LinearLayout mLlLoad;

    private int mId;
    private Dialog mSelectPhoto;
    private ImagePickerAdapter mAdapter;
    private ArrayList<ImageItem> mImages;
    private int mIndex;
    private int mSelectCount = 3;
    private boolean mIsUploadIng;
    private StringBuilder mSb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_cardadd_product;
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
        mAdapter = new ImagePickerAdapter(this, mImages, mSelectCount);
        mAdapter.setOnItemClickListener(this);
        mRecyclerViewAddProductPic.setAdapter(mAdapter);
        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(mSelectCount - mImages.size());
                Intent intent = new Intent(AddProductActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                ImagePicker.getInstance().setSelectLimit(mSelectCount - mImages.size());
                Intent intent1 = new Intent(AddProductActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        AddAdvanceBean.DataBean info = (AddAdvanceBean.DataBean) intent.getSerializableExtra("info");
        if (info != null) {
            mTvAddProductSave.setVisibility(View.GONE);
            rlAddProductEdit.setVisibility(View.VISIBLE);
            mId = info.getAboutus_id();
            mEtAddAProductTitle.setText(info.getTitle());
            mEtAddProductIntroduce.setText(info.getDescribe());
            ImageItem imageItem;
            for (String s : info.getImage_array()) {
                if (!TextUtils.isEmpty(s)) {
                    imageItem = new ImageItem();
                    imageItem.path = s;
                    mImages.add(imageItem);
                }
            }
            mAdapter.setImages(mImages);
        }
        mSb = new StringBuilder();
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

    @OnClick({R.id.iv_add_product_back, R.id.tv_add_product_save, R.id.tv_add_product_delete, R.id.tv_add_product_complete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_product_back:
                onBackPressed();
                break;
            case R.id.tv_add_product_save:
                saveProductData();
                break;
            case R.id.tv_add_product_delete:
                deleteCompanyCardAdavance();
                break;
            case R.id.tv_add_product_complete:
                String title = mEtAddAProductTitle.getText().toString().trim();
                String des = mEtAddProductIntroduce.getText().toString().trim();
                if (mAdapter.getImages().isEmpty() || TextUtils.isEmpty(title) || TextUtils.isEmpty(des)) {
                    asyncShowToast("请检查一下,还有哪里没有填写");
                } else {
                    mIsUploadIng = true;
                    mLlLoad.setVisibility(View.VISIBLE);
                    upLoadPic(mAdapter.getImages().get(0).path);
                }
                break;
        }
    }

    //保存数据
    private void saveProductData() {
        String title = mEtAddAProductTitle.getText().toString().trim();
        String des = mEtAddProductIntroduce.getText().toString().trim();
        if (mAdapter.getImages().isEmpty() || TextUtils.isEmpty(title) || TextUtils.isEmpty(des)) {
            asyncShowToast("产品或产品介绍不能为空");
        } else {
            mIsUploadIng = true;
            mLlLoad.setVisibility(View.VISIBLE);
            upLoadPic(mAdapter.getImages().get(0).path);
        }
    }

    private void upLoadPic(String urls) {
        mIndex++;
        if (urls.startsWith("http")) {
            next(urls);
            return;
        }
        OkGo.<UploadImage>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", "data:image/jpeg;base64," + Utils.imageToBase64(urls))
                .execute(new JsonCallback<UploadImage>() {
                    @Override
                    public void onSuccess(Response<UploadImage> response) {
                        UploadImage uploadImage = response.body();
                        if ("0".equals(uploadImage.code)) {
                            next(uploadImage.data.file);
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

    private void next(String img) {
        mSb.append(img);
        if (mIndex == mAdapter.getImages().size()) {
            if (mId == 0) {
                saveProduct();
            } else {
                editCompanyCardAdvance();
            }
        } else {
            upLoadPic(mAdapter.getImages().get(mIndex).path);
        }
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

    /*修改公司产品*/
    private void editCompanyCardAdvance() {
        OkGo.<CommonBean>post(Constant.EDIT_COMPANY_CENTER_ADVANCE).
                params("title", mEtAddAProductTitle.getText().toString().trim()).
                params("aboutus_id", mId).
                params("describe", mEtAddProductIntroduce.getText().toString().trim()).
                params("image", mSb.toString()).
                params("datatype", 2).
                execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 200) {
                            asyncShowToast("修改成功");
                            setResult(0x0022);
                            finish();
                        } else {
                            mLlLoad.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        mLlLoad.setVisibility(View.GONE);
                    }
                });
    }

    private void saveProduct() {
        OkGo.<CommonBean>post(Constant.ADD_COMPANY_CENTER_ADVANCE)
                .params("title", mEtAddAProductTitle.getText().toString().trim())
                .params("describe", mEtAddProductIntroduce.getText().toString().trim())
                .params("image", mSb.toString())
                .params("datatype", 2)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("上传成功");
                            setResult(0x0022);
                            finish();
                        }
                    }
                });
    }

    /*删除公司产品*/
    private void deleteCompanyCardAdavance() {
        OkGo.<CommonBean>post(Constant.DELETE_COMPANY_CENTER_ADVANCE)
                .params("aboutus_id", mId)
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

    @Override
    public void onBackPressed() {
        if (mIsUploadIng) {
            asyncShowToast("上传中，请稍等");
            return;
        }
        super.onBackPressed();
    }
}
