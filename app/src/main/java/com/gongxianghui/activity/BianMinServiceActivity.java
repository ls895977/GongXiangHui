package com.gongxianghui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.adapter.homeAdapter.BianMinGridAdapter;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.config.Constant;
import com.gongxianghui.fragments.homeFragment.activity.ProtocolActivity;
import com.gongxianghui.interfaces.OnItemClickListener;
import com.gongxianghui.widget.TitleBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class BianMinServiceActivity extends BaseActivity {

    private int[] images = {R.mipmap.bianmin_call, R.mipmap.bianmin_check_express, R.mipmap.bianmin_consign_express
            , R.mipmap.bianmin_phone_dollar, R.mipmap.bianmin_bus_check, R.mipmap.bianmin_car_piao, R.mipmap.bianmin_train, R.mipmap.bianmin_air, R.mipmap.bianmin_calendar, R.mipmap.bianmin_hotel, R.mipmap.bianmin_see_work, R.mipmap.bianmin_see_work};
    private String[] iconName = {"常用电话", "查快递", "寄快递", "充话费", "公交查询"
            , "汽车票", "火车票", "飞机票", "农历黄历", "酒店住宿", "找工作", "常用银行"};

    @BindView(R.id.grid_bianmin)
    GridView gridBianmin;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_bianmin;
    }

    @Override
    protected void initViews() {
        final BianMinGridAdapter bianMinGridAdapter = new BianMinGridAdapter(mContext, images, iconName);

        gridBianmin.setAdapter(bianMinGridAdapter);


    }


    @Override
    protected void initDatas() {
        new TitleBuilder(this).setTitleText("便民服务").setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        gridBianmin.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                intent.putExtra("title", iconName[position]);
                intent.putExtra("url", Constant.COMMON_PHONE);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initListeners() {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}
