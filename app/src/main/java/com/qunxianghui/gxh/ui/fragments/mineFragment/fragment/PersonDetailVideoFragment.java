package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.PersonDetailVideoAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.video.ScrollCalculatorHelper;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PersonDetailVideoFragment extends BaseFragment implements PersonDetailVideoAdapter.VideoListClickListener {

    @BindView(R.id.xrecycler_persondetail_video)
    XRecyclerView xrecyclerPersondetailVideo;
    @BindView(R.id.ll_empty)
    View mEmptyView;

    private PersonDetailActivity personDetailActivity;
    private List<HomeVideoListBean.DataBean.ListBean> mVideoList=new ArrayList<>();
    private PersonDetailVideoAdapter mPersonDetailVideoAdapter;
    private int count;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private LinearLayoutManager linearLayoutManager;
    ListVideoUtil listVideoUtil;
    private int playTop;
    private int playBottom;
    boolean mFull = false;
    ScrollCalculatorHelper scrollCalculatorHelper;
    @Override
    protected void onLoadData() {
        RequestPersonDataVideoData();

    }

    private void RequestPersonDataVideoData() {
        OkGo.<HomeVideoListBean>post(Constant.HOME_VIDEO_LIST_URL)
                .params("user_id", personDetailActivity.member_id)
                .params("limit", 12)
                .params("skip", count)
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
        if (mIsRefresh) {
            mIsRefresh = false;
            mVideoList.clear();
        }
        mVideoList.addAll(homeVideoListBean.getData().getList());
        count=mVideoList.size();
        if (homeVideoListBean.getCode() == 0) {
            if (mIsFirst){
                mIsFirst=false;
                scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_item_player, playTop, playBottom);
                mPersonDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity,linearLayoutManager, mVideoList);
                listVideoUtil = new ListVideoUtil(getActivity());
                listVideoUtil.setHideStatusBar(true);
                listVideoUtil.setNeedLockFull(true);
                mPersonDetailVideoAdapter.setListVideoUtil(listVideoUtil);
                xrecyclerPersondetailVideo.setAdapter(mPersonDetailVideoAdapter);

                mPersonDetailVideoAdapter.setVideoListClickListener(this);

                if (mVideoList.isEmpty()){
                    mEmptyView.setVisibility(View.VISIBLE);
                }

            }
        }
        xrecyclerPersondetailVideo.refreshComplete();
        mPersonDetailVideoAdapter.notifyDataSetChanged();
        mPersonDetailVideoAdapter.notifyItemRangeChanged(count,homeVideoListBean.getData().getList().size());
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
        linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        xrecyclerPersondetailVideo.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecyclerPersondetailVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                RequestPersonDataVideoData();
            }

            @Override
            public void onLoadMore() {
                RequestPersonDataVideoData();
            }
        });
    }

    @Override
    public void attentionClick(final int position) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        OkGo.<CommonBean>post(Constant.ATTENTION_URL)
                .params("be_member_id", mVideoList.get(position).getMember_id())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        String msg = response.body().message;
                        if (code == 0) {
                            mVideoList.get(position).setFollow("true");
                        } else if (code == 202) {
                            mVideoList.get(position).setFollow("");
                        }
                        asyncShowToast(msg);
                        mPersonDetailVideoAdapter.notifyDataSetChanged();
                    }

                });

    }

    @Override
    public void videoHeadImageClick(int position) {
        mPersonDetailVideoAdapter.notifyDataSetChanged();
    }

    @Override
    public void videoLikeItemClick(final int position) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        OkGo.<CommonBean>post(Constant.VIDEO_LIKE_URL)
                .params("data_uuid", mVideoList.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        HomeVideoListBean.DataBean.ListBean listBean = mVideoList.get(position);
                        int likeCnt = Integer.parseInt(listBean.getLike_cnt());
                        if (response.body().code == 100) {
                            listBean.setIs_like(1);
                            listBean.setLike_cnt(++likeCnt + "");
                        } else if (response.body().code == 101) {
                            listBean.setIs_like(0);
                            listBean.setLike_cnt(--likeCnt + "");
                        }
                        asyncShowToast(response.body().message);
                        mPersonDetailVideoAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void videoAddAdvert(int position) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        Intent intent = new Intent(mActivity, AddTiePianAdvertActivity.class);
        intent.putExtra("url", Constant.VIDEO_DETAIL_URL
                + "?token=" + SPUtils.getString(SpConstant.ACCESS_TOKEN, "")
                + "&uuid=" + mVideoList.get(position).getUuid());
        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {
        int uuid = mVideoList.get(position - 1).getUuid();
        String content = mVideoList.get(position - 1).getContent();
        String title = mVideoList.get(position - 1).getTitle();
        Intent intent = new Intent(mActivity, NewsDetailActivity.class);
        intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
        intent.putExtra("uuid", uuid);
        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
        intent.putExtra("descrip", content);
        intent.putExtra("title", title);
        intent.putExtra("position", 4);
        startActivity(intent);
    }


    @Override
    public void onStartPrepared(int position, String url) {
//        if (position < mVideoList.size() - 1) {
//            if (!mFull) {
//                smoothMoveToPosition(position);
//
//            }
//        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (newConfig.orientation != ActivityInfo.SCREEN_ORIENTATION_USER) {
            mFull = false;
        } else {
            mFull = true;
        }

    }

    @Override
    public void onAutoComplete(int position,String url) {
        if (position < mVideoList.size() - 1) {
            if (!mFull) {
                smoothMoveToPosition(position + 2);
                PlayRunnable runnable = new PlayRunnable(position,url);
                //降低频率
                playHandler.postDelayed(runnable, 2000);
            }
        }
    }
    private Handler playHandler = new Handler();
    private class PlayRunnable implements Runnable {

        String url;
        int position;
        public PlayRunnable(int position,String url) {
            this.url = url;
            this.position = position;
        }

        @Override
        public void run() {
            for (int i = position+1; i < mVideoList.size(); i++) {
                HomeVideoListBean.DataBean.ListBean listBeanNext = mVideoList.get(i);
                String videoUrlNext = listBeanNext.getVideo_url();
                Log.e("TAG_播放下一个", "videoUrlNext=" + videoUrlNext);
                if (!url.equals(videoUrlNext)) {
                    Log.e("TAG_播放位置", "i=====" + (i+1));
                    View childAt = linearLayoutManager.findViewByPosition(i+1);
                    if (childAt != null && childAt.findViewById(R.id.video_item_player) != null) {
                        gsyBaseVideoPlayer = (GSYBaseVideoPlayer) childAt.findViewById(R.id.video_item_player);
                        Rect rect = new Rect();
                        gsyBaseVideoPlayer.getLocalVisibleRect(rect);
                        //如果未播放，需要播放
                        if (gsyBaseVideoPlayer != null) {
                            scrollCalculatorHelper.startPlayLogic(gsyBaseVideoPlayer, gsyBaseVideoPlayer.getContext());
                            //gsyBaseVideoPlayer.startPlayLogic();
                        }
                    }
                    break;
                }
            }

        }
    }
    GSYBaseVideoPlayer gsyBaseVideoPlayer;
    //动画定位
    private void smoothMoveToPosition(final int position) {
        Log.e("TAG_播放位置", "滚动=====" + position);
        int firstItem = linearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = linearLayoutManager.findLastVisibleItemPosition();
        Log.e("TAG_Scroll", "firstItem=" + firstItem + ";position=" + position + ";lastItem=" + lastItem);
        if (position <= firstItem) {
            xrecyclerPersondetailVideo.smoothScrollToPosition(position);
        } else if (position <= lastItem) {
            int top = xrecyclerPersondetailVideo.getChildAt(position - firstItem).getTop();
//            int top = xrecyclerPersondetailVideo.getChildAt(1).getTop();
            xrecyclerPersondetailVideo.smoothScrollBy(0, top);
        } else {
            xrecyclerPersondetailVideo.smoothScrollToPosition(position);
        }

//        playHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (position > xrecyclerPersondetailVideo.getChildCount() - 1) {
//                    if (oldPosition > position) {
//                        childAt = xrecyclerPersondetailVideo.getChildAt(0);
//
//                    } else {
//
//                        childAt = xrecyclerPersondetailVideo.getChildAt(xrecyclerPersondetailVideo.getChildCount() - 1);
//                    }
//                } else {
//                    if (oldPosition > position) {
//                        childAt = xrecyclerPersondetailVideo.getChildAt(0);
//                    } else {
//                        childAt = xrecyclerPersondetailVideo.getChildAt(position);
//                    }
//                }
//                changeX = (int) childAt.getX();
//                childWidth = childAt.getWidth() / 2;
//                xrecyclerPersondetailVideo.smoothScrollBy(changeX - (width - childWidth), 0);
//            }
//        }, 50);
//        playHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                oldPosition = position;
//
//            }
//        }, 100);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (gsyBaseVideoPlayer !=null)
        gsyBaseVideoPlayer.onVideoPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }
}

