package com.gongxianghui.activity;

import android.view.View;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class BianMinActiviry extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_bianmin;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("便民服务").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
