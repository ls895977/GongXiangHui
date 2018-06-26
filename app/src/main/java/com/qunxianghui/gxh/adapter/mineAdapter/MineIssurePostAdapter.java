package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssurePostBean;
import com.qunxianghui.gxh.widget.BigListView;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.List;

public class MineIssurePostAdapter extends BaseRecycleViewAdapter<MineIssurePostBean.DataBean.ListBean>implements MineCollectPostAdapter.MycollectPostListener {

    private MyPostOnClickListener postOnClickListener;

    public void setPostOnClickListener(MyPostOnClickListener postOnClickListener) {
        this.postOnClickListener = postOnClickListener;
    }

    public MineIssurePostAdapter(Context context, List<MineIssurePostBean.DataBean.ListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, MineIssurePostBean.DataBean.ListBean listBean) {
        final List<String> images = (List<String>) listBean.getImages();
        final MyGridView myGridView = holder.getView(R.id.layout_nine_grid_mineissue_post);
        holder.setText(R.id.tv_mine_issue_post_name, listBean.getMember_name());
        holder.setText(R.id.tv_mine_issue_post_content, listBean.getContent());
        holder.setText(R.id.tv_mine_issue_post_issuetime, listBean.getCtime());



        //设置九宫哥
        final LocationGridAdapter myIssuePostAdapter = new LocationGridAdapter(mContext, images);
        myGridView.setAdapter(myIssuePostAdapter);
        myIssuePostAdapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
            @Override
            public void onClick(View v, int p) {
                postOnClickListener.onPicClick(position,p);
            }
        });

        final TextView tv_mypost_discuss = holder.getView(R.id.tv_mine_issue_post_discuss);
        final TextView tv_mypost_good = holder.getView(R.id.tv_mine_issue_post_like);

        final TextView tv_mine_issue_post_delete = holder.getView(R.id.tv_mine_issue_post_delete);
        final TextView tv_mine_issue_post_collect = holder.getView(R.id.tv_mine_issue_post_collect);

/**
 * 点击了评论
 */
        tv_mypost_discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了评论", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * 点击了点赞
         */
        tv_mypost_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了点赞", Toast.LENGTH_SHORT).show();
                postOnClickListener.onLaunLikeClick(position);
            }
        });
        /**
         * 点击了收藏
         */
        tv_mine_issue_post_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了收藏", Toast.LENGTH_SHORT).show();
                postOnClickListener.onCollectionItemClick(position);
            }
        });
        /**
         * 点击了删除
         */
        tv_mine_issue_post_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了删除", Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_issue_post;
    }

    @Override
    public void cancelCollect(int position) {

    }

    @Override
    public void onPicClick(int position, int picpostion) {

    }


    public  interface  MyPostOnClickListener{
        /* 收藏*/
        void  onCollectionItemClick(int position);
        /* 点赞*/
        void  onLaunLikeClick(int position);
        /* 图片点击*/
        void onPicClick(int position, int picpostion);
    }
}
