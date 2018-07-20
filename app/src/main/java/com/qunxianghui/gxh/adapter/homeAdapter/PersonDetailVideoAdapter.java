package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class PersonDetailVideoAdapter extends BaseRecycleViewAdapter<HomeVideoListBean.DataBean.ListBean> {
    private VideoListClickListener videoListClickListener;


    public void setVideoListClickListener(VideoListClickListener videoListClickListener) {
        this.videoListClickListener = videoListClickListener;
    }

    public PersonDetailVideoAdapter(Context context, List<HomeVideoListBean.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, HomeVideoListBean.DataBean.ListBean listBean) {
        final RoundImageView personHeadImag = holder.getView(R.id.round_item_collect_video_personhead);
        TextView videoAttention = holder.getView(R.id.tv_mycollect_video_attention);
        ImageView ivCollectVideoLike = holder.getView(R.id.iv_item_collect_video_like);

        if (TextUtils.isEmpty(listBean.getFollow())) {
            videoAttention.setText("关注");
        } else {
            videoAttention.setText("已关注");
        }

        final String member_name = listBean.getMember_name();
        final String member_avatar = listBean.getMember_avatar();
        String video_url = listBean.getVideo_url();
        int like_cnt = listBean.getLike_cnt();
        String picurl = listBean.getPicurl();
        String title = listBean.getTitle();
        holder.setText(R.id.tv_item_collect_video_personname, member_name);
        holder.setText(R.id.tv_item_collect_video_likecountt, String.valueOf(like_cnt));
        JZVideoPlayerStandard videpPlayer = holder.getView(R.id.videoplayer);
        videpPlayer.setUp(video_url, JZVideoPlayer.SCREEN_WINDOW_LIST,
                title);

        GlideApp.with(mContext).load(picurl).into(videpPlayer.thumbImageView);
/**
 * 加载人的头像
 */
        GlideApp.with(mContext).load(member_avatar).centerCrop()
                .placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .into(personHeadImag);
        personHeadImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoListClickListener.videoHeadImageClick(position);
            }
        });
        /**
         * 点击关注
         * @return
         */
        videoAttention.setOnClickListener(new View.OnClickListener() {
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
    }


    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }


    public interface VideoListClickListener {
        /* 视频关注*/
        void attentionClick(int position);

        /* 头像点击*/
        void videoHeadImageClick(int position);

        void videoLikeItemClick(int position);
    }
}
