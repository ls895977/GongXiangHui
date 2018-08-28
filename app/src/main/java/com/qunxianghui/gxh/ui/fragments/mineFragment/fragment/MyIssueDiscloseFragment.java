package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueDiscloseAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyIssueDiscloseFragment extends BaseFragment {

    @BindView(R.id.recycler_mineissue_disclose)
    XRecyclerView mRv;

    private int mSkip = 0;
    private List<MyIssueDiscloseBean.DataBean> mList = new ArrayList<>();
    private MineIssueDiscloseAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issue_disclose;
    }

    @Override
    public void initViews(View view) {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MineIssueDiscloseAdapter(getContext(), mList);
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
        OkGo.<MyIssueDiscloseBean>post(Constant.GET_ISSURE_DISCLOSS_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<MyIssueDiscloseBean>() {
                    @Override
                    public void onSuccess(Response<MyIssueDiscloseBean> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(MyIssueDiscloseBean data) {
        if (data.getCode() == 0) {
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
