package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.ui.view.MeVideo;
import com.qunxianghui.gxh.video.RecyclerItemNormalHolder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.ListVideoUtil;

import java.util.List;


public class PersonDetailVideoAdapter extends RecyclerView.Adapter<RecyclerItemNormalHolder> {

    private List<HomeVideoListBean.DataBean.ListBean> itemDataList = null;
    private Context context = null;
    private ListVideoUtil listVideoUtil;
    private LinearLayoutManager mLinearLayoutManager;

    public PersonDetailVideoAdapter(Context context, LinearLayoutManager mLinearLayoutManager, List<HomeVideoListBean.DataBean.ListBean> itemDataList) {
        this.itemDataList = itemDataList;
        this.context = context;
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    private VideoListClickListener videoListClickListener;
    private MeVideo.PlayCompleteLister playCompleteLister;

    public void setVideoListClickListener(VideoListClickListener videoListClickListener) {
        this.videoListClickListener = videoListClickListener;
    }

    public void setPlayCompleteListener(MeVideo.PlayCompleteLister playCompleteLister) {
        this.playCompleteLister = playCompleteLister;
    }

    @Override
    public RecyclerItemNormalHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_home_video, parent, false);
        RecyclerItemNormalHolder holder = new RecyclerItemNormalHolder(context, v);
        return holder;

    }

    @Override
    public void onBindViewHolder(final RecyclerItemNormalHolder holder, final int position) {
        holder.setListVideoUtil(listVideoUtil);
        holder.setRecyclerBaseAdapter(this);
        HomeVideoListBean.DataBean.ListBean listBean = itemDataList.get(position);
        holder.onBind(position, listBean);

        int is_like = listBean.getIs_like();
        String follow = listBean.getFollow();
        if (TextUtils.isEmpty(follow)) {
            holder.tvVideoAttention.setText("关注");
        } else {
            holder.tvVideoAttention.setVisibility(View.GONE);
        }

        if (is_like == 0) {
            holder.ivItemCollectCideoLike.setImageResource(R.mipmap.home_video_collect_normal);

        } else {
            holder.ivItemCollectCideoLike.setImageResource(R.mipmap.home_video_collect_select);

        }
        holder.tvLike.setText(listBean.getLike_cnt());

//        holder.setText(R.id.tv_mycollect_video_attention, TextUtils.isEmpty(listBean.getFollow()) ? "+关注" : "已关注");
        holder.tvItemCollectCideoPersonname.setText(listBean.getMember_name());

        holder.tvComment.setText(listBean.getComment_cnt());


        RequestOptions options = new RequestOptions();

        Glide.with(context).load(listBean.getPicurl()).apply(options.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(holder.imageView);

        Glide.with(context).load(listBean.getMember_avatar()).apply(options.placeholder(R.mipmap.user_moren).error(R.mipmap.user_moren).centerCrop().circleCrop()).into(holder.roundCollectVideoPersonhead);

        holder.rl_videolist_item_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoListClickListener.onItemClick(position);
            }
        });
        holder.roundCollectVideoPersonhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoHeadImageClick(position);
            }
        });
        holder.tvVideoAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.attentionClick(position);
            }
        });
        holder.ivItemCollectCideoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoLikeItemClick(position);
            }
        });
        holder.tvVideoPaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoAddAdvert(position);
            }
        });


        holder.gsyVideoPlayer.
                setVideoAllCallBack(new GSYSampleCallBack() {

                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        Log.e("TAG_播放完成", "onPrepared" + listVideoUtil.getDuration());
                        Debuger.printfLog("Duration " + listVideoUtil.getDuration() + " CurrentPosition " + listVideoUtil.getCurrentPositionWhenPlaying());
                    }

                    @Override
                    public void onQuitSmallWidget(String url, Object... objects) {
                        super.onQuitSmallWidget(url, objects);
                        //大于0说明有播放,//对应的播放列表TAG
                        //当前播放的位置
                        int position = listVideoUtil.getPlayPosition();
                        if (position >= 0 && listVideoUtil.getPlayTAG().equals(RecyclerItemNormalHolder.TAG)) {
                            //不可视的时候
                            int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                            int lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
                            if ((position < firstVisibleItem || position > lastVisibleItem)) {
                                //释放掉视频
                                listVideoUtil.releaseVideoPlayer();

                            }
                        }
                    }

                    @Override
                    public void onAutoComplete(String url, Object... objects) {
                        super.onAutoComplete(url, objects);
                        Log.e("TAG_視頻完成", "position=" + position + "=====url=" + url);
                        videoListClickListener.onAutoComplete(position, url);
                    }

                    @Override
                    public void onStartPrepared(String url, Object... objects) {
                        super.onStartPrepared(url, objects);
                        Log.e("TAG_視頻開始", "position=" + position + "=====url=" + url);
                        videoListClickListener.onStartPrepared(position, url);
                    }
                });
    }

    @Override
    public int getItemCount() {
        return itemDataList.size();
    }


    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public void setListData(List<HomeVideoListBean.DataBean.ListBean> data) {
        itemDataList = data;
        notifyDataSetChanged();
    }

    public ListVideoUtil getListVideoUtil() {
        return listVideoUtil;
    }

    public void setListVideoUtil(ListVideoUtil listVideoUtil) {
        this.listVideoUtil = listVideoUtil;
    }

    public interface VideoListClickListener {
        /* 视频关注*/
        void attentionClick(int position);

        /* 头像点击*/
        void videoHeadImageClick(int position);

        /*视频的喜欢*/
        void videoLikeItemClick(int position);

        void videoAddAdvert(int position);

        /*实现的单击事件*/
        void onItemClick(int position);

        //播放完成
        void onStartPrepared(int position, String url);

        //播放完成
        void onAutoComplete(int position, String url);
    }
}
