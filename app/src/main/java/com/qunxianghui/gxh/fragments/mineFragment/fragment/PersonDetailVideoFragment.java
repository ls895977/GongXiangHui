package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PersonDetailVideoFragment extends BaseFragment {
    @BindView(R.id.xrecycler_persondetail_video)
    XRecyclerView xrecyclerPersondetailVideo;
    Unbinder unbinder;
    private PersonDetailActivity personDetailActivity;

    @Override
    protected void onLoadData() {
        OkGo.<String>post(Constant.HOME_VIDEO_LIST_URL)
                .params("user_id",personDetailActivity.member_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parsePersonDetailVideoData(response.body());

                    }
                });

    }

    /**
     * 解析视频列表
     * @param body
     */
    private void parsePersonDetailVideoData(String body) {
        final HomeVideoListBean homeVideoListBean = GsonUtils.jsonFromJson(body, HomeVideoListBean.class);
        if (homeVideoListBean.getCode()==0){
            final List<HomeVideoListBean.DataBean.ListBean> videoList = homeVideoListBean.getData().getList();
            final PersonDetailVideoAdapter personDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity, videoList);

            xrecyclerPersondetailVideo.setAdapter(personDetailVideoAdapter);

            personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent=new Intent(mActivity, NewsDetailActivity.class);

                    intent.putExtra("url",videoList.get(position-1).getUrl());
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
    public void initDatas() {
        personDetailActivity = (PersonDetailActivity) getActivity();


    }

    @Override
    public void initViews(View view) {
        xrecyclerPersondetailVideo.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
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
