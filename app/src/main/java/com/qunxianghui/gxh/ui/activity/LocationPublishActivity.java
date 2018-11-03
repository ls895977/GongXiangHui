package com.qunxianghui.gxh.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePickerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.bean.home.HomeVideoSortBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.observer.PublishBiaoliao;
import com.qunxianghui.gxh.ui.activity.locationitem.DividerGridItemDecoration;
import com.qunxianghui.gxh.ui.activity.locationitem.OnRecyclerItemClickListener;
import com.qunxianghui.gxh.ui.activity.locationitem.RecyItemTouchHelperCallback;
import com.qunxianghui.gxh.ui.fragments.locationFragment.LocationDetailFragment;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_PREVIEW;
import static com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity.REQUEST_CODE_SELECT;

public class LocationPublishActivity extends BaseActivity
        implements ImagePickerAdapter.OnRecyclerViewItemClickListener,
        ImagePickerAdapter.OnImageBack, RecyItemTouchHelperCallback.OnBackonSwiped {

    @BindView(R.id.tv_upload)
    View mUpload;
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
    private LocationDetailFragment mLocationDetailFragment;
    private HashMap<Object, Object> mHashMap;
    private ArrayList<ImageItem> images;

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
        imagePicker.setSelectLimit(9);
        imagePicker.setMultiMode(true);
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setFocusWidth(800);
        imagePicker.setFocusHeight(800);
        imagePicker.setOutPutX(500);
        imagePicker.setOutPutY(500);

        mImages = new ArrayList<>();
        mAdapter = new ImagePickerAdapter(this, mImages, 9);
        mAdapter.setOnItemClickListener(this);
        mAdapter.setOnImageBack(this);
        mRv.setLayoutManager(new GridLayoutManager(this, 3));
        mRv.addItemDecoration(new DividerGridItemDecoration(this));
        mRv.setHasFixedSize(true);
        RecyItemTouchHelperCallback itemTouchHelperCallback = new RecyItemTouchHelperCallback(mAdapter, false, true);
        itemTouchHelperCallback.setOnBackonSwiped(this);
        final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(mRv);
        mRv.addOnItemTouchListener(new OnRecyclerItemClickListener(mRv) {
            @Override
            public void onItemClick(RecyclerView.ViewHolder viewHolder) {

            }

            @Override
            public void onLongClick(RecyclerView.ViewHolder viewHolder) {
                if (viewHolder.getLayoutPosition() != mImages.size()) {
                    itemTouchHelper.startDrag(viewHolder);
                }
            }
        });

        mRv.setAdapter(mAdapter);
        mSelectPhoto = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(9 - mImages.size());
                Intent intent = new Intent(LocationPublishActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(9 - mImages.size());
                Intent intent1 = new Intent(LocationPublishActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
        mEtTitle.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                mUpload.setSelected(mTypeId != 0 && (!TextUtils.isEmpty(mEtTitle.getText().toString().trim()) || !mImages.isEmpty()));
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        mUpload.setSelected(mTypeId != 0 && (!TextUtils.isEmpty(mEtTitle.getText().toString().trim()) || !mImages.isEmpty()));
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                mSelectPhoto.show();
                break;
            default:
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, mImages);
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
                if (TextUtils.isEmpty(mEtTitle.getText().toString().trim()) && mImages.isEmpty()) {
                    asyncShowToast("请填写本地圈内容或者上传图片！");
                    return;
                }
                if (mTypeId == 0) {
                    asyncShowToast("您尚未选择分类！");
                    return;
                }
                mIsUploadIng = true;
                stringBuilder = new StringBuilder();
                mLlLoad.setVisibility(View.VISIBLE);
                if (!mImages.isEmpty()) {
                    String path = mImages.get(0).path;
                    if (path.endsWith(".gif")) {
                        uploadImages("data:image/gif;base64," + Utils.imageToBase64(path));
                    } else {
                        compressImg(path);
                    }
                } else {
                    if (Utils.isTwoClick()) {
                        uploadInfo();
                    }

                }
                break;
            case R.id.tv_type:
                hideKeyboard(view);
                chooseVideoType();
                break;
        }
    }

    private void chooseVideoType() {
        if (mChooseType != null) {
            mChooseType.show();
        } else {
            OkGo.<HomeVideoSortBean>post(Constant.UPLOAD_LOCAL_POST_SORT_SUB_URL)
                    .execute(new JsonCallback<HomeVideoSortBean>() {
                        @Override
                        public void onSuccess(Response<HomeVideoSortBean> response) {
                            parseVideoSortData(response.body());
                        }
                    });
        }
    }

    private void compressImg(String path) {
        Luban.with(LocationPublishActivity.this)
                .load(path)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File newFile) {
                        String newPath = newFile.getAbsolutePath();
                        uploadImages("data:image/jpeg;base64," + Utils.imageToBase64(newPath));
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                }).launch();
    }

    private void uploadImages(String path) {
        OkGo.<UploadImage>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", path)
                .execute(new JsonCallback<UploadImage>() {
                    @Override
                    public void onSuccess(Response<UploadImage> response) {
                        UploadImage uploadImage = response.body();
                        if ("0".equals(uploadImage.code)) {
                            mCount++;
                            stringBuilder.append(uploadImage.data.file).append(",");
                            if (mImages.size() == mCount) {
                                uploadInfo();
                            } else {
                                String path1 = mImages.get(mCount).path;
                                if (path1.endsWith(".gif")) {
                                    uploadImages("data:image/gif;base64," + Utils.imageToBase64(path1));
                                } else {
                                    compressImg(path1);
                                }
                            }
                        } else {
                            uploadFail(uploadImage.message);
                        }
                    }

                    @Override
                    public void onError(Response<UploadImage> response) {
                        uploadFail(response.body().message);
                    }
                });
    }

    private void uploadInfo() {
        OkGo.<CommonBean>post(Constant.PUBLISH_ARTICLE)
                .params("content", mEtTitle.getText().toString().trim())
                .params("images", stringBuilder.toString())
                .params("posts_id", mTypeId)
                .execute(new JsonCallback<CommonBean>() {

                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        CommonBean commonBean = response.body();
                        if (commonBean.code == 200) {
                            mIsUploadIng = false;
                            mLlLoad.setVisibility(View.GONE);
                            EventBus.getDefault().post(new PublishBiaoliao());
                            asyncShowToast("发布成功");
                            finish();
                        } else {
                            uploadFail(commonBean.message);
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        uploadFail(response.body().message);
                    }
                });
    }

    private void uploadFail(String response) {
        mIsUploadIng = false;
        mLlLoad.setVisibility(View.GONE);
        if (TextUtils.isEmpty(response)) {
            asyncShowToast("上传失败");
        } else {
            asyncShowToast(response);
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

    private void parseVideoSortData(HomeVideoSortBean homeVideoSortBean) {
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
                    mUpload.setSelected(!(TextUtils.isEmpty(mEtTitle.getText().toString().trim()) && mImages.isEmpty()));
                }
            }).setCancelColor(Color.parseColor("#676767"))
                    .setSubmitColor(Color.parseColor("#D81717"))
                    .build();
            mChooseType.setNPicker(strings, null, null);
            mChooseType.show();
        } else {
            asyncShowToast(homeVideoSortBean.getMsg());
        }
    }

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void OnBackItem(int position) {
        mImages.remove(position);
        mAdapter.setImages(mImages);
    }

    @Override
    public void backItemMoved(int fromPosition, int toPosition) {
        if (Math.abs(fromPosition - toPosition) == 1) {
            Collections.swap(mImages, fromPosition, toPosition);
            return;
        }
        mImages.add(toPosition, mImages.get(fromPosition));
        if (fromPosition < toPosition)
            mImages.remove(fromPosition);
        else
            mImages.remove(fromPosition + 1);
    }
}
