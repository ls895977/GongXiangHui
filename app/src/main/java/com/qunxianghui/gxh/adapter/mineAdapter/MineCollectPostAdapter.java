package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.LocationGridAdapter;
import com.qunxianghui.gxh.bean.mine.MineCollectPostBean;
import com.qunxianghui.gxh.widget.MyGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MineCollectPostAdapter extends RecyclerView.Adapter<MineCollectPostAdapter.ViewHolder> {

    private MycollectPostListener mycollectPostListener;
    private List<MineCollectPostBean.DataBean> mList = new ArrayList<>();
    private Context mContext;

    public void setMycollectPostListener(MycollectPostListener mycollectPostListener) {
        this.mycollectPostListener = mycollectPostListener;
    }

    public MineCollectPostAdapter(Context context, List<MineCollectPostBean.DataBean> datas) {
        this.mContext = context;
        this.mList = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mycollect_post, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MineCollectPostBean.DataBean dataBean = mList.get(position);
        holder.mTvMinecollectPostTiem.setText(dataBean.getNewctime());
        holder.mTvMycollectPostTitle.setText(dataBean.getInfo().getContent());
        LocationGridAdapter locationGridAdapter = new LocationGridAdapter(mContext, dataBean.getImages());
        holder.mGvMineGrid.setAdapter(locationGridAdapter);
        holder.mTvCollectPostCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycollectPostListener.cancelCollect(holder.getAdapterPosition() - 1);
            }
        });
        locationGridAdapter.setListener(new LocationGridAdapter.ImageOnClickListener() {
            @Override
            public void onClick(View v, int position) {
                mycollectPostListener.onPicClick(position, holder.getAdapterPosition() - 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_mycollect_post_title)
        TextView mTvMycollectPostTitle;
        @BindView(R.id.gv_mine_grid)
        MyGridView mGvMineGrid;
        @BindView(R.id.tv_minecollect_post_tiem)
        TextView mTvMinecollectPostTiem;
        @BindView(R.id.tv_collect_post_cancel)
        TextView mTvCollectPostCancel;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface MycollectPostListener {
        /* 取消收藏*/
        void cancelCollect(int position);

        /* 图片点击*/
        void onPicClick(int position, int picpostion);
    }


}
