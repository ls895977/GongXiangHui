package com.qunxianghui.gxh.adapter.mineAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageBean;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.MineMessageActivity;
import com.qunxianghui.gxh.utils.ToastUtils;

import java.util.List;

public class MineMessageCommentAdapter extends BaseRecycleViewAdapter<MineMessageBean.DataBean> {

    private final RequestOptions mOptions;
    private Callback mCallback;

    @SuppressLint("CheckResult")
    public MineMessageCommentAdapter(Context context, @Nullable List<MineMessageBean.DataBean> data) {
        super(context, data);
        mOptions = new RequestOptions();
        mOptions.placeholder(R.mipmap.default_img)
                .error(R.mipmap.default_img)
                .centerCrop();
    }

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final MineMessageBean.DataBean dataBean) {
        ImageView ivAvatar = holder.getView(R.id.iv_user_avatar);
        if (TextUtils.isEmpty(dataBean.member_avatar)) {
            ivAvatar.setImageResource(R.mipmap.user_moren);
        } else {
            Glide.with(mContext).load(dataBean.member_avatar).apply(mOptions).into(ivAvatar);
        }
        holder.getView(R.id.tv_early).setVisibility(View.GONE);
        holder.setText(R.id.tv_user_name, dataBean.member_nick);
        holder.setText(R.id.tv_time, dataBean.time);
        TextView tvContent = holder.getView(R.id.tv_content);
        TextView tvDetail = holder.getView(R.id.tv_detail);
        ImageView ivDetail = holder.getView(R.id.iv_detail);
        View ivVideo = holder.getView(R.id.iv_detail_video);
        ivVideo.setVisibility(View.GONE);
        tvContent.setBackgroundResource(0);
        tvContent.setText("");
        tvDetail.setText("");
        ivDetail.setImageResource(0);
//       1：本地圈点赞；2：视频汇点赞；3：本地圈评论；4：视频汇评论；5：本地圈评论被回复；6：视频汇评论被回复；7：新闻评论被回复
        final int itemViewType = dataBean.type;
        switch (itemViewType) {
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
                setContentView(dataBean, tvContent);
                break;
            case 2:
                setDetail(dataBean, tvDetail, ivDetail);
                tvContent.setBackgroundResource(R.mipmap.home_video_collect_select);
                ivVideo.setVisibility(View.VISIBLE);
                break;
            case 4:
            case 6:
                setContentView(dataBean, tvContent);
                setDetail(dataBean, tvDetail, ivDetail);
                ivVideo.setVisibility(View.VISIBLE);
                break;
            case 7:
                setContentView(dataBean, tvContent);
                if (dataBean.comment_deleted == 0)
                    tvDetail.setText(dataBean.detail.title);
                break;
        }

        if (MineMessageActivity.mIsHasMsg && position == mDatas.size() - 1) {
            holder.getView(R.id.tv_early).setVisibility(View.VISIBLE);
            holder.getView(R.id.tv_early).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MineMessageActivity.mIsHasMsg = false;
                    mCallback.loadMore();
                }
            });
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.detail_deleted == 1) {
                    ToastUtils.showShort("该内容已被删除");
                    return;
                }
                switch (itemViewType) {
                    case 1:
                    case 3:
                    case 5:
                        mCallback.callback(1, dataBean);
                        break;
                    case 2:
                    case 4:
                    case 6:
                        mCallback.callback(2, dataBean);
                        break;
                    case 7:
                        mCallback.callback(7, dataBean);
                        break;
                }
            }
        });
    }

    private void setContentView(MineMessageBean.DataBean dataBean, TextView tvContent) {
        if (dataBean.comment_deleted == 0)
            tvContent.setText(dataBean.comment_reply);
        else {
            tvContent.setText("该评论已删除");
            tvContent.setBackgroundColor(Color.parseColor("#DCDCDC"));
        }
    }

    private void setDetail(MineMessageBean.DataBean dataBean, TextView tvDetail, ImageView ivDetail) {
        if (dataBean.detail_deleted == 1) {
            tvDetail.setText("该内容已删除");
            return;
        }
        if (TextUtils.isEmpty(dataBean.detail.images))
            tvDetail.setText(dataBean.detail.content);
        else
            Glide.with(mContext).load(dataBean.detail.images).apply(mOptions).into(ivDetail);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_minemessage_addcomment;
    }

    public interface Callback {
        void callback(int type, MineMessageBean.DataBean dataBean);

        void loadMore();
    }
}
