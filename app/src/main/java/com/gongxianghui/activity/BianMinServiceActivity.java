package com.gongxianghui.activity;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.widget.TitleBuilder;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class BianMinServiceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xrecycler_bianMinList)
    XRecyclerView xrecyclerBianMinList;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_service_bianmin;
    }

    @Override
    protected void initViews() {
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4, GridLayoutManager.VERTICAL, false);
        xrecyclerBianMinList.setLayoutManager(gridLayoutManager);

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

    @Override
    protected void initListeners() {

    }


    @Override
    public void onClick(View v) {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
