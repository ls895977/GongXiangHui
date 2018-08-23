package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
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
        holder.setText(R.id.iv_homesearch_video_title, dataBean.getTitle());
        TextView videoSearchPaster = holder.getView(R.id.tv_mycollect_video_paster);
        ImageView mSearchVideoPic = holder.getView(R.id.iv_homesearch_pic);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(dataBean.getPicurl()).apply(options).into(mSearchVideoPic);
        videoSearchPaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVideoSearchListClickListener.PasterClick(position);
            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.item_home_search_video;
    }

    public interface VideoSearchListClickListener {
        void PasterClick(int position);


    }
}
