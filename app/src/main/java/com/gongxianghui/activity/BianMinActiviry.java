package com.gongxianghui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.config.Constant;
import com.gongxianghui.fragments.homeFragment.activity.ProtocolActivity;
import com.gongxianghui.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class BianMinActiviry extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_bianmin_call)
    TextView tvBianminCall;
    @BindView(R.id.tv_bianmin_check_express)
    TextView tvBianminCheckExpress;
    @BindView(R.id.tv_bianmin_consign_express)
    TextView tvBianminConsignExpress;
    @BindView(R.id.tv_bianmin_phone_dollar)
    TextView tvBianminPhoneDollar;
    @BindView(R.id.tv_bianmin_bus_check)
    TextView tvBianminBusCheck;
    @BindView(R.id.tv_bianmin_car_piao)
    TextView tvBianminCarPiao;
    @BindView(R.id.tv_bianmin_train)
    TextView tvBianminTrain;
    @BindView(R.id.tv_bianmin_air)
    TextView tvBianminAir;
    @BindView(R.id.tv_bianmin_calendar)
    TextView tvBianminCalendar;
    @BindView(R.id.tv_bianmin_hotel)
    TextView tvBianminHotel;
    @BindView(R.id.tv_bianmin_see_work)
    TextView tvBianminSeeWork;
    @BindView(R.id.tv_bianmin_bank)
    TextView tvBianminBank;

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

    @Override
    protected void initListeners() {
        tvBianminCall.setOnClickListener(this);
        tvBianminCheckExpress.setOnClickListener(this);
        tvBianminConsignExpress.setOnClickListener(this);
        tvBianminPhoneDollar.setOnClickListener(this);
        tvBianminBusCheck.setOnClickListener(this);
        tvBianminCarPiao.setOnClickListener(this);
        tvBianminTrain.setOnClickListener(this);
        tvBianminAir.setOnClickListener(this);
        tvBianminCalendar.setOnClickListener(this);
        tvBianminHotel.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        String title = "";
        String url = "";
        switch (v.getId()) {
            case R.id.tv_bianmin_call:    //常用电话
                title = tvBianminCall.getText().toString();
                url = Constant.COMMON_PHONE;
                break;
            case R.id.tv_bianmin_check_express:  //查快递
                title = tvBianminCheckExpress.getText().toString();
                url = Constant.CHECK_EXPRESS;
                break;
            case R.id.tv_bianmin_consign_express: //寄快递
                title = tvBianminConsignExpress.getText().toString();
                url = Constant.SEND_EXPRESS;
                break;
            case R.id.tv_bianmin_phone_dollar:  //充话费
                title = tvBianminPhoneDollar.getText().toString();
                url = Constant.RUSH_PHONE_MONEY;
                break;
            case R.id.tv_bianmin_bus_check:        //公交查询
                title = tvBianminBusCheck.getText().toString();
                url = Constant.BUSS_CHECK;
                break;
            case R.id.tv_bianmin_car_piao:      //汽车票
                title = tvBianminCarPiao.getText().toString();
                url = Constant.CAR_TCKET;
                break;
            case R.id.tv_bianmin_train:        //火车票
                title = tvBianminTrain.getText().toString();
                url = Constant.TRAIN_TICKET;
                break;
            case R.id.tv_bianmin_air:          //飞机票
                title = tvBianminAir.getText().toString();
                url = Constant.AIR_TICKET;
                break;
            case R.id.tv_bianmin_calendar:    //农历黄历
                title = tvBianminCalendar.getText().toString();
                url = Constant.ALMANAC;
                break;
            case R.id.tv_bianmin_hotel:        // 酒店住宿
                title = tvBianminHotel.getText().toString();
                url = Constant.HPTEL_ZHUSU;
                break;
        }
        Intent intent=new Intent(mContext,ProtocolActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("url",url);
        startActivity(intent);

    }
}
