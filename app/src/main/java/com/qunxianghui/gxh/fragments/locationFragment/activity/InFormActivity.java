package com.qunxianghui.gxh.fragments.locationFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InFormActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_inform_back)
    ImageView ivInformBack;
    @BindView(R.id.iv_inform_close)
    ImageView ivInformClose;
    @BindView(R.id.ll_infprm_remember)
    LinearLayout llInfprmRemember;
    @BindView(R.id.et_inform_content)
    EditText etInformContent;
    @BindView(R.id.tv_inform_inform)
    TextView tvInformInform;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListeners() {
        ivInformBack.setOnClickListener(this);
        ivInformClose.setOnClickListener(this);
        tvInformInform.setOnClickListener(this);
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
            case R.id.iv_inform_back:
                finish();
                break;
            case R.id.tv_inform_inform:
                asyncShowToast("举报成功");
                break;
            case R.id.iv_inform_close:
                llInfprmRemember.setVisibility(View.GONE);

                break;
        }

    }
}
