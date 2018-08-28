package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueGoodSelectAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueGoodSelectBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyIssueGoodSelectFragment extends BaseFragment {

    @BindView(R.id.xrecler_myissue_goodselect)
    XRecyclerView mRv;

    private int mSkip = 0;
    private List<MyIssueGoodSelectBean.DataBean> mList = new ArrayList<>();
    private MyIssueGoodSelectAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_myissue_goodselect;
    }

    @Override
    public void initViews(View view) {
        mRv.setLayoutManager(new GridLayoutManager(mActivity, 2, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyIssueGoodSelectAdapter(getContext(), mList);
        mRv.setAdapter(mAdapter);
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                initData();
            }
        });
    }

    @Override
    public void initData() {
        OkGo.<MyIssueGoodSelectBean>post(Constant.MYISSURE_GOOD_SELECT_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<MyIssueGoodSelectBean>() {
                    @Override
                    public void onSuccess(Response<MyIssueGoodSelectBean> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(MyIssueGoodSelectBean data) {
        if (data.getCode() == 200) {
            if (mSkip == 0) {
                mList.clear();
                mRv.setLoadingMoreEnabled(true);
            }
            if (data.getData().size() < 10) {
                mRv.setLoadingMoreEnabled(false);
            }
            mList.addAll(data.getData());
            mRv.refreshComplete();
        } else {
            mRv.setLoadingMoreEnabled(false);
        }
        mAdapter.notifyDataSetChanged();
    }
}

