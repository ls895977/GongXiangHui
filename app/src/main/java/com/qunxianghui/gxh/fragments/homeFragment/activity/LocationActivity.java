package com.qunxianghui.gxh.fragments.homeFragment.activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeLocationBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity {

    @BindView(R.id.recyclerview_location_city)
    RecyclerView recyclerviewLocationCity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_location;
    }

    @Override
    protected void initViews() {
        recyclerviewLocationCity.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void initDatas() {
        OkGo.<String> get(Constant.CITY_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ParseHomeLocationData(response.body());
            }
        });

    }

    private void ParseHomeLocationData(String body) {
        Logger.d("城市列表+++"+body.toString());
        final HomeLocationBean homeLocationBean = GsonUtils.jsonFromJson(body, HomeLocationBean.class);
        if (homeLocationBean.getCode()==0){

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
