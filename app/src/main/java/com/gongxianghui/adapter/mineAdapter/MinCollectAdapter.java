package com.gongxianghui.adapter.mineAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.bean.mine.CollectBean;
import com.gongxianghui.utils.GlideApp;


import java.util.List;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MinCollectAdapter extends RecyclerView.Adapter<MinCollectAdapter.MyViewHolder> {
    private List<CollectBean.ListBean> data;
    private Context mContext;
    private View view;
    private OnRecycleItemListener listener;

    public MinCollectAdapter(List<CollectBean.ListBean> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        view = LayoutInflater.from(mContext).inflate(R.layout.layout_mycollect_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.tv_myCollect_item.setText(data.get(i).getName());
        GlideApp
                .with(mContext)
                .load(data.get(i).getImgUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(myViewHolder.iv_myCollect);


    }



    @Override
    public int getItemCount() {
        return data.size();

    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_myCollect_item;
        private LinearLayout ll_myCollect_item;
        private final ImageView iv_myCollect;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_myCollect_item = itemView.findViewById(R.id.tv_myCollect_item);
            ll_myCollect_item = itemView.findViewById(R.id.ll_myCollect_item);
            iv_myCollect = itemView.findViewById(R.id.iv_myCollect);


        }
    }
    public interface OnRecycleItemListener <T>{
        void OnRecycleItemClick(View v,T o);
    }
}
