package com.qunxianghui.gxh.ui.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
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
import com.qunxianghui.gxh.adapter.ImagePickerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PublishActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.iv_fabu_back)
    ImageView ivFabuBack;
    @BindView(R.id.tv_home_baoliao_fabu)
    TextView tvHomeBaoliaoFabu;
    @BindView(R.id.iv_baoliao_close)
    ImageView ivBaoliaoClose;
    @BindView(R.id.ll_baoliao_remember)
    LinearLayout llBaoliaoRemember;
    @BindView(R.id.et_baoliao_fabu_title)
    EditText etBaoliaoFabuTitle;
    @BindView(R.id.et_baoliao_fabu_content)
    EditText etBaoliaoFabuContent;
    @BindView(R.id.recyclerView_publish_images)
    RecyclerView recyclerViewPublishImages;
    @BindView(R.id.ll_publich_load)
    LinearLayout llPublichLoad;
    private int maxImgCount = 8;               //允许选择图片最大数
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> images = new ArrayList<>();
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private List<String> upLoadPics = new ArrayList<>();
    private SelectPhotoDialog selectPhotoDialog;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initViews() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new NewGlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);                      //显示拍照按钮
        imagePicker.setCrop(false);                           //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true);                   //是否按矩形区域保存
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);
        recyclerViewPublishImages.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerViewPublishImages.setHasFixedSize(true);
        recyclerViewPublishImages.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        tvHomeBaoliaoFabu.setOnClickListener(this);
        ivFabuBack.setOnClickListener(this);
        selectPhotoDialog = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(PublishActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(PublishActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }
        });
    }

    private void requestBaoLiaoFaBu() {
        llPublichLoad.setVisibility(View.VISIBLE);
        if (selImageList.size() == 0) {
            fetchPublishConentData();
        } else {
            for (int i = 0, length = selImageList.size(); i < length; i++) {
                String path = selImageList.get(i).path;
                if (!path.contains("http")) {
                    upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(path), i == length - 1);
                } else {
                    upLoadPics.add(path);
                    if (i == length - 1) {
                        fetchPublishConentData();
                    }
                }
            }
        }
    }

    /**
     * 上传发布的内容
     */

    private void fetchPublishConentData() {
        String faBuContent = etBaoliaoFabuContent.getText().toString();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = upLoadPics.size(); i < length; i++) {
            if (i != upLoadPics.size() - 1) {
                stringBuilder.append(upLoadPics.get(i) + ",");
            } else {
                stringBuilder.append(upLoadPics.get(i));
            }
        }
        OkGo.<CommonBean>post(Constant.PUBLISH_ARTICLE)
                .params("content", faBuContent)
                .params("images", stringBuilder.toString())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        if (response.body().code == 0) {
                            asyncShowToast("上传成功");
                            finish();
                        }
                    }
                });
    }

    //上传图片
    private void upLoadPic(String urls, final boolean isUpdate) {
        OkGo.<UploadImage>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", urls)
                .execute(new JsonCallback<UploadImage>() {
                    @Override
                    public void onSuccess(Response<UploadImage> response) {
                        UploadImage uploadImage = response.body();
                        if (uploadImage.code.equals("0")) {
                            upLoadPics.add(uploadImage.data.file);
                            if (isUpdate) {
                                fetchPublishConentData();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<UploadImage> response) {
                        super.onError(response);
                        llPublichLoad.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_home_baoliao_fabu:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    finish();
                    return;
                }
                if (!isCanUpload()) {
                    return;
                }
                requestBaoLiaoFaBu();
                break;
            case R.id.iv_fabu_back:
                finish();
                break;
        }
    }

    private boolean isCanUpload() {
        String faBuContent = etBaoliaoFabuContent.getText().toString();
        if (TextUtils.isEmpty(faBuContent)) {
            asyncShowToast("内容不能为空");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            } else {
                asyncShowToast("没有数据");
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }

    }


    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                selectPhotoDialog.show();
                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                break;
        }
    }
}
