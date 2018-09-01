package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueLocalServiceAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonLocalServiceFragment extends BaseFragment {

    @BindView(R.id.xrecycler_persondetail_post)
    XRecyclerView xrecyclerPersondetailPost;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;
    Unbinder unbinder;
    private List<MineIssueLocalServiceBean.DataBean> mList = new ArrayList<>();
    private int mSkip = 0;
    private MyIssueLocalServiceAdapter mAdapter;

    @Override
    protected void onLoadData() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_persondetail_post;
    }

    @Override
    public void initData() {
        PersonDetailActivity personDetailActivity = (PersonDetailActivity) getActivity();
        OkGo.<MineIssueLocalServiceBean>post(Constant.MYISSURE_LOCAL_SERVICE_URL)
                .params("limit", 10)
                .params("skip", mSkip)
                .params("member_id", personDetailActivity.member_id)
                .execute(new JsonCallback<MineIssueLocalServiceBean>() {
                    @Override
                    public void onSuccess(Response<MineIssueLocalServiceBean> response) {
                        parseData(response.body());
                    }
                });
    }

    private void parseData(MineIssueLocalServiceBean data) {
        xrecyclerPersondetailPost.refreshComplete();
        xrecyclerPersondetailPost.loadMoreComplete();
        if (data.getCode() == 200) {
            if(mSkip == 0) {
                mList.clear();
            }
            mList.addAll(data.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void initViews(View view) {
        xrecyclerPersondetailPost.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mAdapter = new MyIssueLocalServiceAdapter(getContext(), mList);
        xrecyclerPersondetailPost.setAdapter(mAdapter);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        /**
         * 下拉刷新和上拉加载
         */
        xrecyclerPersondetailPost.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //xrecyclerPersondetailPost.refreshComplete();
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                //xrecyclerPersondetailPost.refreshComplete();
                mSkip += 10;
                initData();
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
