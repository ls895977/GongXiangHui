package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResetPasswordActivity extends BaseActivity {
    @BindView(R.id.et_resetpassword_newpassword)
    EditText etResetpasswordNewpassword;
    @BindView(R.id.et_resetpassword_conform_new_passworm)
    EditText etResetpasswordConformNewPassworm;
    @BindView(R.id.bt_resetpassword_conform_password)
    Button btResetpasswordConformPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(ResetPasswordActivity.this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("密码找回");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
