package com.qunxianghui.gxh.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.nex3z.flowlayout.FlowLayout;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.location.ImageBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.ImageUtils;
import com.qunxianghui.gxh.utils.PicassoImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;
import com.tencent.mm.opensdk.utils.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishActivity extends BaseActivity implements View.OnClickListener {

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
    @BindView(R.id.fl_fabu_photo)
    FlowLayout flFabuPhoto;

    private int IMAGE_PICKER = 0x1000;
    private int REQUEST_CODE_SELECT = 0x1007;
    private List<String> list = new ArrayList<>();
    private List<ImageView> imgs = new ArrayList<>();
    private List<String> upLoadPics = new ArrayList<>();
    private SelectPhotoDialog selectPhotoDialog;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_publish;
    }

    @Override
    protected void initViews() {
        flFabuPhoto.setChildSpacing(15);
        flFabuPhoto.setRowSpacing(15);
        flFabuPhoto.setMaxRows(2);
        flFabuPhoto.setFlow(true);

        for (int i = 0; i < 9; i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_grid_photo, null);
            ImageView mIv = inflate.findViewById(R.id.iv_photo);
            if (i < 8) {
                mIv.setVisibility(View.GONE);
            } else {
                mIv.setImageResource(R.mipmap.image_add);
            }
            flFabuPhoto.addView(inflate);
            imgs.add(mIv);
            final int j = i;
            mIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (j == 8) {
                        if (getAllVisibleCount()) {
                            ToastUtils.showShortToast(PublishActivity.this, "最多添加8张图片");
                        } else {
                            selectPhotoDialog.show();
                        }
                    } else {
                        //todo 图片预览，可删除, 使用PhotoView
                    }
                }
            });
        }
    }

    /*获取显示的view*/
    private boolean getAllVisibleCount() {
        boolean isAll = true;
        for (int i = 0; i < imgs.size(); i++) {
            if (i < 8) {
                if (imgs.get(i).getVisibility() != View.VISIBLE) {
                    isAll = false;
                }
            }
        }
        return isAll;

    }

    @Override
    protected void initDatas() {

    }


    @Override
    protected void initListeners() {
        tvHomeBaoliaoFabu.setOnClickListener(this);
        ivFabuBack.setOnClickListener(this);

        selectPhotoDialog = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                startChoosePhoto(0);
                selectPhotoDialog.dismiss();
            }

            @Override
            public void onSelect() {
                startChoosePhoto(1);
                selectPhotoDialog.dismiss();
            }

            @Override
            public void onDismiss() {
                selectPhotoDialog.dismiss();
            }
        });
    }

    /**
     * 选择图片
     *
     * @param i
     */
    private void startChoosePhoto(int i) {
        com.lzy.imagepicker.ImagePicker imagePicker = com.lzy.imagepicker.ImagePicker.getInstance();
        imagePicker.setImageLoader(new PicassoImageLoader());//设置图片加载器
        imagePicker.setShowCamera(false);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(8);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        if (i == 0) {
            Intent intent = new Intent(this, ImageGridActivity.class);
            intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
            startActivityForResult(intent, REQUEST_CODE_SELECT);
        } else {
            Intent intent = new Intent(this, ImageGridActivity.class);
            startActivityForResult(intent, IMAGE_PICKER);
        }

    }

    /**
     * 网络请求上传图片
     *
     * @param urls
     */
    private void upLoadPic(String urls) {

        OkGo.<LzyResponse<ImageBean>>post(Constant.UP_LOAD_PIC)
                .params("base64", urls)
                .execute(new DialogCallback<LzyResponse<ImageBean>>(this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<ImageBean>> response) {
                        if (response.body().code.equals("0")) {
                            upLoadPics.add(response.body().data.getFile());
                            Toast.makeText(mContext, "上传图片成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void requestBaoLiaoFaBu() {
        String faBuContent = etBaoliaoFabuContent.getText().toString();
        String imgUrl = Utils.listToString(upLoadPics);
        if (TextUtils.isEmpty(faBuContent)) {
            asyncShowToast("内容不能为空");
        } else {
            OkGo.<String>post(Constant.PUBLISH_ARTICLE)
                    .params("content", faBuContent)
                    .params("images", imgUrl)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            parseBaoLiaoData(response.body());
                        }
                    });
        }


    }

    private void parseBaoLiaoData(String body) {
        try {
            JSONObject jsonObject=new JSONObject(body);
            int code = jsonObject.getInt("code");
            if (code==0){
                asyncShowToast("上传成功");
                com.orhanobut.logger.Logger.d("意见发布"+body.toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        finish();

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
            case R.id.tv_home_baoliao_fabu:
                requestBaoLiaoFaBu();
                break;
            case R.id.iv_fabu_back:
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            try {
                if (data != null) {
                    final ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                    Bitmap bitmap = null;
                    ImageView mImgView = null;
                    if (images != null && !images.isEmpty()) {
                        if (images.size() == 1) {
                            mImgView = getInVisibleImgview();
                            if (mImgView == null) {
                                ToastUtils.showShortToast(PublishActivity.this, "最多添加8张图片");
                                return;
                            }
                            bitmap = BitmapFactory.decodeFile(images.get(0).path);
                            if (bitmap != null) {
                                GlideApp.with(mContext).load(ImageUtils.getUriFromFile(this, images.get(0).path))
                                        .centerCrop()
                                        .placeholder(R.mipmap.image_add)
                                        .error(R.mipmap.image_add)
                                        .into(mImgView);
                            }
                            if (!images.get(0).path.isEmpty() && bitmap != null) {
                                bitmap = ImageUtils.compressBmpToFile(bitmap, images.get(0).path);
                            }
                        } else {
                            for (int i = 0; i < images.size(); i++) {
                                mImgView = getInVisibleImgview();
                                if (mImgView == null) {
                                    ToastUtils.showShortToast(PublishActivity.this, "最多添加8张图片");
                                    break;
                                }
                                if (!TextUtils.isEmpty(images.get(i).path)) {
                                    mImgView.setVisibility(View.VISIBLE);
                                    bitmap = BitmapFactory.decodeFile(images.get(i).path);
                                    bitmap = ImageUtils.compressBmpToFile(bitmap, images.get(i).path);
                                }
                                if (bitmap != null) {
                                    GlideApp.with(mContext).load(ImageUtils.getUriFromFile(this, images.get(i).path))
                                            .centerCrop()
                                            .placeholder(R.mipmap.image_add)
                                            .error(R.mipmap.image_add)
                                            .into(mImgView);
                                }
                            }
                        }


                        //todo upload selected pics
                        for (ImageItem imageItem : images) {
                            Log.e("imgPicker:", imageItem.path);
                            upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(imageItem.path));
                        }
                        // /storage/emulated/0/DCIM/Screenshots/Screenshot_2018-06-26-22-09-50-699_chinsoft.water.png
                        //upLoadPic("");

                    }
                }
            } catch (Exception e) {
                Log.w("test", e.getMessage());
            }
        }
    }

    private ImageView getInVisibleImgview() {
        ImageView mIview = null;
        for (int i = 0; i < imgs.size(); i++) {
            if (imgs.get(i).getVisibility() == View.GONE) {
                imgs.get(i).setVisibility(View.VISIBLE);
                mIview = imgs.get(i);
                break;
            }
        }
        return mIview;
    }
}
