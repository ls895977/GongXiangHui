package com.qunxianghui.gxh.ui.fragments.homeFragment.fragments;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.LocationActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.AddTiePianAdvertActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.PersonDetailActivity;
import com.qunxianghui.gxh.utils.LogUtil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.video.ScrollCalculatorHelper;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.CommonUtil;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeVideoListFragment extends BaseFragment implements PersonDetailVideoAdapter.VideoListClickListener {

    @BindView(R.id.xrv)
    XRecyclerView mRv;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private int mSkip;
    private int mCateId;
    private PersonDetailVideoAdapter personDetailVideoAdapter;
    private List<HomeVideoListBean.DataBean.ListBean> videoDataList = new ArrayList<>();
//    private JZVideoPlayerStandard mVideoPlayerStandard;
    private int firstVisible = 0, visibleCount = 0, totalCount = 0;
    private LinearLayoutManager mLinearLayoutManager;
    ScrollCalculatorHelper scrollCalculatorHelper;
    ListVideoUtil listVideoUtil;
    private int lastVisibleItem;
    private int firstVisibleItem;
    boolean mFull = false;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_homevideo_list;
    }

    @Override
    public void initViews(View view) {
        mLinearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        mRv.setLayoutManager(mLinearLayoutManager);
        //限定范围为屏幕一半的上下偏移280
        int playTop = CommonUtil.getScreenHeight(getActivity()) / 2 - CommonUtil.dip2px(getActivity(), 80);
        int playBottom = CommonUtil.getScreenHeight(getActivity()) / 2 + CommonUtil.dip2px(getActivity(), 80);
        //自定播放帮助类
        scrollCalculatorHelper = new ScrollCalculatorHelper(R.id.video_item_player, playTop, playBottom);
        personDetailVideoAdapter = new PersonDetailVideoAdapter(mActivity, mLinearLayoutManager, videoDataList);


        listVideoUtil = new ListVideoUtil(getActivity());
//        listVideoUtil.setFullViewContainer(videoFullContainer);
        listVideoUtil.setHideStatusBar(true);
        //listVideoUtil.setHideActionBar(true);
        listVideoUtil.setNeedLockFull(true);
//        initFootView();

        personDetailVideoAdapter.setListVideoUtil(listVideoUtil);
        mRv.setAdapter(personDetailVideoAdapter);
    }
    boolean isScrollMoveTo = false;
    //动画定位
    private void smoothMoveToPosition(int position) {
        isScrollMoveTo = true;
        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        Log.e("TAG_Scroll", "firstItem=" + firstItem + ";position=" + position + ";lastItem=" + lastItem);
        if (position <= firstItem) {
            if (position == 1){
                mRv.smoothScrollToPosition(position);
                mRv.smoothScrollBy(0, 10);
            }else {
                mRv.smoothScrollToPosition(position);
            }
        } else if (position <= lastItem) {
            int top = mRv.getChildAt(position - firstItem).getTop();
            mRv.smoothScrollBy(0, top);
        } else {
            mRv.smoothScrollToPosition(position);
        }
    }

    @Override
    public void initData() {
        mCateId = getArguments().getInt("channel_id");
        requestHomeVideoList();
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        mSkip = 0;
//        requestHomeVideoList();
//    }

    @Override
    public void onResume() {
        super.onResume();
        if (mCateId == 0 && LocationActivity.sVideoCanChange) {
            LocationActivity.sVideoCanChange = false;
            mSkip = 0;
            initData();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
//        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mRv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                requestHomeVideoList();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                requestHomeVideoList();
            }
        });

        personDetailVideoAdapter.setVideoListClickListener(this);

