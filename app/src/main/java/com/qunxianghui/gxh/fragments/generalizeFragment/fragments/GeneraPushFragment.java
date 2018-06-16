package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.generaAdapter.GeneraPushFragmentAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPushFragment extends BaseFragment implements View.OnClickListener {

    private List<String> datas = new ArrayList<>();
    @BindView(R.id.xrecycler_generapush)
    XRecyclerView xrecyclerGenerapush;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {

        return R.layout.genera_push_eachother;
    }

    @Override
    public void initDatas() {
        xrecyclerGenerapush.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));


        for (int i = 0; i < 10; i++) {
            datas.add(i + 1 + "");
        }
        final GeneraPushFragmentAdapter generaPushFragmentAdapter = new GeneraPushFragmentAdapter(mActivity, datas);
        generaPushFragmentAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(mActivity, "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        xrecyclerGenerapush.setAdapter(generaPushFragmentAdapter);
        xrecyclerGenerapush.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerGenerapush.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerGenerapush.refreshComplete();
            }
        });

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
    protected void onLoadData() {

    }

    @Override
    protected void initListeners() {
        super.initListeners();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
