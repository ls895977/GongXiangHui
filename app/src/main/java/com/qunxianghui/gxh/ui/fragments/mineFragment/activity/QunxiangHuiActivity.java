package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QunxiangHuiActivity extends BaseActivity {
    @BindView(R.id.tv_qunxianghui)
    TextView tvQunxianghui;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_qunxianghui;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int tag = intent.getIntExtra("tag", 0);
        if (tag == 1) {
            tvQunxianghui.setText("群享汇");
        }else if (tag==2){
            tvQunxianghui.setText("群享汇平台");
        }else if (tag==3){
            tvQunxianghui.setText("群享汇分享云");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
