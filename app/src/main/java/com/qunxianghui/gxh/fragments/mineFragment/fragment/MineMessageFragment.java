package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageFragment extends BaseFragment {

    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.xrecycler_mineMessage)
    XRecyclerView xrecyclerMineMessage;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minemessage;
    }

    @Override
    public void initDatas() {

        for (int i = 0; i < 30; i++) {
            mDatas.add(i, i + 1 + " ");
        }
        final MineMessageAdapter mineMessageAdapter = new MineMessageAdapter(mActivity, mDatas);
        mineMessageAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(mActivity, "点击了:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        xrecyclerMineMessage.setAdapter(mineMessageAdapter);
    }

    @Override
    public void initViews(View view) {
        xrecyclerMineMessage.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        xrecyclerMineMessage.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerMineMessage.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerMineMessage.refreshComplete();
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
