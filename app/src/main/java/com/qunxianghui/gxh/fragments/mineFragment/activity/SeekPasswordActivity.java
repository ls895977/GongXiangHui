package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class SeekPasswordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_seek_password_next)
    Button btSeekPasswordNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seek_password;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("密码找回");
    }

    @Override
    protected void initDatas() {
        btSeekPasswordNext.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){


        }
    }
}
