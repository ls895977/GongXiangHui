package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageCommentBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageAdapter extends BaseRecycleViewAdapter<MineMessageCommentBean.DataBean> {
    private CommontMeClickListener commontMeClickListener;
    private RequestOptions options;

    public void setCommontMeClickListener(CommontMeClickListener commontMeClickListener) {
        this.commontMeClickListener = commontMeClickListener;
    }

    public MineMessageAdapter(Context context, List<MineMessageCommentBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, MineMessageCommentBean.DataBean dataBean) {
        final RoundImageView issueHead = holder.getView(R.id.iv_mineMessage_issueImage);
        final RoundImageView commenHead = holder.getView(R.id.iv_mineMessage_commenImage);
        TextView tv_mineMessage_response = holder.getView(R.id.tv_mineMessage_response);
        holder.setText(R.id.tv_mineMessage_headContent, dataBean.getPosts_content());
        holder.setText(R.id.tv_mineMessage_headtitle, dataBean.getPosts_member_name());
        holder.setText(R.id.tv_mineMessage_personLocation, dataBean.getLocation());
        holder.setText(R.id.tv_mineMessage_issueTime, dataBean.getCtime());
        holder.setText(R.id.mineMessage_discuss_message, dataBean.getContent());


        //本人头像
        options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.circleCrop();
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getPosts_member_avatar()).apply(options).into(issueHead);
        //加载回复头像
        Glide.with(mContext).load(dataBean.getReply_member_avatar()).apply(options).into(commenHead);

        tv_mineMessage_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commontMeClickListener.ResponseCommentListener(position);
            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.mine_message_list;
    }

    public interface CommontMeClickListener {
        void ResponseCommentListener(int position);
    }
}
