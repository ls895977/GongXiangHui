package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

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
                asyncShowToast("点击了核心优势");
                break;
        }
    }
}