//        mRv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
//            @Override
//            public void onChildViewAttachedToWindow(View view) {
//            }
//
//            @Override
//            public void onChildViewDetachedFromWindow(View view) {
//                JZVideoPlayer jzvd = view.findViewById(R.id.videoplayer);
//                if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
//                    JZVideoPlayer currentJzvd = JZVideoPlayerManager.getCurrentJzvd();
//                    if (currentJzvd != null && currentJzvd.currentScreen != JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN) {
//                        JZVideoPlayer.releaseAllVideos();
//                    }
//                }
//            }
//        });
        /*监听滑动状态*/
        mRv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                scrollCalculatorHelper.onScrollStateChanged(recyclerView, newState);
                if (isScrollMoveTo){
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE:
                            mRv.smoothScrollBy(0, 10);
                            isScrollMoveTo =false;
                            break;
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();

                //这是滑动自动播放的代码
                if (!mFull) {
                    if (!isScrollMoveTo){
                        Log.e("TAG_播放","isScrollMoveTo="+(!isScrollMoveTo));
                        scrollCalculatorHelper.onScroll(recyclerView, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
                    }
                }
                if (dy > 0) {
                    int visibleItemCount = mLinearLayoutManager.getChildCount();
                    int totalItemCount = mLinearLayoutManager.getItemCount();
                    int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                    if (firstVisible == firstVisibleItem) {
                        return;
                    }
                    firstVisible = firstVisibleItem;
                    visibleCount = visibleItemCount;
                    totalCount = totalItemCount;

                }

            }
        });

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

    private void requestHomeVideoList() {
        OkGo.<HomeVideoListBean>post(Constant.HOME_VIDEO_LIST_URL)
                .params("cate_id", mCateId)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<HomeVideoListBean>() {
                    @Override
                    public void onSuccess(Response<HomeVideoListBean> response) {
                        HomeVideoListBean homeVideoListBean = response.body();
                        if (homeVideoListBean.getCode() == 0) {
                            if (mSkip == 0) {
                                videoDataList.clear();
                                mRv.refreshComplete();
                                mRv.setLoadingMoreEnabled(true);
                            }
                            llEmpty.setVisibility(View.GONE);
                            int total = homeVideoListBean.getData().getList().size();
                            if (total < 10) {
                                mRv.setLoadingMoreEnabled(false);
                            }
                            videoDataList.addAll(homeVideoListBean.getData().getList());
                            LogUtil.loge("TAG_視頻播放", "videoDataList=" + videoDataList.toString());
                            personDetailVideoAdapter.setListData(videoDataList);
                            if (videoDataList.isEmpty()) {
                                llEmpty.setVisibility(View.VISIBLE);
                            }
                        }
                        mRv.loadMoreComplete();
                        if (mRv.getEmptyView() == null)
                            mRv.setEmptyView(LayoutInflater.from(mActivity).inflate(R.layout.layout_empty, mRv, false));
                        personDetailVideoAdapter.notifyDataSetChanged();
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
                .params("be_member_id", videoDataList.get(position).getMember_id())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        int code = response.body().code;
                        String msg = response.body().message;
                        if (code == 0) {
                            videoDataList.get(position).setFollow("true");
                        } else if (code == 202) {
                            videoDataList.get(position).setFollow("");
                        }
                        asyncShowToast(msg);
                        personDetailVideoAdapter.notifyDataSetChanged();
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
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        OkGo.<CommonBean>post(Constant.VIDEO_LIKE_URL)
                .params("data_uuid", videoDataList.get(position).getUuid())
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        HomeVideoListBean.DataBean.ListBean listBean = videoDataList.get(position);
                        int likeCnt = Integer.parseInt(listBean.getLike_cnt());
                        if (response.body().code == 100) {
                            listBean.setIs_like(1);
                            listBean.setLike_cnt(++likeCnt + "");
                        } else if (response.body().code == 101) {
                            listBean.setIs_like(0);
                            listBean.setLike_cnt(--likeCnt + "");
                        }
                        asyncShowToast(response.body().message);
                        personDetailVideoAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void videoAddAdvert(int position) {
        if (!LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
        if (!SPUtils.getSp().getBoolean(SpConstant.IS_COMPANY, false)) {
            asyncShowToast("亲，非企业会员只可添加底部广告哦～～");
            return;
        }
        Intent intent = new Intent(mActivity, AddTiePianAdvertActivity.class);
        intent.putExtra("url", Constant.VIDEO_DETAIL_URL
                + "?token=" + SPUtils.getString(SpConstant.ACCESS_TOKEN, "")
                + "&uuid=" + videoDataList.get(position).getUuid());
        startActivity(intent);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(mActivity, NewsDetailActivity.class);
        intent.putExtra("url", Constant.VIDEO_DETAIL_URL);
        intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
        intent.putExtra("uuid", videoDataList.get(position - 1).getUuid());
        intent.putExtra("descrip", videoDataList.get(position - 1).getDescription());
        intent.putExtra("title", videoDataList.get(position - 1).getTitle());
        intent.putExtra("videoimage", videoDataList.get(position - 1).getPicurl());
        intent.putExtra("position", 4);
        HomeVideoListFragment.this.startActivityForResult(intent, 0x0011);
    }

    @Override
    public void onAutoComplete(int position) {
        Log.e("TAG_播放完成", "position=" + position);
        Log.e("TAG_播放完成", "mFull=" + mFull);
        if (position < videoDataList.size() - 1) {
//                HomeVideoListBean.DataBean.ListBean listBeanNext = videoDataList.get(position + 1);
//                String  videoUrlNext = listBeanNext.getVideo_url();
//                Log.e("TAG_播放下一个","videoUrlNext="+videoUrlNext);
//                        listVideoUtil.startPlay(videoUrlNext);
            if (!mFull) {
                smoothMoveToPosition(position + 1);

//                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
//                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
//                Log.e("TAG_播放下一个", "firstVisibleItem=" + firstVisibleItem+";lastVisibleItem="+lastVisibleItem);
//                scrollCalculatorHelper.onScroll(mRv, firstVisibleItem, lastVisibleItem, lastVisibleItem - firstVisibleItem);
//                scrollCalculatorHelper.onScrollStateChanged(mRv, 0);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        listVideoUtil.releaseVideoPlayer();
        GSYVideoManager.releaseAllVideos();
    }

}
