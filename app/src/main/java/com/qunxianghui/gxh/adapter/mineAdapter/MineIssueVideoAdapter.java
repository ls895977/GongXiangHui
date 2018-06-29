package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.List;

public class MineIssueVideoAdapter extends BaseRecycleViewAdapter<MineIssueVideoBean.DataBean> {

    private int uuid;
    private MyIssueVideoClikListener myIssueVideoClikListener;

    public void setMyIssueVideoClikListener(MyIssueVideoClikListener myIssueVideoClikListener) {
        this.myIssueVideoClikListener = myIssueVideoClikListener;
    }


    public MineIssueVideoAdapter(Context context, List<MineIssueVideoBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, MineIssueVideoBean.DataBean dataBean) {

        final ImageView headImage = holder.getView(R.id.iv_item_issue_video_head);
        holder.setText(R.id.tv_item_issue_video_title, dataBean.getTitle());
        holder.setText(R.id.tv_item_issue_vido_time, dataBean.getNewctime());
        GlideApp.with(mContext).load(dataBean.getPicurl()).centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(headImage);

/**
 * 删除视频
 *
 */
        final TextView deleteVideo = holder.getView(R.id.tv_item_issue_video_delete);

        deleteVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              myIssueVideoClikListener.deleVideoItem(position);





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
