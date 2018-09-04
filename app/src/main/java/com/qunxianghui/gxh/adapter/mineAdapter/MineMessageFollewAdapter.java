package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageFollowBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageFollewAdapter extends BaseRecycleViewAdapter <MineMessageFollowBean.DataBean>{

    private ImageView mMineMessageResponse;
    private MineMessageResponseListener mineMessageResponseListener;
    private RequestOptions options;

    public void setMineMessageResponseListener(MineMessageResponseListener mineMessageResponseListener) {
        this.mineMessageResponseListener = mineMessageResponseListener;
    }

    public MineMessageFollewAdapter(Context context, List<MineMessageFollowBean.DataBean> datas) {
        super(context, datas);
    }
    @Override
    protected void convert(MyViewHolder holder, final int position, MineMessageFollowBean.DataBean dataBean) {
        mMineMessageResponse = holder.getView(R.id.iv_mineMessage_response);
        final RoundImageView issueHead = holder.getView(R.id.iv_mineMessagefollow_issueImage);
        final RoundImageView commenHead = holder.getView(R.id.iv_mineMessagefollow_commenImage);
        holder.setText(R.id.tv_mineMessagefollow_headContent,dataBean.getPosts_content());
        holder.setText(R.id.tv_mineMessagefollow_headtitle,dataBean.getPosts_member_name());
        holder.setText(R.id.tv_mineMessagefollow_personLocation,dataBean.getLocation());
        holder.setText(R.id.tv_mineMessagefollow_issueTime,dataBean.getCtime());
        holder.setText(R.id.mineMessagefollow_discuss_message,dataBean.getContent());
        //本人头像
        options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getPosts_member_avatar()).apply(options).into(issueHead);

        //加载回复头像
        Glide.with(mContext).load(dataBean.getReply_member_avatar()).apply(options).into(commenHead);

        mMineMessageResponse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mineMessageResponseListener.ResponseClick(position);
            }
        });
    }
    @Override
    protected int getItemView() {
        return R.layout.mine_message_followlist;
    }
    public interface  MineMessageResponseListener{
        void  ResponseClick(int position);
    }
}
