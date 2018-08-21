package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinCallGridAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CommoentCallActivity extends BaseActivity {

    private int[] images = {R.mipmap.icon_commen_call_police, R.mipmap.icon_commen_call_jinjicenter, R.mipmap.icon_commen_call_firepolice
            , R.mipmap.icon_commen_call_jinji, R.mipmap.icon_commen_call_jiaotong,

            R.mipmap.icon_commen_call_police, R.mipmap.icon_commen_call_jinjicenter, R.mipmap.icon_commen_call_firepolice
            , R.mipmap.icon_commen_call_jinji, R.mipmap.icon_commen_call_jiaotong,

            R.mipmap.icon_commen_call_police, R.mipmap.icon_commen_call_jinjicenter, R.mipmap.icon_commen_call_firepolice


    };

    private int[] imagesCall = {R.mipmap.bianmin_call, R.mipmap.bianmin_call, R.mipmap.bianmin_call
            , R.mipmap.bianmin_call, R.mipmap.bianmin_call,

            R.mipmap.bianmin_call, R.mipmap.bianmin_call, R.mipmap.bianmin_call
            , R.mipmap.bianmin_call, R.mipmap.bianmin_call,

            R.mipmap.bianmin_call, R.mipmap.bianmin_call, R.mipmap.bianmin_call


    };
    private String[] iconName = {"警匪", "急救中心", "火警", "紧急或报警电话", "交通事故报警", "高速公路报警救援", "市民专线",
            "消费者投诉", "全民住房公积金热线", "人力资源社会保障热线", "供电服务", "114电话导航", "天气预报"};

    private String[] NameCall = {"110", "120", "119", "112", "122", "12122", "12345",
            "12315", "12329", "12333", "95598", "114", "12121"};

    @BindView(R.id.recycler_comment_call)
    RecyclerView recyclerCommentCall;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_call;
    }

    @Override
    protected void initViews() {
        recyclerCommentCall.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        BianMinCallGridAdapter homegridNavigator = new BianMinCallGridAdapter(mContext, images, imagesCall,iconName,NameCall);
        recyclerCommentCall.setAdapter(homegridNavigator);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
