package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lljjcoder.Constant;
import com.lljjcoder.style.citythreelist.CityBean;
import com.lljjcoder.style.citythreelist.ProvinceActivity;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.logging.Logger;

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
    @BindView(R.id.et_mine_companyset_selectProvince)
    EditText etMineCompanysetSelectProvince;

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



    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_company_set;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListeners() {
        etMineCaompanysetToIndustry.setOnClickListener(this);
        etMineCompanysetSelectProvince.setOnClickListener(this);
        tvMmineCompanysetFabu.setOnClickListener(this);
//        etMineCompanysetSelectCity.setOnClickListener(this);
//        etMineCompanysetSelectCounty.setOnClickListener(this);

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
            case R.id.et_mine_companyset_selectProvince:
                list();
                break;
//            case R.id.et_mine_companyset_selectCity:
//                asyncShowToast("选择城市");
//                break;
//            case R.id.et_mine_companyset_selectCounty:
//                asyncShowToast("选择县区");
//                break;
            case R.id.tv_mine_companyset_fabu:
           fetchCompayData();
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

      OkGo.<String>post(com.qunxianghui.gxh.config.Constant.ADD_COMPANY_URL).
              params("company_name",companyName)
              .params("address",detailAddress)
              .params("linkname",connectName)
              .params("mobile",connectPhone)
              .params("tel",connectCall)
              .params("qq",connectQQ)
              .params("images","")
              .params("description",companyLowshow)
              .params("content",companyL)
              .params("province_id","")
              .params("city_id","")
              .params("area_id","")
              .execute(new StringCallback() {
                  @Override
                  public void onSuccess(Response<String> response) {
                      com.orhanobut.logger.Logger.e("发布成功+"+response.body().toString());
                  }

                  @Override
                  public void onError(Response<String> response) {
                      super.onError(response);
                      asyncShowToast(response.message());
                  }
              });

    }

    private void list() {
        Intent intent = new Intent(mContext, ProvinceActivity.class);
        startActivityForResult(intent, ProvinceActivity.RESULT_DATA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ProvinceActivity.RESULT_DATA) {
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    return;
                }

                CityBean area = data.getParcelableExtra("area");
                CityBean city = data.getParcelableExtra("city");
                CityBean province = data.getParcelableExtra("province");

                etMineCompanysetSelectProvince.setText( province.getName()+" ."+ city.getName()+" ."+ area.getName());
            }
        }
    }
}
