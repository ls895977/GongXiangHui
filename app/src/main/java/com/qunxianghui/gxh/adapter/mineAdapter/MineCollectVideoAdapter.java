package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

public class MineCollectVideoAdapter extends BaseRecycleViewAdapter<MineCollectVideoBean.DataBean> {
    private MyCollectVideoClickListener myCollectVideoClickListener;

    public void setMyCollectVideoClickListener(MyCollectVideoClickListener myCollectVideoClickListener) {
        this.myCollectVideoClickListener = myCollectVideoClickListener;
    }

    public MineCollectVideoAdapter(Context context, List<MineCollectVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, MineCollectVideoBean.DataBean dataBean) {
        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);
        final RoundImageView personHeadImag = holder.getView(R.id.round_item_collect_video_personhead);
        TextView videoAttention = holder.getView(R.id.tv_mycollect_video_attention);
        String follow = dataBean.getMember().getFollow();
        if (follow.equals("")) {
            videoAttention.setText("关注");
        } else {
            videoAttention.setText("已关注");
        }
        final String picurl = dataBean.getPicurl();
        final String title = dataBean.getInfo().getTitle();
        final String member_name = dataBean.getMember().getMember_name();
        final String member_avatar = dataBean.getMember().getMember_avatar();
        holder.setText(R.id.tv_mycollect_video_title, title);
        holder.setText(R.id.tv_item_collect_video_personname, member_name);

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);


        /**
         * 加载视频第一张默认图
         */
        Glide.with(mContext).load(picurl).apply(options).into(videoImag);

/**
 * 加载人的头像
 */
        Glide.with(mContext).load(member_avatar).apply(options).into(personHeadImag);
        /**
         * 收藏视频关注点击
         */
        videoAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCollectVideoClickListener.attentionClick(position);
            }
        });
        /**
         * 收藏头像点击
         */
        personHeadImag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myCollectVideoClickListener.videoHeadImageClick(position);
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }

    public interface MyCollectVideoClickListener {
        /* 视频关注*/
        void attentionClick(int position);

        /* 头像点击*/
        void videoHeadImageClick(int position);
    }
}

