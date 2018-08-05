package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.CompanyCardBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyCardActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_companycard_back)
    ImageView mIvCompanycardBack;
    @BindView(R.id.iv_companycard_share)
    ImageView mIvCompanycardShare;
    @BindView(R.id.ll_companycard_diamond)
    LinearLayout mLlCompanycardDiamond;
    @BindView(R.id.rl_companycard_center_advance)
    RelativeLayout mRlCompanycardCenterAdvance;
    @BindView(R.id.ll_companycard_goodselect)
    LinearLayout mLlCompanycardGoodselect;
    @BindView(R.id.rl_companycard_company_product)
    RelativeLayout mRlCompanycardCompanyProduct;
    @BindView(R.id.tv_company_card_name)
    TextView mTvCompanyCardName;
    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.iv_companycard_editlocation)
    ImageView ivCompanycardEditlocation;
    @BindView(R.id.rl_mine_person_data)
    LinearLayout mLlMinePersonData;
    @BindView(R.id.tv_company_mobile)
    TextView tvCompanyMobile;
    @BindView(R.id.tv_company_card_username)
    TextView mTvCompanyCardUsername;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_card;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
        OkGo.<String>post(Constant.MINE_COMPANY_CARD_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {

                parseCompanyCardData(response.body());
            }
            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Logger.e("获取失败了" + response.body().toString());
            }
        });

    }

    private void parseCompanyCardData(String body) {
        CompanyCardBean companyCardBean = GsonUtils.jsonFromJson(body, CompanyCardBean.class);
        int code = companyCardBean.getCode();
        CompanyCardBean.DataBean dataList = companyCardBean.getData();
        String address = dataList.getAddress();
        String company_name = dataList.getCompany_name();
        String duty = dataList.getDuty();
        String email = dataList.getEmail();
        String mobile = dataList.getMobile();
        String username = dataList.getUsername();
        if (code == 200) {
            tvCompanyMobile.setText(mobile);
            mTvCompanyCardName.setText(company_name);
            mTvCompanyCardUsername.setText(username);
        }

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mIvCompanycardBack.setOnClickListener(this);
        mIvCompanycardShare.setOnClickListener(this);
        mRlCompanycardCenterAdvance.setOnClickListener(this);
        mRlCompanycardCompanyProduct.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_companycard_back:
                finish();
                break;
            case R.id.rl_companycard_company_product:
                asyncShowToast("点击了公司产品");
                break;
            case R.id.iv_companycard_share:
                asyncShowToast("点击了分享");
                break;
            case R.id.iv_companycard_editlocation:
                asyncShowToast("点击了编辑地址");
                break;
            case R.id.rl_companycard_center_advance:
                toActivity(ComPanyAdvanceActivity.class);
                break;
        }
    }
}
