package com.gongxianghui.adapter.homeAdapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gongxianghui.R;
import com.gongxianghui.bean.home.MoreTypeBean;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeItemListAdapter extends RecyclerView.Adapter {

    public static final int TYPE_PULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGE = 2;
    private List<MoreTypeBean> mData;
    private View view;

    public HomeItemListAdapter(List<MoreTypeBean> mData) {
        this.mData = mData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //创建不同的 ViewHolder
        //根据viewtype来创建条目

        if (viewType == TYPE_PULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_text_text, null);
            return new PullImageHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_right_img, null);
            return new RightImageHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_three_img, null);
            return new ThreeImageHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mData.get(position);
        if (moreTypeBean.type == 0) {
            return TYPE_PULL_IMAGE;
        } else if (moreTypeBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGE;
        }
    }

    private class PullImageHolder extends RecyclerView.ViewHolder {

        public PullImageHolder(View itemView) {
            super(itemView);
        }
    }

    private class RightImageHolder extends RecyclerView.ViewHolder {

        public RightImageHolder(View itemView) {
            super(itemView);
        }
    }


    private class ThreeImageHolder extends RecyclerView.ViewHolder {

        public ThreeImageHolder(View itemView) {
            super(itemView);
        }
    }
}
