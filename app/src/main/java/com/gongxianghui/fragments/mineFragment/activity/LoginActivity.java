package com.gongxianghui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @BindView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户登录");
    }

    @Override
    protected void initDatas() {



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login_login, R.id.tv_login_regist, R.id.tv_login_forget_password})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt_login_login:
                break;
            case R.id.tv_login_regist:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_login_forget_password:
                intent = new Intent(this, SeekPasswordActivity.class);
                startActivity(intent);
                break;



        }
    }
}

