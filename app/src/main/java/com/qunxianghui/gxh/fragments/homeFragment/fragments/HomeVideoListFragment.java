package com.qunxianghui.gxh.fragments.homeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;

public class HomeVideoListFragment extends BaseFragment implements View.OnClickListener, PersonDetailVideoAdapter.VideoListClickListener {
    @BindView(R.id.xrecycler_homevideo_list)
    XRecyclerView xrecyclerHomevideoList;
    Unbinder unbinder;
    private int count = 0;
    private boolean mIsFirst = true;
    private PersonDetailVideoAdapter personDetailVideoAdapter;
    private List<HomeVideoListBean.DataBean.ListBean> videoDataList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homevideo_list;
    }

    @Override
    public void initViews(View view) {
        super.initViews(view);

    }

    @Override
    public void initDatas() {
        xrecyclerHomevideoList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        RequestHomeVideoList();
    }


    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerHomevideoList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                videoDataList.clear();
                count = 0;
                RequestHomeVideoList();
            }

            @Override
            public void onLoadMore() {
                RequestHomeVideoList();
            }
        });
    }

    private void RequestHomeVideoList() {
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
                xrecyclerHomevideoList.setAdapter(personDetailVideoAdapter);

                personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                        intent.putExtra("url", videoDataList.get(position - 1).getUrl());
                        intent.putExtra("uuid", videoDataList.get(position - 1).getUuid());
                        startActivity(intent);
                    }
                });
                xrecyclerHomevideoList.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
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
            xrecyclerHomevideoList.refreshComplete();

            personDetailVideoAdapter.notifyItemRangeChanged(count, homeVideoListBean.getData().getList().size());
        }
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

    @Override
    public void onClick(View v) {

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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
