package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoListBean;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

public class PersonDetailVideoAdapter extends BaseRecycleViewAdapter<HomeVideoListBean.DataBean.ListBean> {
    public PersonDetailVideoAdapter(Context context, List<HomeVideoListBean.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, HomeVideoListBean.DataBean.ListBean listBean) {
        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);
        final RoundImageView personHeadImag = holder.getView(R.id.round_item_collect_video_personhead);
        TextView videoAttention = holder.getView(R.id.tv_mycollect_video_attention);

        final String picurl = listBean.getPicurl();
        final String title = listBean.getTitle();
        final String member_name = listBean.getMember_name();
        final String member_avatar = listBean.getMember_avatar();

        holder.setText(R.id.tv_mycollect_video_title, title);
        holder.setText(R.id.tv_item_collect_video_personname, member_name);

        /**
         * 加载视频第一张默认图
         */
        GlideApp.with(mContext).load(picurl).centerCrop()
                .placeholder(R.mipmap.ic_test_1)
                .error(R.mipmap.ic_test_0)
                .into(videoImag);
/**
 * 加载人的头像
 */
        GlideApp.with(mContext).load(member_avatar).centerCrop()
                .placeholder(R.mipmap.ic_test_1)
                .error(R.mipmap.ic_test_0)
                .into(personHeadImag);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }
}
