package com.qunxianghui.gxh.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinGridAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.CommoentCallActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

/**
 * 首页便民服务界面
 * Created by Administrator on 2018/3/10 0010.
 */

public class BianMinServiceActivity extends BaseActivity {

    private int[] images = {R.mipmap.bianmin_phone, R.mipmap.bianmin_calendar, R.mipmap.bianmin_chakuaidi, R.mipmap.bianmin_send_kuaidi,
            R.mipmap.bianmin_check_bus_ticket, R.mipmap.bianmin_seekjob, R.mipmap.bianmin_car_ticket,
            R.mipmap.bianmin_check_train_ticket, R.mipmap.bianmin_air, R.mipmap.bianmin_hotel};


    private String[] iconName = {"常用电话", "农历黄历", "查快递", "寄快递", "公交查询", "找工作", "汽车票", "火车票", "飞机票", "酒店住宿"};
    @BindView(R.id.grid_bianmin)
    RecyclerView gridBianmin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_service_bianmin;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setTitleText("便民服务").setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        BianMinGridAdapter bianMinGridAdapter = new BianMinGridAdapter(mContext, images, iconName);
        gridBianmin.setAdapter(bianMinGridAdapter);
        bianMinGridAdapter.setOnClickListener(new BianMinGridAdapter.OnItemClickListener() {
            @Override
            public void onpicItemClick(int position) {


                Intent intent = null;
                switch (position) {
                    case 0:
                        intent = new Intent(BianMinServiceActivity.this, CommoentCallActivity.class);
                        break;
                    case 1:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.BIANMIN_HUANGLI_URL);
                        intent.putExtra("tag", 2);

                        break;
                    case 2:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_SEND_KUAIDI_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 3:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_CHECK_KUAIDI_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 4:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_BUS_CHECK_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 5:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_SEE_JOB_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 6:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_CAR_POCKET_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 7:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_TRAIN_POCKET_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 8:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_AIR_POCKET_URL);
                        intent.putExtra("tag", 2);
                        break;
                    case 9:
                        intent = new Intent(BianMinServiceActivity.this, ProtocolActivity.class);
                        intent.putExtra("title", iconName[position]);
                        intent.putExtra("url", Constant.HOME_HOTEL_ZHUSU_URL);
                        intent.putExtra("tag", 2);
                        break;

                }
                startActivity(intent);
            }
        });
    }

}
