package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;

import java.util.List;

import butterknife.BindView;

public class PersonDetailVideoFragment extends BaseFragment {

    @BindView(R.id.xrecycler_persondetail_video)
    XRecyclerView xrecyclerPersondetailVideo;
    @BindView(R.id.ll_empty)
    View mEmptyView;

    private PersonDetailActivity personDetailActivity;

    @Override
    protected void onLoadData() {
        OkGo.<HomeVideoListBean>post(Constant.HOME_VIDEO_LIST_URL)
                .params("user_id", personDetailActivity.member_id)
                .execute(new JsonCallback<HomeVideoListBean>() {
                    @Override
                    public void onSuccess(Response<HomeVideoListBean> response) {
                        parsePersonDetailVideoData(response.body());
                    }
                });
    }

    /**
     * 解析视频列表
     *
     * @param
     */
    private void parsePersonDetailVideoData(HomeVideoListBean homeVideoListBean) {
        if (homeVideoListBean.getCode() == 0) {
            final List<HomeVideoListBean.DataBean.ListBean> videoList = homeVideoListBean.getData().getList();
            PersonDetailVideoAdapter personDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity, videoList);
            xrecyclerPersondetailVideo.setAdapter(personDetailVideoAdapter);
            personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("url", videoList.get(position - 1).getUrl());
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_persondetail_video;
    }

    @Override
    public void initData() {
        personDetailActivity = (PersonDetailActivity) getActivity();
    }

    @Override
    public void initViews(View view) {
        xrecyclerPersondetailVideo.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerPersondetailVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrecyclerPersondetailVideo.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecyclerPersondetailVideo.refreshComplete();
            }
        });
    }
}
