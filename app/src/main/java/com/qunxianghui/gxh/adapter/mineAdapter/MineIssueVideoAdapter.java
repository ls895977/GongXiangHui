package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueVideoBean;

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
    protected void convert(MyViewHolder holder, final int position, final MineIssueVideoBean.DataBean dataBean) {
        holder.setText(R.id.tv_item_issue_vido_time, dataBean.getNewctime());
        final TextView deleteVideo = holder.getView(R.id.tv_item_issue_video_delete);
        deleteVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myIssueVideoClikListener.deleVideoItem(position);
                notifyDataSetChanged();
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
