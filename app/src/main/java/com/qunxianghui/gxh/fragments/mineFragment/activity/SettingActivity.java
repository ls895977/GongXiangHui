package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/13 0013.
 */

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_setting_quit)
    Button btSettingQuit;

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

    @Override
    protected void initListeners() {
        btSettingQuit.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.bt_setting_quit:
                //*此按钮可点击时已经是登录状态*/
                new AlertDialog.Builder(mContext)
                        .setTitle("是否退出登录")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
             /* 清楚登录信息*/
                            finish();
                            }
                        }).setNegativeButton("否",null).show();
                break;
        }

    }
}
