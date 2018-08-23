package com.qunxianghui.gxh.ui.fragments.homeFragment.fragments;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

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
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;
import com.qunxianghui.gxh.utils.SPUtils;

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
    private int mPage;
    private int mCateId;
    private PersonDetailVideoAdapter personDetailVideoAdapter;
    private List<HomeVideoListBean.DataBean.ListBean> videoDataList = new ArrayList<>();
    private TextView mMTvComment;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homevideo_list;
    }

    @Override
    public void initViews(View view) {
        mRv.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
        personDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity, videoDataList);
        mRv.setAdapter(personDetailVideoAdapter);
    }

    @Override
    public void initData() {
        mPage = 0;
        mCateId = getArguments().getInt("channel_id");
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
                mPage = 0;
                requestHomeVideoList();
            }

            @Override
            public void onLoadMore() {
                requestHomeVideoList();
            }
        });

        personDetailVideoAdapter.setVideoListClickListener(this);
        personDetailVideoAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
                intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                intent.putExtra("uuid", videoDataList.get(position - 1).getUuid());
                intent.putExtra("position", 4);
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

    private void requestHomeVideoList() {
        OkGo.<String>post(Constant.HOME_VIDEO_LIST_URL)
                .params("cate_id", mCateId)
                .params("limit", 10)
                .params("skip", mPage)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        HomeVideoListBean homeVideoListBean = GsonUtils.jsonFromJson(response.body(), HomeVideoListBean.class);
                        if (homeVideoListBean.getCode() == 0) {
                            int index = videoDataList.size();
                            videoDataList.addAll(homeVideoListBean.getData().getList());
                            mPage++;
                            mRv.refreshComplete();
                            personDetailVideoAdapter.notifyItemRangeChanged(index, homeVideoListBean.getData().getList().size());
                            personDetailVideoAdapter.notifyDataSetChanged();
                        }
                    }
                });
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

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        personDetailVideoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("点赞失败" + response.body().toString());
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
    public void videoLikeItemClick(final int position) {
        OkGo.<String>post(Constant.VIDEO_LIKE_URL).params("data_uuid", videoDataList.get(position).getUuid()).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.body());
                    int code = jsonObject.getInt("code");
                    if (code == 100) {
                        asyncShowToast("点赞成功");
                        videoDataList.get(position).setIs_like(1);
                    } else if (code == 101) {
                        asyncShowToast("取消点赞");
                        videoDataList.get(position).setIs_like(0);
                    }
                    personDetailVideoAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void videoAddAdvert(int position) {
        Intent intent = new Intent(mActivity, AddTiePianAdvertActivity.class);
        StringBuilder sb = new StringBuilder(Constant.VIDEO_DETAIL_URL);
        sb.append("?token=").append(SPUtils.getString(SpConstant.ACCESS_TOKEN, "")).append("&uuid=").append(videoDataList.get(position).getUuid());
        intent.putExtra("url", sb.toString());
        startActivity(intent);
    }

}
