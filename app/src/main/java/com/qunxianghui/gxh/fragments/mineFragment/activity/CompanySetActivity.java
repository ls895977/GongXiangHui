package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.nex3z.flowlayout.FlowLayout;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.CompanySetBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.ImageUtils;
import com.qunxianghui.gxh.utils.PicassoImageLoader;
import com.qunxianghui.gxh.widget.LocationPickDialog;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class CompanySetActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_mine_companyset_inputCompany)
    EditText etMineCompanysetInputCompany;
    @BindView(R.id.et_mine_caompanyset_toIndustry)
    EditText etMineCaompanysetToIndustry;

    @BindView(R.id.et_mine_companyset_selectAddress)
    TextView etMineCompanysetSelectAddress;

    @BindView(R.id.et_mine_companyset_detailAddress)
    EditText etMineCompanysetDetailAddress;
    @BindView(R.id.et_mine_companyset_writContactName)
    EditText etMineCompanysetWritContactName;
    @BindView(R.id.et_mine_companyset_zuojiPhoneNumber)
    EditText etMineCompanysetZuojiPhoneNumber;
    @BindView(R.id.et_mine_companyset_mobilePhoneNumber)
    EditText etMineCompanysetMobilePhoneNumber;
    @BindView(R.id.et_mine_companyset_writeQQ)
    EditText etMineCompanysetWriteQQ;
    @BindView(R.id.tv_mine_companyset_fabu)
    TextView tvMmineCompanysetFabu;
    @BindView(R.id.et_mine_companyset_company_lowshow)
    TextView et_mine_companyset_company_lowshow;
    @BindView(R.id.et_mine_companyset_compaydetail)
    TextView et_mine_companyset_compaydetail;
    @BindView(R.id.fl_company_photo)
    FlowLayout fl_company_photo;
    @BindView(R.id.iv_companyset_back)
    ImageView ivCompanysetBack;
    private CompanySetBean.DataBean dataList;
    private SelectPhotoDialog selectPhotoDialog;
    private List<Bitmap> mData;
    private int IMAGE_PICKER = 0x1000;
    private int REQUEST_CODE_SELECT = 0x1007;
    private List<ImageView> imgs = new ArrayList<>();
    private LocationPickDialog locationPickDialog;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_company_set;
    }

    @Override
    protected void initViews() {
        locationPickDialog = new LocationPickDialog(this, new LocationPickDialog.LocationPickListener() {
            @Override
            public void onSelect(String info) {
                if (!TextUtils.isEmpty(info)) {
                    etMineCompanysetSelectAddress.setText(info);
                }
            }
        });

        fl_company_photo.setChildSpacing(15);
        fl_company_photo.setRowSpacing(15);
        fl_company_photo.setMaxRows(2);
        fl_company_photo.setFlow(true);
        for (int i = 0; i < 9; i++) {
            View inflate = LayoutInflater.from(this).inflate(R.layout.item_grid_photo, null);
            ImageView mIv = inflate.findViewById(R.id.iv_photo);
            if (i < 8) {
                mIv.setVisibility(View.GONE);
            } else {
                mIv.setImageResource(R.mipmap.image_add);
            }
            fl_company_photo.addView(inflate);
            imgs.add(mIv);
            final int j = i;
            mIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (j == 8) {
                        if (getAllVisibleCount()) {
                            ToastUtils.showShortToast(CompanySetActivity.this, "最多添加8张图片");
                        } else {
                            selectPhotoDialog.show();
                        }
                    } else {
                        //todo 图片预览，可删除, 使用PhotoView
                    }
                }
            });

        }

        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(150, 150));

        /**
         * 获取用户company的信息
         *
         */

        OkGo.<String>post(Constant.GET_COMPANY_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                parseCompanyInfo(response.body());


            }
        });
    }

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

    private void parseCompanyInfo(String body) {
        final CompanySetBean companySetBean = GsonUtils.jsonFromJson(body, CompanySetBean.class);
        if (companySetBean.getCode() == 0) {
            dataList = companySetBean.getData();

            /**
             * 如果有数据  填充设置的数据
             */

            fillPersonCompanyData(dataList);

        }
    }

    private void fillPersonCompanyData(CompanySetBean.DataBean dataList) {
        final String company_name = dataList.getCompany_name(); //公司名称
        final String company_trade_name = dataList.getCompany_trade_name();// 所属行业
        final String province_name = dataList.getProvince_name();  //省份
        final String city_name = dataList.getCity_name(); //城市
        final String area_name = dataList.getArea_name();  //区
        final String address = dataList.getAddress();  //详细地址
        final String linkname = dataList.getLinkname(); //联系人姓名
        final String qq = dataList.getQq();  //联系QQ
        final String mobile = dataList.getMobile();  //手机号码
        final String tel = dataList.getTel();  //座机号码
        final String content = dataList.getContent();  //公司详情
        final String description = dataList.getDescription();  //公司简介


        etMineCompanysetInputCompany.setText(company_name);
        etMineCompanysetDetailAddress.setText(address);
        etMineCompanysetMobilePhoneNumber.setText(mobile);
        etMineCompanysetZuojiPhoneNumber.setText(tel);
        etMineCompanysetWriteQQ.setText(qq);
        et_mine_companyset_company_lowshow.setText(description);
        et_mine_companyset_compaydetail.setText(content);
        etMineCompanysetWritContactName.setText(linkname);
        etMineCompanysetSelectAddress.setText(province_name + city_name + area_name);
        etMineCaompanysetToIndustry.setText(company_trade_name);


    }

    @Override
    protected void initDatas() {

        mData = new ArrayList<>();

    }

    @Override
    protected void initListeners() {
        etMineCaompanysetToIndustry.setOnClickListener(this);
        tvMmineCompanysetFabu.setOnClickListener(this);
        etMineCompanysetSelectAddress.setOnClickListener(this);
        ivCompanysetBack.setOnClickListener(this);


        selectPhotoDialog = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                /*ActivityCompat.requestPermissions(CompanySetActivity.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        0x009);*/
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

    private void startChoosePhoto(int i) {
        ImagePicker imagePicker = ImagePicker.getInstance();
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_mine_caompanyset_toIndustry:
                asyncShowToast("选择行业");
                break;
            case R.id.et_mine_companyset_selectAddress:
                locationPickDialog.show();
                locationPickDialog.showPickView();
                break;
            case R.id.tv_mine_companyset_fabu:

                fetchCompayData();
                break;
            case R.id.iv_companyset_back:
                finish();
                break;

        }
    }

    private void fetchCompayData() {

        final String companyName = etMineCompanysetInputCompany.getText().toString().trim();  //公司名称
        final String detailAddress = etMineCompanysetDetailAddress.getText().toString().trim();  //详细地址
        final String connectName = etMineCompanysetWritContactName.getText().toString().trim(); //联系人姓名
        final String connectPhone = etMineCompanysetMobilePhoneNumber.getText().toString().trim(); //联系人手机
        final String connectCall = etMineCompanysetZuojiPhoneNumber.getText().toString().trim(); //联系人电话
        final String connectQQ = etMineCompanysetWriteQQ.getText().toString().trim(); //联系人QQ
        final String companyLowshow = et_mine_companyset_company_lowshow.getText().toString().trim(); //企业简介
        final String companyL = et_mine_companyset_compaydetail.getText().toString().trim(); //企业详情


        if (TextUtils.isEmpty(companyName) || TextUtils.isEmpty(detailAddress) || TextUtils.isEmpty(connectName) || TextUtils.isEmpty(connectPhone)
                || TextUtils.isEmpty(connectCall) || TextUtils.isEmpty(connectQQ) || TextUtils.isEmpty(companyLowshow)) {
            asyncShowToast("还有一些信息没有填，仔细检查一下");

        } else {

            OkGo.<String>post(Constant.ADD_COMPANY_URL).
                    params("company_name", companyName)
                    .params("address", detailAddress)
                    .params("linkname", connectName)
                    .params("mobile", connectPhone)
                    .params("tel", connectCall)
                    .params("qq", connectQQ)
                    .params("images", "")
                    .params("description", companyLowshow)
                    .params("content", companyL)
                    .params("province_id", "")
                    .params("city_id", "")
                    .params("area_id", "")
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Logger.e("发布成功+" + response.body().toString());
                        }

                        @Override
                        public void onError(Response<String> response) {


                            super.onError(response);
                            asyncShowToast(response.message());
                        }
                    });

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                                ToastUtils.showShortToast(CompanySetActivity.this, "最多添加8张图片");
                                return;
                            }
                            bitmap = BitmapFactory.decodeFile(images.get(0).path);
                            if (bitmap != null && mImgView != null) {
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
                                    ToastUtils.showShortToast(CompanySetActivity.this, "最多添加8张图片");
                                    break;
                                }
                                if (!TextUtils.isEmpty(images.get(i).path)) {
                                    mImgView.setVisibility(View.VISIBLE);
                                    bitmap = BitmapFactory.decodeFile(images.get(i).path);
                                    bitmap = ImageUtils.compressBmpToFile(bitmap, images.get(i).path);
                                }
                                if (bitmap != null && mImgView != null) {
                                    GlideApp.with(mContext).load(ImageUtils.getUriFromFile(this, images.get(i).path))
                                            .centerCrop()
                                            .placeholder(R.mipmap.image_add)
                                            .error(R.mipmap.image_add)
                                            .into(mImgView);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
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
