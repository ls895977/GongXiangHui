package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.alibaba.fastjson.JSON;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.BaoLiaoAdapter;
import com.qunxianghui.gxh.adapter.BaoLiaoItemAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.BaoLiaoBean;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.ToastUtils;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;
import com.sina.weibo.sdk.utils.LogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.RequestBody;

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
    private BaoLiaoAdapter mAdapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    private SelectPhotoDialog selectPhotoDialog;
    private String mBaoLiaoContent;
    private EditText mEtContent;
    private int selectImgSize = 0;
    private List<BaoliaoBean.DataBean.Content> contentBeanList = new ArrayList<>();
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
        imagePicker.setMultiMode(false);                       //不能多选
        imagePicker.setSelectLimit(maxImgCount);              //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);                       //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);                      //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);                         //保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);                         //保存文件的高度。单位像素
        selImageList = new ArrayList<>();
        mAdapter = new BaoLiaoAdapter(this);
        mAdapter.setOnItemClickListener(this);
        mRv.setHasFixedSize(true);
        mRv.setAdapter(mAdapter);
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
                uploadBaoLiaoData();
                break;
        }
    }

    /**
     * 上传图片前的判断
     *
     * @return
     */
    private boolean isCanUpload() {
        String mBaoLiaoTitle = mEtTitle.getText().toString().trim();
        if (TextUtils.isEmpty(mBaoLiaoTitle)) {
            asyncShowToast("您尚未输入标题！");
            return false;
        }
        for (int i = 0; i < mRv.getChildCount(); i++) {
            LinearLayout layout = (LinearLayout) mRv.getChildAt(i);  //获得子item的layout
            mEtContent = layout.findViewById(R.id.et_content);
            RecyclerView mChildImgRecview = layout.findViewById(R.id.rv);
            String sd = "";
            if (null != mEtContent.getText()) {
                sd = mEtContent.getText().toString();
            }
            int childCount = mChildImgRecview.getChildCount();

            BaoLiaoItemAdapter itemAdapter = (BaoLiaoItemAdapter) mChildImgRecview.getAdapter();

            boolean isNoPath = true;
            if(itemAdapter.getImages()!=null && !itemAdapter.getImages().isEmpty()
                    && !TextUtils.isEmpty(itemAdapter.getImages().get(0).path)){
                isNoPath = false;
            }
            if (TextUtils.isEmpty(sd) && childCount == 1 && isNoPath) {
                ToastUtils.showShort("您尚未填写第" + (i + 1) + "条发布内容！");
                return false;
            }
        }
        return true;
    }


    /**
     * 上传爆料的内容
     */
    private void uploadBaoLiaoData() {
        mLlLoad.setVisibility(View.VISIBLE);
        upLoadPics.clear();
        contentBeanList.clear();
        boolean isHasPics = false;
        try {
            for (int i = 0; i < mAdapter.mData.size(); i++) {
                BaoLiaoBean datum = mAdapter.mData.get(i);
                BaoliaoBean.DataBean.Content contentBean = new BaoliaoBean.DataBean.Content();
                contentBean.text = datum.mEt;
                if (datum.mList != null && !datum.mList.isEmpty()) {
                    isHasPics = true;
                    for (int j = 0; j < datum.mList.size(); j++) {
                        ImageItem imageItem = datum.mList.get(j);
                        Log.e("imgUrl:",imageItem.path);
                        if (!imageItem.path.contains("http")) {
                            upLoadPic(i,"data:image/jpeg;base64," + Utils.imageToBase64(imageItem.path));
                        }
                    }
                }
                contentBeanList.add(contentBean);
            }
            if (!isHasPics) {
                fetchBaoLiaoData();
            }
        } catch (Exception e) {
            LogUtil.e(TAG, e.getMessage());
        }
    }
    /**
     * 上传图片
     *
     * @param
     * @param
     * @param
     */
    private void upLoadPic(final int position, String urls) {
        OkGo.<UploadImage>post(Constant.UP_LOAD_OSS_PIC)
                .params("base64", urls)
                .execute(new JsonCallback<UploadImage>() {
                    @Override
                    public void onSuccess(Response<UploadImage> response) {
                        UploadImage uploadImage = response.body();
                        if (uploadImage.code.equals("0")) {
                            upLoadPics.add(uploadImage.data.file);
                            if (!contentBeanList.isEmpty()) {
                                BaoliaoBean.DataBean.Content contentBean = contentBeanList.get(position);
                                contentBean.img = uploadImage.data.file;
                            }

                            if (selectImgSize == 0) {
                                for (int i = 0; i < mAdapter.mData.size(); i++) {
                                    int itemSize = 0;
                                    if (null != mAdapter.mData.get(i).mList && !mAdapter.mData.get(i).mList.isEmpty()) {
                                        itemSize = mAdapter.mData.get(i).mList.size();
                                    }
                                    selectImgSize += itemSize;
                                }
                            }
                            Log.e("imgSize:",""+selectImgSize);
                            if (selectImgSize == upLoadPics.size()) {
                                fetchBaoLiaoData();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<UploadImage> response) {
                        super.onError(response);
                        ToastUtils.showShort(response.message());
                        mLlLoad.setVisibility(View.GONE);
                    }
                });
    }

    /**
     * 填充爆料
     */
    private void fetchBaoLiaoData() {
        JSONArray objects = new JSONArray();
        for (int i = 0; i < contentBeanList.size(); i++) {
            JSONObject object = new JSONObject();
            BaoliaoBean.DataBean.Content contentBean = contentBeanList.get(i);
            try {
                object.put("img",contentBean.img);
                object.put("text",contentBean.text);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            objects.put(object);
        }

        final String faBuTitle = mEtTitle.getText().toString().trim();

        StringBuilder stringBuilder = new StringBuilder("");
        if (!upLoadPics.isEmpty()) {
            stringBuilder.append(upLoadPics.get(0));
        }

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("title", faBuTitle);
            jsonObject.putOpt("content", objects);
            jsonObject.put("images", stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.e("imgContent",jsonObject.toString());

        OkGo.<CommonBean>post(Constant.HOME_DISCLOSS_URL)
                .upJson(jsonObject.toString())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        mLlLoad.setVisibility(View.GONE);
                        int code = response.body().code;
                        if (code == 0) {
                            asyncShowToast("爆料成功");
                            finish();
                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        mLlLoad.setVisibility(View.GONE);
                        asyncShowToast(response.message());
                    }
                });
    }

    private int addItemPosition = 0;
    @Override
    public void onItemClick(View view, int position, int type) {
        switch (view.getId()) {
            case R.id.iv_delete:
                mAdapter.remove(position);
                break;
            case R.id.tv_add:
                mAdapter.addItem(position);
                addItemPosition = position;
                break;
            case R.id.rv:
                selImageList = mAdapter.getImages();
                switch (type) {
                    case IMAGE_ITEM_ADD:
                        selectPhotoDialog.show();
                        break;
                    default:
                        //打开预览
                        Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                        intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, mAdapter.getImages());
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

                    selImageList.clear();
                    selImageList.addAll(images);
                    mAdapter.setImages(selImageList);

                    clearAddImg();
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    mAdapter.setImages(selImageList);

                    clearAddImg();
                }
            }
        }

    }

    private void clearAddImg() {
        RecyclerView recyclerView = ((LinearLayout) mRv.getChildAt(addItemPosition)).findViewById(R.id.rv);
        BaoLiaoItemAdapter mChildAdapter = (BaoLiaoItemAdapter) recyclerView.getAdapter();
        mChildAdapter.remove(addItemPosition,1);
    }
}
