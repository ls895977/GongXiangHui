package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.TreeRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.item.ItemHelperFactory;
import com.qunxianghui.gxh.item.TreeRecyclerType;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity {

    @BindView(R.id.recyclerview_location_city)
    RecyclerView recyclerviewLocationCity;
    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND);
    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_location;
    }

    @Override
    protected void initViews() {
        recyclerviewLocationCity.setLayoutManager(new GridLayoutManager(mContext,3));
        recyclerviewLocationCity.setAdapter(treeRecyclerAdapter);
    }

    @Override
    protected void initData() {
        new Thread() {
            @Override
            public void run() {
                super.run();

                OkGo.<String>post(Constant.FETCH_COUNTRY_ADRESS).execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        String message = response.message();
                        String string = getResources().getString(R.string.location);
                        List<ProvinceBean> cityBeen = JSON.parseArray(string, ProvinceBean.class);
                        refresh(cityBeen);
                    }
                });

        }
    }.start();

}

    private void refresh(final List<ProvinceBean> cityBeen) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                treeRecyclerAdapter.getItemManager().replaceAllItem(ItemHelperFactory.createItems(cityBeen, null));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
