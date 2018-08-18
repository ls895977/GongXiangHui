package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.TreeRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.Address;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.item.BaseRecyclerAdapter;
import com.qunxianghui.gxh.item.ItemHelperFactory;
import com.qunxianghui.gxh.item.TreeRecyclerType;
import com.qunxianghui.gxh.item.ViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

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
        recyclerviewLocationCity.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerviewLocationCity.setAdapter(treeRecyclerAdapter);
        treeRecyclerAdapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(ViewHolder viewHolder, int position) {
                return true;
            }
        });
    }

    @Override
    protected void initData() {
        System.out.println(System.currentTimeMillis());
        OkGo.<Address>post(Constant.FETCH_COUNTRY_ADRESS).execute(new JsonCallback<Address>() {
            @Override
            public void onSuccess(Response<Address> response) {
                System.out.println(System.currentTimeMillis());
                treeRecyclerAdapter.getItemManager().replaceAllItem(ItemHelperFactory.createItems(response.body().data, null));
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
