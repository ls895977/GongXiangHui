package com.gongxianghui.fragments.mineFragment.activity;

import android.view.View;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class SettingActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {

        return R.layout.activity_set;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("设置").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
