package com.qunxianghui.gxh.ui.fragments.homeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeVideoSearchAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @time 2018/5/28  18:07
 * @desc 搜索的页面
 */
public class SearchVideoFragment extends BaseFragment implements HomeVideoSearchAdapter.VideoSearchListClickListener {
    public static final String DATA = "data";
    @BindView(R.id.recyclerview_video)
    XRecyclerView mRecyclerview;
    private HomeVideoSearchBean mBean;
    private List<HomeVideoSearchBean.DataBean> mSearchVideodata = new ArrayList<>();
    private HomeVideoSearchAdapter mAdapter;
    private int mPage;
    private boolean mIsRefresh = false;
    private String mKeyWords;
    public static SearchVideoFragment newInstance(String data) {
        SearchVideoFragment fragment = new SearchVideoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(SearchVideoFragment.DATA, data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_video_search;
    }
    @Override
    public void initViews(View view) {
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        mKeyWords = getArguments().getString(DATA);
        if (!TextUtils.isEmpty(mKeyWords)) {
            goNextWorks(mKeyWords);
        }
    }

    /**
     * 子类在此方法中实现数据的初始化
     */
    @Override
    public void initData() {
        mAdapter = new HomeVideoSearchAdapter(mActivity, mSearchVideodata);
        mAdapter.setVideoSearchListClickListener(this);
        mRecyclerview.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                int uuid = mBean.getData().get(position-1).getUuid();
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                intent.putExtra("uuid", uuid);
                intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initListeners() {
        mRecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mPage = 0;
                goNextWorks(mKeyWords);
            }

            @Override
            public void onLoadMore() {
                goNextWorks(mKeyWords);
            }
        });
    }
    /**
     * ==================请求网络=====================
     */
    private void goNextWorks(String trim) {
        OkGo.<HomeVideoSearchBean>get(Constant.SEARCH_GET_VIDEO_LIST).
                params("keywords", trim).
                params("limit", 10)
                .params("skip", mPage).
                execute(new JsonCallback<HomeVideoSearchBean>() {
                    @Override
                    public void onSuccess(Response<HomeVideoSearchBean> response) {
                        parseData(response.body());
                    }

                    @Override
                    public void onError(Response<HomeVideoSearchBean> response) {
                        super.onError(response);
                        mRecyclerview.setLoadingMoreEnabled(false);
                    }
                });
    }
    //设置数据
    private void parseData(HomeVideoSearchBean body) {
        if (mIsRefresh) {
            mIsRefresh = false;
            mSearchVideodata.clear();
        }
        mBean = body;
        mSearchVideodata.addAll(mBean.getData());
        mPage++;
        int code = mBean.getCode();
        if (code == 200) {
            mRecyclerview.refreshComplete();
            mAdapter.notifyDataSetChanged();
        }
    }

    /*搜索的贴片的点击事件 */
    @Override
    public void PasterClick(int position) {
        Intent intent = new Intent(mActivity, AddTiePianAdvertActivity.class);
        StringBuilder sb = new StringBuilder(Constant.VIDEO_DETAIL_URL);
        sb.append("?token=").append(SPUtils.getString(SpConstant.ACCESS_TOKEN, "")).append("&uuid=").append(mSearchVideodata.get(position).getUuid());
        intent.putExtra("url", sb.toString());
        startActivity(intent);
    }

    /*搜索视频的关注*/
    @Override
    public void SearchVideoClick(final int position) {
        OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                .params("be_member_id", mSearchVideodata.get(position).getMember_id())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        String msg = response.body().msg;
                        if (code == 0) {
                            asyncShowToast(msg);
                            mSearchVideodata.get(position).setFollow("true");
                        } else if (code == 202) {
                            asyncShowToast(msg);
                            mSearchVideodata.get(position).setFollow("");
                        }else if (code==101){
                            asyncShowToast(msg);
                        }
                        mAdapter.notifyDataSetChanged();
                    }

                });
    }

    /*搜索视频的喜欢*/
    @Override
    public void SearchVideoLikeClick(final int position) {
        OkGo.<CommonBean>post(Constant.VIDEO_LIKE_URL)
                .params("data_uuid", mSearchVideodata.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        HomeVideoSearchBean.DataBean videoSearchDataBean = mSearchVideodata.get(position);
                        int like_cnt = videoSearchDataBean.getLike_cnt();

                        int code = response.body().code;
                        if (code == 100) {
                            asyncShowToast("点赞成功");
                            videoSearchDataBean.setIs_like(1);
                            videoSearchDataBean.setLike_cnt(++like_cnt);
                        } else if (code == 101) {
                            asyncShowToast("取消点赞");
                            videoSearchDataBean.setIs_like(0);
                            videoSearchDataBean.setLike_cnt(--like_cnt);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

}
