package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.BaoLiaoAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.qunxianghui.gxh.ui.activity.PublishActivity.IMAGE_ITEM_ADD;


/**
 * Created by Administrator on 2018/3/16 0016.
 */

public class BaoLiaoActivity extends BaseActivity implements BaoLiaoAdapter.OnRecyclerViewItemClickListener {

    @BindView(R.id.et_title)
    EditText mEtTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;
    @BindView(R.id.ll_load)
    LinearLayout mLlLoad;

    private List<String> upLoadPics = new ArrayList<>();
    private int maxImgCount = 3;               //允许选择图片最大数
    private BaoLiaoAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private SelectPhotoDialog selectPhotoDialog;
    private String mBaoLiaoContent;
    private EditText mEtContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baoliao;
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
        adapter = new BaoLiaoAdapter(this);
        adapter.setOnItemClickListener(this);
        mRv.setHasFixedSize(true);
        mRv.setAdapter(adapter);
    }

    @Override
    protected void initListeners() {
        selectPhotoDialog = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(BaoLiaoActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(BaoLiaoActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
            }

        });
    }

    @OnClick({R.id.tv_cancel, R.id.tv_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_upload:
                if (!LoginMsgHelper.isLogin()) {
                    toActivity(LoginActivity.class);
                    finish();
                    return;
                }
                if (!isCanUpload()) {
                    return;
                }
                RequestBaoLiaoData();
                break;
        }
    }

    /**
     * 上传图片前的判断
     *
     * @return
     */
    private boolean isCanUpload() {
        for (int i = 0; i < mRv.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) mRv.getChildAt(i);  //获得子item的layout
            mEtContent = layout.findViewById(R.id.et_content);
            mBaoLiaoContent = mEtContent.getText().toString().trim();
        }
        String mBaoLiaoTitle = mEtTitle.getText().toString().trim();
        if (TextUtils.isEmpty(mBaoLiaoContent) || TextUtils.isEmpty(mBaoLiaoTitle)) {
            asyncShowToast("标题和内容不能为空");
            return false;
        }
        return true;
    }

    /**
     * 上传爆料的内容
     */
    private void RequestBaoLiaoData() {
        mLlLoad.setVisibility(View.VISIBLE);

        if (selImageList.size() == 0) {
            fetchBaoLiaoData();
        } else {
            for (int i = 0, length = selImageList.size(); i < length; i++) {
                String path = selImageList.get(i).path;
                if (!path.contains("http")) {
                    File file=new File(path);
                    upLoadPic(file, i == length - 1);
                } else {
                    upLoadPics.add(path);
                    if (i == length - 1) {
                        fetchBaoLiaoData();
                    }

                }
            }
        }

    }

    /**
     * 填充爆料
     */
    private void fetchBaoLiaoData() {
        final String faBuContent = mEtContent.getText().toString().trim();
        final String faBuTitle = mEtTitle.getText().toString().trim();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = upLoadPics.size(); i < length; i++) {
            if (i != upLoadPics.size() - 1) {
                stringBuilder.append(upLoadPics.get(i) + ",");
            } else {
                stringBuilder.append(upLoadPics.get(i));
            }
        }
        OkGo.<String>post(Constant.HOME_DISCLOSS_URL)
                .params("title", faBuTitle)
                .params("content", faBuContent)
                .params("images", stringBuilder.toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        mLoadView.setVisibility(View.GONE);
                        parseBaoLiaoData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        mLoadView.setVisibility(View.GONE);
                        asyncShowToast(response.message());
                    }
                });
    }

    /**
     * 上传图片
     *
     * @param
     * @param
     */
    private void upLoadPic(File urls, final boolean isUpdate) {
        OkGo.<String>post(Constant.UP_LOAD_OSS_PIC)
                .params("file", urls)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if (uploadImage.code.equals("0")) {
                            upLoadPics.add(uploadImage.data.file);
                            if (isUpdate) {
                                fetchBaoLiaoData();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        mLoadView.setVisibility(View.GONE);
                    }
                });
    }

    private void parseBaoLiaoData(String body) {
        try {
            JSONObject jsonObject = new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code == 0) {
                asyncShowToast("爆料成功");
                Logger.i("爆料的数据------" + body.toString());
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(View view, int position, int type) {
        switch (view.getId()) {
            case R.id.iv_delete:
                adapter.remove(position);
                break;
            case R.id.tv_add:
                adapter.addItem(position);
                break;
            case R.id.rv:
                selImageList = adapter.getImages();
                switch (type) {
                    case IMAGE_ITEM_ADD:
                        selectPhotoDialog.show();
                        break;
                    default:
                        //打开预览
                        Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, adapter.getImages());
                        intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, type);
                        intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                        startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                }
                break;
        }
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
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
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


//    private boolean isCanUpload() {
//        final String faBuContent = etBaoliaoFabuContent.getText().toString().trim();
//        final String faBuTitle = etBaoliaoFabuTitle.getText().toString().trim();
//        if (TextUtils.isEmpty(faBuTitle) || TextUtils.isEmpty(faBuContent)) {
//            asyncShowToast("还有一些信息没有填，仔细检查一下");
//            return false;
//        }
//        return true;
//    }
}
