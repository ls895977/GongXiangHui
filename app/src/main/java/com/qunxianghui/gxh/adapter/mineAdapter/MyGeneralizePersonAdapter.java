package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.generalize.GeneraPersonStaticBean;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyGeneralizePersonAdapter extends BaseRecycleViewAdapter<GeneraPersonStaticBean.DataBean> {
    public MyGeneralizePersonAdapter(Context context, List<GeneraPersonStaticBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, GeneraPersonStaticBean.DataBean dataBean) {
        final ImageView collectHeadImag = holder.getView(R.id.iv_generalize_person_head);
        final List<String> images = dataBean.getImages();
        final int click_cnt = dataBean.getClick_cnt();
        final String title = dataBean.getTitle();
        final String ctime = dataBean.getCtime();
        final int view_cnt = dataBean.getView_cnt();
        final int share_cnt = dataBean.getShare_cnt();


        holder.setText(R.id.tv_generalize_person_click, String.valueOf(click_cnt));
        holder.setText(R.id.tv_generalize_person_time, ctime);
        holder.setText(R.id.tv_generalize_person_title, title);
        holder.setText(R.id.tv_generalize_person_seecount, String.valueOf(view_cnt));
        holder.setText(R.id.tv_generalize_person_share, String.valueOf(share_cnt));
        if (images.size() >= 1) {


            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.default_img);
            options.placeholder(R.mipmap.default_img);
            options.centerCrop();
            Glide.with(mContext).load(images.get(0)).apply(options).into(collectHeadImag);
        }

    }

    @Override
    protected int getItemView() {
        return R.layout.item_generalize_person;
    }
}
