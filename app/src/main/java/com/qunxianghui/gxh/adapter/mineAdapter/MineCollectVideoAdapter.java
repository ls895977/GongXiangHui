package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectVideoBean;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;

import org.w3c.dom.Text;

import java.util.List;

public class MineCollectVideoAdapter extends BaseRecycleViewAdapter<MineCollectVideoBean.DataBean> {

    public MineCollectVideoAdapter(Context context, List<MineCollectVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineCollectVideoBean.DataBean dataBean) {

        final ImageView videoImag = holder.getView(R.id.iv_item_collect_video_head);
        final RoundImageView personHeadImag = holder.getView(R.id.round_item_collect_video_personhead);
        TextView videoAttention = holder.getView(R.id.tv_mycollect_video_attention);

        final String picurl = dataBean.getPicurl();
        final String title = dataBean.getInfo().getTitle();
        final String member_name = dataBean.getMember().getMember_name();
        final String member_avatar = dataBean.getMember().getMember_avatar();

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

        /**
         * 点击了视频关注
         * @return
         */



    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_collect_video;
    }
}
