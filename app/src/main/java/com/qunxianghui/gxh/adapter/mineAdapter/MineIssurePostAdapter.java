package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.ui.fragments.locationFragment.adapter.CommentItemAdapter;
import com.qunxianghui.gxh.widget.BigListView;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.List;

public class MineIssurePostAdapter extends RecyclerView.Adapter<MineIssurePostAdapter.ViewHolder> implements MineCollectPostAdapter.MycollectPostListener {
    private MyPostOnClickListener postOnClickListener;
    private List<TestMode.DataBean.ListBean> mList;
    private Context mContext;
    private CommentItemAdapter commentItemAdapter;
    public void setPostOnClickListener(MyPostOnClickListener postOnClickListener) {
        this.postOnClickListener = postOnClickListener;
    }
    public MineIssurePostAdapter(Context context,  List<TestMode.DataBean.ListBean> dataBeanList){
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
        TestMode.DataBean.ListBean listBean = mList.get(position);
        final String collect = mList.get(position).getCollect();
        if (collect.length() == 0 || collect == null) {
            holder.mTvCollect.setText("已收藏");
            holder.mIvCollect.setBackgroundResource(R.drawable.collect);

        } else {
            holder.mTvCollect.setText("收藏");
            holder.mIvCollect.setBackgroundResource(R.drawable.collect_normal);
        }
        List<TestMode.DataBean.ListBean.ClickLikeBean> click_like = mList.get(position).getClick_like();
        String like_info_res = mList.get(position).getLike_info_res();

        if (!TextUtils.isEmpty(like_info_res)) {
            holder.mTvlike.setText("已赞");
            holder.mIvLike.setBackgroundResource(R.mipmap.icon_good_true);
        } else {
            holder.mTvlike.setText("点赞");

            holder.mIvLike.setBackgroundResource(R.mipmap.icon_good);
        }
        List<String> images = ((List<String>) mList.get(position).getImages());
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

                if (postOnClickListener != null) {
                    postOnClickListener.onCommentClick(position, "");
                }
            }
        });
        holder.mLLLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postOnClickListener.onLaunLikeClick(position);
            }
        });
        holder.mLLCollect.setOnClickListener(new View.OnClickListener() {
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

        if (mList.get(position).getComment_res().size() != 0) {
            holder.llCommentBody.setVisibility(View.VISIBLE);
            commentItemAdapter = new CommentItemAdapter(mContext, mList.get(position).getComment_res(), holder.comment_list);
            holder.comment_list.setAdapter(commentItemAdapter);
        } else {
            holder.llCommentBody.setVisibility(View.GONE);
        }
        //点赞用户
        if ( mList.get(position).getTem().size() >0 ){
            holder.llCommentBody.setVisibility(View.VISIBLE);
            holder.click_like_user.setVisibility(View.VISIBLE);
            String content = "";
            for (int i = 0; i<mList.get(position).getTem().size(); i++){
                TestMode.DataBean.ListBean.ClickLikeBean like = mList.get(position).getTem().get(i);
                content = content + like.getMember_name() + " ";
            }
            holder.click_like_user.setText(content);
        }else {
            holder.click_like_user.setVisibility(View.GONE);
        }

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
        TextView click_like_user;
        EditText comment_edit;
        BigListView comment_list;
        ImageView mIvCollect;
        ImageView mIvLike;
        LinearLayout mLLCollect, mLLLike, llCommentBody;

        public ViewHolder(View itemView) {
            super(itemView);
            myGridView = itemView.findViewById(R.id.layout_nine_grid_mineissue_post);
            llCommentBody = itemView.findViewById(R.id.llCommentBody);
            mTvMineName = itemView.findViewById(R.id.tv_mine_issue_post_name);
            mTvMineContent = itemView.findViewById(R.id.tv_mine_issue_post_content);
            mTvIssueTime = itemView.findViewById(R.id.tv_mine_issue_post_issuetime);
            mTvDiscuss = itemView.findViewById(R.id.tv_mine_issue_post_discuss);
            mTvlike = itemView.findViewById(R.id.tv_mine_issue_post_like);
            mTvDelete = itemView.findViewById(R.id.tv_mine_issue_post_delete);
            mTvCollect = itemView.findViewById(R.id.tv_mine_issue_post_collect);
            mTvCollect = itemView.findViewById(R.id.tv_mine_issue_post_collect);
            mLLCollect = itemView.findViewById(R.id.ll_mine_issue_post_collect);
            mIvCollect = itemView.findViewById(R.id.iv_mine_issue_post_collect);
            mLLLike = itemView.findViewById(R.id.ll_mine_issue_post_like);
            click_like_user = itemView.findViewById(R.id.click_like_user);
            mIvLike = itemView.findViewById(R.id.iv_mine_issue_post_like);
            comment_list = itemView.findViewById(R.id.comment_list);
            comment_edit = itemView.findViewById(R.id.comment_edit);
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

        /*图片点击*/
        void onCommentClick(int position, String content);
    }
}
