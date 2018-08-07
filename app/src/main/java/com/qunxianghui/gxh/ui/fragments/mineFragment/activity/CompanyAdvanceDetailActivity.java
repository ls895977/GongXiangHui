package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyAdvanceDetailActivity extends BaseActivity {
    @BindView(R.id.iv_add_advance_back)
    ImageView ivAddAdvanceBack;
    @BindView(R.id.tv_add_advance_save)
    TextView tvAddAdvanceSave;
    @BindView(R.id.et_add_advance_title)
    EditText etAddAdvanceTitle;
    @BindView(R.id.et_add_advance_introduce)
    EditText etAddAdvanceIntroduce;
    @BindView(R.id.iv_add_advance_pic)
    ImageView ivAddAdvancePic;
    @BindView(R.id.tv_add_advance_delete)
    TextView tvAddAdvanceDelete;
    @BindView(R.id.rl_add_advance_edit)
    RelativeLayout rlAddAdvanceEdit;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_advance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
