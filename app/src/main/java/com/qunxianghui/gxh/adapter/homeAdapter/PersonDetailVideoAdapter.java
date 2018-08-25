package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class PersonDetailVideoAdapter extends BaseRecycleViewAdapter<HomeVideoListBean.DataBean.ListBean> {

    private VideoListClickListener videoListClickListener;
    private RequestOptions options;

    public void setVideoListClickListener(VideoListClickListener videoListClickListener) {
        this.videoListClickListener = videoListClickListener;
    }

    public PersonDetailVideoAdapter(Context context, List<HomeVideoListBean.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, HomeVideoListBean.DataBean.ListBean listBean) {
        RoundImageView personHeadImag = holder.getView(R.id.round_item_collect_video_personhead);
        ImageView ivCollectVideoLike = holder.getView(R.id.iv_item_collect_video_like);
        ImageView mIvItemCollectVideoLike = holder.getView(R.id.iv_item_collect_video_like);
        TextView mTvCommentCount = holder.getView(R.id.tv_comment);
        int is_like = listBean.getIs_like();
        if (is_like == 0) {
            mIvItemCollectVideoLike.setImageResource(R.mipmap.home_video_collect_normal);

        } else {
            mIvItemCollectVideoLike.setImageResource(R.mipmap.home_video_collect_select);

        }  holder.setText(R.id.tv_like, listBean.getLike_cnt());

        holder.setText(R.id.tv_mycollect_video_attention, TextUtils.isEmpty(listBean.getFollow()) ? "+关注" : "已关注");
        holder.setText(R.id.tv_item_collect_video_personname, listBean.getMember_name());

        holder.setText(R.id.tv_comment, listBean.getComment_cnt());
        final JZVideoPlayerStandard videoPlayer = holder.getView(R.id.videoplayer);
        videoPlayer.setUp(listBean.getVideo_url(), JZVideoPlayer.SCREEN_WINDOW_LIST, listBean.getTitle());
        options = new RequestOptions();
        Glide.with(mContext).load(listBean.getPicurl()).apply(options.placeholder(R.mipmap.default_img).error(R.mipmap.default_img)).into(videoPlayer.thumbImageView);
        Glide.with(mContext).load(listBean.getMember_avatar()).apply(options.centerCrop().circleCrop()).into(personHeadImag);

        personHeadImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoHeadImageClick(position);
            }
        });
        holder.getView(R.id.tv_mycollect_video_attention).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.attentionClick(position);
            }
        });
        ivCollectVideoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoLikeItemClick(position);
            }
        });
        holder.getView(R.id.tv_mycollect_video_paster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoAddAdvert(position);
            }
        });
    }


    @Override
    protected int getItemView() {
        return R.layout.item_home_video;
    }

    public interface VideoListClickListener {
        /* 视频关注*/
        void attentionClick(int position);

        /* 头像点击*/
        void videoHeadImageClick(int position);

        /*视频的喜欢*/
        void videoLikeItemClick(int position);

        void videoAddAdvert(int position);
    }
}
