package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddProductActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_add_product_back)
    ImageView mIvAddProductBack;
    @BindView(R.id.tv_add_product__save)
    TextView mTvAddProductSave;
    @BindView(R.id.et_add_product_title)
    EditText mEtAddAProductTitle;
    @BindView(R.id.iv_add_product__introduce)
    TextView mTvAddProductIntroduce;
    @BindView(R.id.iv_add_product_pic)
    ImageView mIvAddProductPic;
    @BindView(R.id.rl_add_product_edit)
    RelativeLayout rlAddProductEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_company_cardadd_product;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mIvAddProductPic.setOnClickListener(this);
        mIvAddProductBack.setOnClickListener(this);
        mTvAddProductSave.setOnClickListener(this);
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
            case R.id.iv_add_product_back:
                finish();
                break;
            case R.id.tv_add_product__save:
                asyncShowToast("保存");
                break;
            case R.id.iv_add_product_pic:
                break;


        }
    }
}
