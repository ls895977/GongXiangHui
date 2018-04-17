package com.gongxianghui.fragments.mineFragment.activity;

import android.view.View;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_up;
    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {
        new TitleBuilder(MemberUpActivity.this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("产品介绍");

    }
}
