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
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;

import java.util.List;

public class HomeVideoSearchAdapter extends BaseRecycleViewAdapter<HomeVideoSearchBean.DataBean> {
    private VideoSearchListClickListener mVideoSearchListClickListener;

    public void setVideoSearchListClickListener(VideoSearchListClickListener videoSearchListClickListener) {
        mVideoSearchListClickListener = videoSearchListClickListener;
    }

    public HomeVideoSearchAdapter(Context context, List<HomeVideoSearchBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, HomeVideoSearchBean.DataBean dataBean) {
        TextView mTvSearchVideoAttention = holder.getView(R.id.tv_mycollect_video_attention);
        ImageView mIvSearchVideoLike = holder.getView(R.id.iv_item_collect_video_like);
        ImageView mSearchImageVideoHead = holder.getView(R.id.round_item_collect_video_personhead);
        TextView mTvSearchVideoUserName = holder.getView(R.id.tv_item_collect_video_personname);
        int is_like = dataBean.getIs_like();

        if (is_like == 0) {
            mSearchImageVideoHead.setImageResource(R.mipmap.home_video_collect_normal);

        } else {
            mSearchImageVideoHead.setImageResource(R.mipmap.home_video_collect_select);
        }
        holder.setText(R.id.iv_homesearch_video_title, dataBean.getTitle());
        holder.setText(R.id.tv_like, String.valueOf(dataBean.getLike_cnt()));
        holder.setText(R.id.tv_comment, String.valueOf(dataBean.getComment_cnt()));
        holder.setText(R.id.tv_item_collect_video_personname, dataBean.getMember_name());
        holder.setText(R.id.tv_mycollect_video_attention, TextUtils.isEmpty(dataBean.getFollow()) ? "+关注" : "已关注");

        TextView videoSearchPaster = holder.getView(R.id.tv_mycollect_video_paster);
        ImageView mSearchVideoPic = holder.getView(R.id.iv_homesearch_pic);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);

        Glide.with(mContext).load(dataBean.getMember_avatar()).apply(options).into(mSearchImageVideoHead);

        Glide.with(mContext).load(dataBean.getPicurl()).apply(options).into(mSearchVideoPic);


        videoSearchPaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoSearchListClickListener.PasterClick(position);
            }
        });

        mTvSearchVideoAttention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoSearchListClickListener.SearchVideoClick(position);
            }
        });
        mIvSearchVideoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoSearchListClickListener.SearchVideoLikeClick(position);
            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.item_home_search_video;
    }

    public interface VideoSearchListClickListener {
        void PasterClick(int position);

        void SearchVideoClick(int position);

        void SearchVideoLikeClick(int position);


    }
}
