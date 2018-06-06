package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageCommentBean;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageAdapter extends BaseRecycleViewAdapter <MineMessageCommentBean.DataBean>{

    public MineMessageAdapter(Context context, List<MineMessageCommentBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineMessageCommentBean.DataBean dataBean) {
        final RoundImageView issueHead = holder.getView(R.id.iv_mineMessage_issueImage);
        final RoundImageView commenHead = holder.getView(R.id.iv_mineMessage_commenImage);
        holder.setText(R.id.tv_mineMessage_headContent,dataBean.getPosts_content());
    holder.setText(R.id.tv_mineMessage_headtitle,dataBean.getPosts_member_name());
    holder.setText(R.id.tv_mineMessage_personLocation,dataBean.getLocation());
    holder.setText(R.id.tv_mineMessage_issueTime,dataBean.getCtime());
    holder.setText(R.id.mineMessage_discuss_message,dataBean.getContent());

    //本人头像
        GlideApp.with(mContext).load(dataBean.getPosts_member_avatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(issueHead);

    //加载回复头像
        GlideApp.with(mContext).load(dataBean.getReply_member_avatar())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .centerCrop()
                .into(commenHead);



    }

    @Override
    protected int getItemView() {
        return R.layout.mine_message_list;
    }
}
