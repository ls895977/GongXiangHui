package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

/**
 * 我收藏贴子的适配器
 */
public class MyCollectPostAdapter extends BaseRecycleViewAdapter<MyCollectPostBean.DataBean> {
    public MyCollectPostAdapter(Context context, List<MyCollectPostBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MyCollectPostBean.DataBean dataBean) {
        final ImageView collectHeadImag = holder.getView(R.id.iv_mine_mycollect_head);
        final List<String> images = dataBean.getImages();
        final String source = dataBean.getInfo().getSource();
        final String title = dataBean.getInfo().getTitle();
        final String ctime = dataBean.getCtime();


        holder.setText(R.id.tv_mine_mycollect_from, source);
        holder.setText(R.id.tv_mine_mycollect_time, ctime);
        holder.setText(R.id.tv_mine_mycollect_title, title);
        if (images.size() >= 1) {
            GlideApp.with(mContext).load(images.get(0))
                    .centerCrop()
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_test_1)
                    .into(collectHeadImag);
        }

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_mycollect_post;
    }
}
