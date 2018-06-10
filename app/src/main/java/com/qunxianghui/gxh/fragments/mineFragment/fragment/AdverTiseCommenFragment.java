package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.RefreshRecyclerAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.SelfTestRecyclerviewAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by user on 2018/6/10.
 */

public class AdverTiseCommenFragment extends BaseFragment {
    @BindView(R.id.xrecycler_addver_commen)
    XRecyclerView xrecyclerAddverCommen;
    Unbinder unbinder;


    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_addvertise_common;
    }

    @Override
    public void initDatas() {
        SelfTestRecyclerviewAdapter selfTestRecyclerviewAdapter = new SelfTestRecyclerviewAdapter();
        xrecyclerAddverCommen.setAdapter(selfTestRecyclerviewAdapter);

    }

    @Override
    public void initViews(View view) {
        xrecyclerAddverCommen.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {

        xrecyclerAddverCommen.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {

                xrecyclerAddverCommen.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerAddverCommen.refreshComplete();
            }
        });
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
