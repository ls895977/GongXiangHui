package com.qunxianghui.gxh.adapter.mineAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageBean;

import java.util.List;

public class MineMessageCommentAdapter extends BaseRecycleViewAdapter<MineMessageBean.DataBean> {

    private final RequestOptions mOptions;

    @SuppressLint("CheckResult")
    public MineMessageCommentAdapter(Context context, @Nullable List<MineMessageBean.DataBean> data) {
        super(context, data);

        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineMessageBean.DataBean dataBean) {
        ImageView ivAvatar = holder.getView(R.id.iv_user_avatar);
        if (TextUtils.isEmpty(dataBean.member_avatar)) {
            ivAvatar.setImageResource(R.mipmap.user_moren);
        } else {
            Glide.with(mContext).load(dataBean.member_avatar).apply(mOptions).into(ivAvatar);
        }
        holder.setText(R.id.tv_user_name, dataBean.member_nick);
        holder.setText(R.id.tv_time, dataBean.time);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvDetail = holder.getView(R.id.tv_detail);
        ImageView ivDetail = holder.getView(R.id.iv_detail);
        tvContent.setBackgroundResource(0);
        tvContent.setText("");
        tvDetail.setText("");
        ivDetail.setImageResource(0);
//        0：事件已删除或不存在；1：本地圈点赞；2：视频汇点赞；3：本地圈评论；4：视频汇评论；5：本地圈评论被回复；6：视频汇评论被回复；7：新闻评论被回复
        switch (holder.getItemViewType()) {
//            0：事件已删除或不存在；
            case 0:
                setDetail(dataBean, tvDetail, ivDetail);
                tvContent.setText("该评论已删除");
                tvContent.setBackgroundColor(Color.parseColor("#DCDCDC"));
                break;
//            1：本地圈点赞；
//            2：视频汇点赞；
//            3：本地圈评论；
//            4：视频汇评论；
//            5：本地圈评论被回复；
//            6：视频汇评论被回复；
//            7：新闻评论被回复
            case 1:
                setDetail(dataBean, tvDetail, ivDetail);
                tvContent.setBackgroundResource(R.mipmap.icon_local_good_select);
                break;
            case 3:
            case 5:
                setDetail(dataBean, tvDetail, ivDetail);
                tvContent.setText(dataBean.comment_reply);
                break;
            case 2:
                setDetail(dataBean, tvDetail, ivDetail);
                tvContent.setBackgroundResource(R.mipmap.home_video_collect_select);
                break;
            case 4:
            case 6:
                if (!TextUtils.isEmpty(dataBean.detail.images))
                    Glide.with(mContext).load(dataBean.detail.images).apply(mOptions).into(ivDetail);
                tvContent.setText(dataBean.comment_reply);
                break;
            case 7:
                tvContent.setText(dataBean.comment_reply);
                break;
        }
    }

    private void setDetail(MineMessageBean.DataBean dataBean, TextView tvDetail, ImageView ivDetail) {
        if (TextUtils.isEmpty(dataBean.detail.images))
            tvDetail.setText(dataBean.detail.content);
        else
            Glide.with(mContext).load(dataBean.detail.images).apply(mOptions).into(ivDetail);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_minemessage_addcomment;
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).type;
    }
}
