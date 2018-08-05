package com.qunxianghui.gxh.ui.fragments.homeFragment.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;

public class HomeVideoListFragment extends BaseFragment implements PersonDetailVideoAdapter.VideoListClickListener {

    @BindView(R.id.xrv)
    XRecyclerView mRv;

    private int count = 0;
    private boolean mIsFirst = true;
    private PersonDetailVideoAdapter personDetailVideoAdapter;
    private List<HomeVideoListBean.DataBean.ListBean> videoDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homevideo_list;
    }

    @Override
    public void initData() {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        requestHomeVideoList();
    }

    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                videoDataList.clear();
                count = 0;
                requestHomeVideoList();
            }

            @Override
            public void onLoadMore() {
                requestHomeVideoList();
            }
        });
    }

    private void requestHomeVideoList() {
        OkGo.<String>post(Constant.HOME_VIDEO_LIST_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parsePersonDetailVideoData(response.body());
                    }
                });
    }

    private void parsePersonDetailVideoData(String body) {
        HomeVideoListBean homeVideoListBean = GsonUtils.jsonFromJson(body, HomeVideoListBean.class);
        videoDataList.addAll(homeVideoListBean.getData().getList());
        count = videoDataList.size();
        if (homeVideoListBean.getCode() == 0) {
            if (mIsFirst) {
                mIsFirst = false;
                personDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity, videoDataList);
                personDetailVideoAdapter.setVideoListClickListener(this);
                mRv.setAdapter(personDetailVideoAdapter);

                personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                        intent.putExtra("url", videoDataList.get(position - 1).getUrl());
                        intent.putExtra("uuid", videoDataList.get(position - 1).getUuid());
                        startActivity(intent);
                    }
                });
                mRv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
                    @Override
                    public void onChildViewAttachedToWindow(View view) {
                    }

                    @Override
                    public void onChildViewDetachedFromWindow(View view) {
                        JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
                        if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                            JZVideoPlayer currentJzvd = JZVideoPlayerManager.getCurrentJzvd();
                            if (currentJzvd != null && currentJzvd.currentScreen != JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN) {
                                JZVideoPlayer.releaseAllVideos();
                            }
                        }
                    }
                });
            }
            mRv.refreshComplete();
            personDetailVideoAdapter.notifyItemRangeChanged(count, homeVideoListBean.getData().getList().size());
        }
    }

    @Override
    public void attentionClick(final int position) {
        OkGo.<String>post(Constant.ATTENTION_URL).params("be_member_id", videoDataList.get(position).getMember_id())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            final int code = jsonObject.getInt("code");
                            if (code == 0) {
                                asyncShowToast("关注成功");
                                videoDataList.get(position).setFollow("true");
                            } else if (code == 202) {
                                videoDataList.get(position).setFollow("");
                                asyncShowToast("取消关注成功");
                            }
                            personDetailVideoAdapter.notifyItemChanged(position + 1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("视频关注" + response.body().toString());
                    }

                });

    }

    @Override
    public void videoHeadImageClick(int position) {
        Intent intent = new Intent(mActivity, PersonDetailActivity.class);
        intent.putExtra("member_id", videoDataList.get(position).getMember_id());
        startActivity(intent);
    }

    @Override
    public void videoLikeItemClick(int position) {
        OkGo.<String>post(Constant.LIKE_URL).params("data_uuid", videoDataList.get(position).getUuid()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    JSONObject data = jsonObject.getJSONObject("data");
                    JSONObject like_one_res = data.getJSONObject("like_one_res");
                    if (like_one_res.toString() != null) {
                        asyncShowToast("点赞成功");
                    } else {
                        asyncShowToast("取消点赞");

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                personDetailVideoAdapter.notifyDataSetChanged();
            }
        });
    }


}
