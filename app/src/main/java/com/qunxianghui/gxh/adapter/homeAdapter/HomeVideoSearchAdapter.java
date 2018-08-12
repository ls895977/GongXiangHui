package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;

import java.util.List;

public class HomeVideoSearchAdapter extends BaseRecycleViewAdapter<HomeVideoSearchBean.DataBean> {
    public HomeVideoSearchAdapter(Context context, List<HomeVideoSearchBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, HomeVideoSearchBean.DataBean dataBean) {
        holder.setText(R.id.iv_homesearch_video_title, dataBean.getTitle());
        ImageView mSearchVideoPic = holder.getView(R.id.iv_homesearch_pic);
        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(dataBean.getPicurl()).apply(options).into(mSearchVideoPic);


    }

    @Override
    protected int getItemView() {
        return R.layout.item_home_search_video;
    }
}
