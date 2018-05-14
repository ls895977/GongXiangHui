package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MineMessageAdapter;
import com.qunxianghui.gxh.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageFragment extends BaseFragment {

    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.xrecycler_mineMessage)
    XRecyclerView xrecyclerMineMessage;

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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        linearLayoutManager.setSmoothScrollbarEnabled(false);
        xrecyclerMineMessage.setLayoutManager(linearLayoutManager);
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


}
