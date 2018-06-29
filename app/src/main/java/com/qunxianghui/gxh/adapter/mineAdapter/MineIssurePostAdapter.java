package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.List;

public class MineIssurePostAdapter extends RecyclerView.Adapter<MineIssurePostAdapter.ViewHolder> implements MineCollectPostAdapter.MycollectPostListener {

    private MyPostOnClickListener postOnClickListener;
    private List<MineIssurePostBean.DataBean.ListBean> mList;
    private Context mContext;

    public void setPostOnClickListener(MyPostOnClickListener postOnClickListener) {
        this.postOnClickListener = postOnClickListener;
    }

    public MineIssurePostAdapter(Context context, List<MineIssurePostBean.DataBean.ListBean> dataBeanList) {
        mContext = context;
        this.mList = dataBeanList;
    }

    @Override
    public MineIssurePostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_mine_issue_post, parent, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(final MineIssurePostAdapter.ViewHolder holder, final int position) {
        MineIssurePostBean.DataBean.ListBean listBean = mList.get(position);
        List<String> images = ((List<String>) mList.get(position).getImages());
        final String collect = mList.get(position).getCollect();
        holder.mTvMineName.setText(listBean.getMember_name());
        holder.mTvMineContent.setText(listBean.getContent());
        holder.mTvIssueTime.setText(listBean.getCtime());
        LocationGridAdapter myIssuePostAdapter = new LocationGridAdapter(mContext, images);
        holder.myGridView.setAdapter(myIssuePostAdapter);
        myIssuePostAdapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
            @Override
            public void onClick(View v, int p) {
                postOnClickListener.onPicClick(holder.getAdapterPosition(), p);
            }
        });
        holder.mTvDiscuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了评论", Toast.LENGTH_SHORT).show();
            }
        });
        holder.mTvlike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postOnClickListener.onLaunLikeClick(holder.getAdapterPosition());
            }
        });
        holder.mTvCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postOnClickListener.onCollectionItemClick(holder.getAdapterPosition());
            }
        });
        holder.mTvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了删除", Toast.LENGTH_SHORT).show();
                postOnClickListener.deletePost(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public void cancelCollect(int position) {

    }

    @Override
    public void onPicClick(int position, int picpostion) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MyGridView myGridView;
        TextView mTvMineName;
        TextView mTvMineContent;
        TextView mTvIssueTime;
        TextView mTvDiscuss;
        TextView mTvlike;
        TextView mTvDelete;
        TextView mTvCollect;

        public ViewHolder(View itemView) {
            super(itemView);
            myGridView = itemView.findViewById(R.id.layout_nine_grid_mineissue_post);
            mTvMineName = itemView.findViewById(R.id.tv_mine_issue_post_name);
            mTvMineContent = itemView.findViewById(R.id.tv_mine_issue_post_content);
            mTvIssueTime = itemView.findViewById(R.id.tv_mine_issue_post_issuetime);
            mTvDiscuss = itemView.findViewById(R.id.tv_mine_issue_post_discuss);
            mTvlike = itemView.findViewById(R.id.tv_mine_issue_post_like);
            mTvDelete = itemView.findViewById(R.id.tv_mine_issue_post_delete);
            mTvCollect = itemView.findViewById(R.id.tv_mine_issue_post_collect);
        }
    }


    public interface MyPostOnClickListener {
        /* 收藏*/
        void onCollectionItemClick(int position);

        /* 点赞*/
        void onLaunLikeClick(int position);

        /* 图片点击*/
        void onPicClick(int position, int picpostion);

        /*删除*/
        void deletePost(int position);
    }
}
