package com.gongxianghui.fragments.homeFragment.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gongxianghui.R;
import com.gongxianghui.adapter.homeAdapter.HomeSalerListAdapter;
import com.gongxianghui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class OrderStatusFragment extends BaseFragment {
    @BindView(R.id.rv_saler_list)
    RecyclerView rvSalerList;
    Unbinder unbinder;
    private List<String> mDatas;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_saler_order_status;
    }

    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        for(int i=0;i<50;i++){
            mDatas.add(i,i+1+"");
        }
        rvSalerList.setLayoutManager(new LinearLayoutManager(mActivity));
        HomeSalerListAdapter adapter = new HomeSalerListAdapter(mActivity, mDatas);
        rvSalerList.setAdapter(adapter);

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

