package com.qunxianghui.gxh.adapter.mineAdapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.ArrayList;
import java.util.List;

public class RefreshRecyclerAdapter extends RecyclerView.Adapter<RefreshRecyclerAdapter.MyViewHolder> {
    public static final int TYPE_ITEM = 0;  //普通的item_view
    public static final int TYPE_FOOT = 1;

    private Context context;
    private LayoutInflater mInflater;
    private List<String> mTitles = null;

    public RefreshRecyclerAdapter(Context context) {
        this.context = context;
        this.mTitles = mTitles;
        mInflater = LayoutInflater.from(context);
        this.mTitles = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int index = i + 1;
            mTitles.add("item" + index);
        }


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            final View view = mInflater.inflate(R.layout.simple_1_line_text, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            final MyViewHolder myViewHolder = new MyViewHolder(view);
            return myViewHolder;
        } else if (viewType == TYPE_FOOT) {
            View foot_view = mInflater.inflate(R.layout.layout_footer, parent, false);
            //这边可以做一些属性设置，甚至事件监听绑定
            //view.setBackgroundColor(Color.RED);
            final footViewHolder footViewHolder = new footViewHolder(foot_view);
        }
        return null;
    }

    /**
     * 数据的绑定显示
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_tv.setText(mTitles.get(position));
        holder.itemView.setTag(position);
    }


    @Override
    public int getItemCount() {
        return mTitles.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position + 1 == getItemCount()) {
            return TYPE_FOOT;
        } else {
            return TYPE_ITEM;
        }
    }

    //添加数据
    public void addItem(List<String> newDatas) {
        newDatas.addAll(mTitles);
        mTitles.removeAll(mTitles);
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(List<String> newDatas) {
        mTitles.addAll(newDatas);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView item_tv = null;

        public MyViewHolder(View itemView) {
            super(itemView);
            item_tv = itemView.findViewById(R.id.tv_simple_1_line);
        }
    }

    /**
     * Footer
     */
    class footViewHolder extends RecyclerView.ViewHolder {
        TextView tv_click_addmore = null;

        public footViewHolder(View itemView) {
            super(itemView);
            tv_click_addmore = itemView.findViewById(R.id.tv_click_addmore);

        }
    }
}
