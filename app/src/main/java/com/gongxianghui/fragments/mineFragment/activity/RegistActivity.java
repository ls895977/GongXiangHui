package com.gongxianghui.fragments.mineFragment.activity;

import android.view.View;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class RegistActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户注册");

    }

    @Override
    protected void initDatas() {

    }
}

