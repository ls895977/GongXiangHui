package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.ImagePickerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.UploadImage;
import com.qunxianghui.gxh.bean.mine.CompanySetBean;
import com.qunxianghui.gxh.bean.mine.ThirdStepCityBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.NewGlideImageLoader;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.SelectPhotoDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class CompanySetActivity extends BaseActivity implements View.OnClickListener, ImagePickerAdapter.OnRecyclerViewItemClickListener {
    @BindView(R.id.et_mine_companyset_inputCompany)
    EditText etMineCompanysetInputCompany;
    @BindView(R.id.et_mine_companyset_toIndustry)
    EditText etMineCaompanysetToIndustry;
    @BindView(R.id.et_mine_companyset_select_area)
    TextView etMineCompanysetSelectArea;
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
    @BindView(R.id.et_company_detail)
    EditText et_mine_companyset_company_lowshow;
    @BindView(R.id.et_mine_companyset_compaydetail)
    TextView et_mine_companyset_compaydetail;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.iv_companyset_back)
    ImageView ivCompanysetBack;
    @BindView(R.id.ll_load)
    View mLoadView;
    //    @BindView(R.id.et_mine_companyset_selectcity)
//    TextView etMineCompanysetSelectcity;
    private SelectPhotoDialog selectPhotoDialog;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;
    public static final int IMAGE_PICKER = 102;
    private List<ImageView> imgs = new ArrayList<>();
    private int maxImgCount = 8;               //允许选择图片最大数
    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> images = new ArrayList<>();
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private List<String> upLoadPics = new ArrayList<>();
    private int[] mPosition = new int[3];
    private ArrayList<ThirdStepCityBean.DataBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private CompanySetBean.DataBean mDataBean;
    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_company_set;
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
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void parseCompanyInfo(String body) {
        final CompanySetBean companySetBean = GsonUtils.jsonFromJson(body, CompanySetBean.class);

        int code = companySetBean.getCode();
        if (code == 0) {
            mDataBean = companySetBean.getData();
            if (mDataBean != null) {
                fillPersonCompanyData(mDataBean);
                String images = mDataBean.getImages();
                if (!TextUtils.isEmpty(images)) {
                    String[] split = images.split(",");
                    for (String aSplit : split) {
                        ImageItem imageItem = new ImageItem();
                        imageItem.path = aSplit;
                        this.selImageList.add(imageItem);
                    }
                    if (!this.selImageList.isEmpty()) {
                        adapter.setImages(selImageList);
                    }
                }
            }
        } else if (code == 101) {
            asyncShowToast("查询失败 请添加后再查询！");
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
        if (city_name != null) {
            etMineCompanysetSelectArea.setText(province_name + city_name + area_name);
        }
        etMineCompanysetDetailAddress.setText(address);
        etMineCompanysetMobilePhoneNumber.setText(mobile);
        etMineCompanysetZuojiPhoneNumber.setText(tel);
        etMineCompanysetWriteQQ.setText(qq);
        et_mine_companyset_company_lowshow.setText(description);
        et_mine_companyset_compaydetail.setText(content);
        etMineCompanysetWritContactName.setText(linkname);
        etMineCaompanysetToIndustry.setText(company_trade_name);
    }
    @Override
    protected void initDatas() {

        OkGo.<String>post(Constant.GET_COMPANY_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
      Logger.d("企业设置的信息"+response.body().toString());
          if(response.body().toString().length()>0){
              tvMmineCompanysetFabu.setText("修改");
              parseCompanyInfo(response.body());
          }

            }
        });
    }

    @Override
    protected void initListeners() {
        etMineCaompanysetToIndustry.setOnClickListener(this);
        tvMmineCompanysetFabu.setOnClickListener(this);
        etMineCompanysetSelectArea.setOnClickListener(this);
        ivCompanysetBack.setOnClickListener(this);
        selectPhotoDialog = new SelectPhotoDialog(this, new SelectPhotoDialog.SelectPhotoListener() {
            @Override
            public void onTakePhoto() {
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent = new Intent(CompanySetActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, REQUEST_CODE_SELECT);
                selectPhotoDialog.dismiss();
            }

            @Override
            public void onSelect() {
                //打开选择,本次允许选择的数量
                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                Intent intent1 = new Intent(CompanySetActivity.this, ImageGridActivity.class);
                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                selectPhotoDialog.dismiss();
            }

            @Override
            public void onDismiss() {
                selectPhotoDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_mine_companyset_toIndustry:
                asyncShowToast("选择行业");
                break;
            case R.id.et_mine_companyset_select_area:  //所在区域
                setCompantSetArea();
                break;
            case R.id.tv_mine_companyset_fabu:
                //todo upload selected pics
                if (!isCanUpload()) {
                    return;
                }
                mLoadView.setVisibility(View.VISIBLE);
                if (selImageList.size() == 0) {
                    fetchCompayData();
                } else {
                    for (int i = 0, length = selImageList.size(); i < length; i++) {
                        String path = selImageList.get(i).path;
                        if (!path.contains("http")) {
                            upLoadPic("data:image/jpeg;base64," + Utils.imageToBase64(path), i == length - 1);
                        } else {
                            upLoadPics.add(path);
                            if (i == length - 1) {
                                fetchCompayData();
                            }
                        }
                    }
                }
                break;
            case R.id.iv_companyset_back:
                finish();
                break;
        }
    }

    /**
     * 企业设置
     */
    private void setCompantSetArea() {
        OkGo.<String>get(Constant.HOST_THIRD_STEPAREA_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final ThirdStepCityBean thirdStepCityBean = GsonUtils.jsonFromJson(response.body(), ThirdStepCityBean.class);
                ArrayList<ThirdStepCityBean.DataBean> dataList = thirdStepCityBean.getData();
                options1Items = dataList;
                for (int i = 0; i < dataList.size(); i++) {  //遍历省份
                    ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
                    ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）
                    if (dataList.get(i).sub.size() != 0) {
                        for (int c = 0; c < dataList.get(i).sub.size(); c++) {//遍历该省份的所有城市
                            String CityName = dataList.get(i).sub.get(c).name;
                            CityList.add(CityName);//添加城市
                            ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表
                            //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                            if (dataList.get(i).sub.get(c).sub == null
                                    || dataList.get(i).sub.get(c).sub.size() == 0) {
                                City_AreaList.add("");
                            } else {
                                for (int i1 = 0; i1 < dataList.get(i).sub.get(c).sub.size(); i1++) {
                                    City_AreaList.add(dataList.get(i).sub.get(c).sub.get(i1).name);
                                }
                            }
                            Province_AreaList.add(City_AreaList);//添加该省所有地区数据
                        }
                    } else {
                        CityList.add("");
                        ArrayList<String> City_AreaList = new ArrayList<>();
                        City_AreaList.add("");
                        Province_AreaList.add(City_AreaList);
                    }
                    options2Items.add(CityList);
                    options3Items.add(Province_AreaList);
                }
                if (thirdStepCityBean.getCode() == 0) {
                    showPickerView();
                }
            }
        });

    }

    /**
     * 弹出选择器
     */
    private void showPickerView() {
        try {
            OptionsPickerView pvOptions = new OptionsPickerBuilder(mContext, new OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int options2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    final String tx = options1Items.get(options1).getPickerViewText() +
                            options2Items.get(options1).get(options2) +
                            options3Items.get(options1).get(options2).get(options3);
                    mPosition[0] = options1;
                    mPosition[1] = options2;
                    mPosition[2] = options3;
                    etMineCompanysetSelectArea.setText(tx);

                }
            }).setTitleText("城市选择")
                    .setDividerColor(Color.BLACK)
                    .setTextColorCenter(Color.BLACK)   //设置选中文字的颜色
                    .setContentTextSize(20)
                    .build();

            pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
            pvOptions.show();
        } catch (Exception e) {
            e.printStackTrace();
            Logger.e("企业设置省市区三级" + e.toString());
        }
    }

    private void fetchCompayData() {
        final String companyName = etMineCompanysetInputCompany.getText().toString().trim();  //公司名称
        final String detailAddress = etMineCompanysetDetailAddress.getText().toString().trim();  //详细地址
        final String fromIndustry = etMineCaompanysetToIndustry.getText().toString().trim();  //所属行业
        final String fromArea = etMineCompanysetSelectArea.getText().toString().trim();  //所属地区
        final String connectName = etMineCompanysetWritContactName.getText().toString().trim(); //联系人姓名
        final String connectPhone = etMineCompanysetMobilePhoneNumber.getText().toString().trim(); //联系人手机
        final String connectCall = etMineCompanysetZuojiPhoneNumber.getText().toString().trim(); //联系人电话

        final String connectQQ = etMineCompanysetWriteQQ.getText().toString().trim(); //联系人QQ
        final String companyLowshow = et_mine_companyset_company_lowshow.getText().toString().trim(); //企业简介
        final String companyL = et_mine_companyset_compaydetail.getText().toString().trim(); //企业详情

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0, length = upLoadPics.size(); i < length; i++) {
            if (i != upLoadPics.size() - 1) {
                stringBuilder.append(upLoadPics.get(i) + ",");
            } else {
                stringBuilder.append(upLoadPics.get(i));
            }
        }
        OkGo.<String>post(Constant.ADD_COMPANY_URL).
                params("company_name", companyName)
                .params("address", detailAddress)
                .params("linkname", connectName)
                .params("mobile", connectPhone)
                .params("tel", connectCall)
                .params("qq", connectQQ)
                .params("images", stringBuilder.toString())
                .params("description", companyLowshow)
                .params("content", companyL)
                .params("province_id", options1Items.isEmpty() ? mDataBean.getProvince_id() : options1Items.get(mPosition[0]).id)
                .params("city_id", options1Items.isEmpty() ? mDataBean.getCity_id() : options1Items.get(mPosition[0]).sub.get(mPosition[1]).id)
                .params("area_id", options1Items.isEmpty() ? mDataBean.getArea_id() : options1Items.get(mPosition[0]).sub.get(mPosition[1]).sub.get(mPosition[2]).id)
                .params("company_trade", "保洁清洗qqq")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        mLoadView.setVisibility(View.GONE);
                        if (response.body().contains("0")) {
                            finish();
                            Logger.e("发布成功+" + response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mLoadView.setVisibility(View.GONE);
                        asyncShowToast(response.message());
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
        // /storage/emulated/0/DCIM/Screenshots/Screenshot_2018-06-26-22-09-50-699_chinsoft.water.png
        //upLoadPic("");
    }

    private void upLoadPic(String urls, final boolean isUpdate) {
        OkGo.<String>post(Constant.UP_LOAD_PIC)
                .params("base64", urls)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        UploadImage uploadImage = GsonUtils.jsonFromJson(response.body(), UploadImage.class);
                        if (uploadImage.code.equals("0")) {
                            upLoadPics.add(uploadImage.data.file);
                            if (isUpdate) {
                                fetchCompayData();
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mLoadView.setVisibility(View.GONE);
                    }
                });
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

    private boolean isCanUpload() {
        String companyName = etMineCompanysetInputCompany.getText().toString().trim();  //公司名称
        String detailAddress = etMineCompanysetDetailAddress.getText().toString().trim();  //详细地址
        String fromIndustry = etMineCaompanysetToIndustry.getText().toString().trim();  //所属行业
        String fromArea = etMineCompanysetSelectArea.getText().toString().trim();  //所属地区
        String connectName = etMineCompanysetWritContactName.getText().toString().trim(); //联系人姓名
        String connectPhone = etMineCompanysetMobilePhoneNumber.getText().toString().trim(); //联系人手机
        String connectCall = etMineCompanysetZuojiPhoneNumber.getText().toString().trim(); //联系人电话
        String connectQQ = etMineCompanysetWriteQQ.getText().toString().trim(); //联系人QQ
        String companyLowshow = et_mine_companyset_company_lowshow.getText().toString().trim(); //企业简介
        String companyL = et_mine_companyset_compaydetail.getText().toString().trim(); //企业详情
        if (TextUtils.isEmpty(companyName) || TextUtils.isEmpty(detailAddress) || TextUtils.isEmpty(connectName) || TextUtils.isEmpty(connectPhone)
                || TextUtils.isEmpty(connectCall) || TextUtils.isEmpty(connectQQ) || TextUtils.isEmpty(companyLowshow) || TextUtils.isEmpty(fromArea)) {
            asyncShowToast("还有一些信息没有填，仔细检查一下");
            return false;
        }
        return true;
    }
}
