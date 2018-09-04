package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;

import java.util.List;

public class MineIssueVideoAdapter extends BaseRecycleViewAdapter<MineIssueVideoBean.DataBean> {

    private String mImage;
    public boolean isShow = false;
    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public MineIssueVideoAdapter(Context context, List<MineIssueVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MineIssueVideoBean.DataBean dataBean) {
        ImageView iconMyIssueVideo = holder.getView(R.id.iv_item_issue_video_head);
        holder.setText(R.id.tv_item_issue_video_title,dataBean.getTitle());

        List<String> images = dataBean.getImages();
        for (int i=0; i<images.size();i++){
            mImage = images.get(i);

        }
        Glide.with(mContext).load(mImage).apply(new RequestOptions()
                .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(iconMyIssueVideo);
        if (isShow) {
            //videoImag.setClickable(false);
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            //videoImag.setClickable(true);
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
        }
        final CheckBox checkBox = holder.getView(R.id.ch_delete);
        checkBox.setChecked(dataBean.isChecked());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(!dataBean.isChecked());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    if (mCallback != null) mCallback.callback(dataBean.getUuid());
                } else {
                    checkBox.performClick();
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_issue_video;
    }

    public interface Callback {
        void callback(int id);
    }

}
