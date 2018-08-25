package com.qunxianghui.gxh.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.List;

public class SelfCheckAdapter extends RecyclerView.Adapter<SelfCheckAdapter.MyViewHolder> {

    private List<MyLiveList> mMyLiveLists;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_live, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    public void notifyAdapter(List<MyLiveList> myLiveLists,boolean isAdd){

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mRadioImg;
        public TextView mTvTitle;
        public TextView mTvSource;
        public RelativeLayout mRootView;
        public ImageView mCheckBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle= itemView.findViewById(R.id.tv_title);
            mRadioImg= itemView.findViewById(R.id.tv_title);
            mTvSource= itemView.findViewById(R.id.tv_title);
            mRootView= itemView.findViewById(R.id.tv_title);
            mCheckBox= itemView.findViewById(R.id.tv_title);
        }
    }
}
