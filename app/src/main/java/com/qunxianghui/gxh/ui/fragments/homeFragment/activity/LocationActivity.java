package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.TreeRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.Address;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.item.AreaItem;
import com.qunxianghui.gxh.item.ItemHelperFactory;
import com.qunxianghui.gxh.item.TreeRecyclerType;
import com.qunxianghui.gxh.utils.SPUtils;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity {

    @BindView(R.id.tv_current_city)
    TextView mTvCurrentCity;
    @BindView(R.id.recyclerview_location_city)
    RecyclerView recyclerviewLocationCity;
    @BindView(R.id.ll_bg_load)
    View mLoadView;

    TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter(TreeRecyclerType.SHOW_EXPAND);

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home_location;
    }

    @Override
    protected void initViews() {
        mLoadView.setVisibility(View.VISIBLE);
        recyclerviewLocationCity.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerviewLocationCity.setAdapter(treeRecyclerAdapter);
        String currcity = SPUtils.getLocation("currcity");
        mTvCurrentCity.setText(TextUtils.isEmpty(currcity) ? SPUtils.getLocation("X-cityName") : currcity);
    }

    @Override
    protected void initData() {
        System.out.println(System.currentTimeMillis());
        OkGo.<Address>post(Constant.FETCH_COUNTRY_ADRESS).execute(new JsonCallback<Address>() {
            @Override
            public void onSuccess(Response<Address> response) {
                mLoadView.setVisibility(View.GONE);
                System.out.println(System.currentTimeMillis());
                treeRecyclerAdapter.getItemManager().replaceAllItem(ItemHelperFactory.createItems(response.body().data, null));
            }

            @Override
            public void onError(Response<Address> response) {
                super.onError(response);
                mLoadView.setVisibility(View.GONE);
            }
        });
        Callback callback = new Callback();
        callback.mre = new WeakReference<>(this);
        AreaItem.sCallback = callback;
    }

    @OnClick({R.id.iv_back, R.id.tv_current_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_current_address:
                break;
        }
    }

    public void callback(ProvinceBean.CityBean.AreasBean areasBean) {
        SPUtils.saveLocation("currcity", areasBean.areaName);
        SPUtils.saveLocation("X-cityId", areasBean.pid);
        SPUtils.saveLocation("X-areaId", areasBean.areaId);
        mTvCurrentCity.setText(areasBean.areaName);
        finish();
    }

    public static class Callback implements AreaItem.Callback {

        private WeakReference<LocationActivity> mre;

        @Override
        public void callback(ProvinceBean.CityBean.AreasBean areasBean) {
            if (mre != null && mre.get() != null) {
                mre.get().callback(areasBean);
            }
        }
    }

    @Override
    protected void onDestroy() {
        AreaItem.sCallback = null;
        super.onDestroy();
    }
}
