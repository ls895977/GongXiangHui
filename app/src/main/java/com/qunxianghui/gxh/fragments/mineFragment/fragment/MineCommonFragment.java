package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MinCollectAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.CollectBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.OkHttpUtil;
import com.qunxianghui.gxh.widget.GloriousRecyclerView;
import com.qunxianghui.gxh.widget.ScrollChildSwipeRefreshLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment {
    @BindView(R.id.recycler_mine_common)
    GloriousRecyclerView recyclerMineCommon;
    Unbinder unbinder;
    @BindView(R.id.mycollect_refresh_layout)
    ScrollChildSwipeRefreshLayout mycollectRefreshLayout;

    private List<CollectBean.ListBean> data;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initDatas() {
        mycollectRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //这里做刷新的操作
                Toast.makeText(mActivity, "刷新完毕", Toast.LENGTH_SHORT).show();
                //然后调用下面这句话告诉SwipeRefreshLayout已经加载完毕
                mycollectRefreshLayout.setRefreshing(false);

            }
        });


    }


    @Override
    public void initViews(View view) {

        mycollectRefreshLayout.setScrollUpChild(recyclerMineCommon);
        recyclerMineCommon.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        String url = OkHttpUtil.obtainGetUrl(Constant.URL);
        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                String body = response.body();
                CollectBean collectBean = GsonUtil.parseJsonWithGson(body, CollectBean.class);
                data = collectBean.getList();
                MinCollectAdapter minCollectAdapter = new MinCollectAdapter(data, mActivity);
                recyclerMineCommon.setAdapter(minCollectAdapter);

            }


            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                Toast.makeText(mActivity, "加载失败", Toast.LENGTH_SHORT).show();
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
