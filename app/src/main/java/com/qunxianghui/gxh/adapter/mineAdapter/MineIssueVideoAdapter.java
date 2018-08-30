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

    private int uuid;
    private MyIssueVideoClikListener myIssueVideoClikListener;
    private String mImage;
    public boolean isShow = false;
    public void setMyIssueVideoClikListener(MyIssueVideoClikListener myIssueVideoClikListener) {
        this.myIssueVideoClikListener = myIssueVideoClikListener;
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

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);

        Glide.with(mContext).load(mImage).apply(options).into(iconMyIssueVideo);

        if (isShow) {
            //videoImag.setClickable(false);
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            //videoImag.setClickable(true);
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
        }
        CheckBox checkBox = holder.getView(R.id.ch_delete);
        if (dataBean.isChecked() == true) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isChecked() == true) {
                    dataBean.setChecked(false);
                } else {
                    dataBean.setChecked(true);
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_issue_video;
    }

    public interface MyIssueVideoClikListener {
        void deleVideoItem(int position);
    }
}
