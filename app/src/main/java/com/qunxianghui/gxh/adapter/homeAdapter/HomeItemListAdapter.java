package com.qunxianghui.gxh.adapter.homeAdapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.MoreTypeBean;

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
   private OnItemClickListener onItemClickListener;

    public HomeItemListAdapter(List<MoreTypeBean> mData) {
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick( int position);
        void onLongClick( int position);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ) {
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        MoreTypeBean moreTypeBean = mData.get(position);

         RecyclerView.ViewHolder viewHolder;

        if (moreTypeBean.type == 0) {
            viewHolder = (PullImageHolder) holder;

        } else if (moreTypeBean.type == 1) {
            viewHolder = (RightImageHolder) holder;

        } else {
            viewHolder = (ThreeImageHolder) holder;
        }

       if(onItemClickListener!=null){
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    onItemClickListener.onClick(position);
               }
           });
           holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View v) {
                   onItemClickListener.onLongClick(position);
                   return false;
               }
           });
       }
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

    class PullImageHolder extends RecyclerView.ViewHolder {
        LinearLayout ll_homelistItem_text_only;
        TextView tv_homelistItem_text_content;

        public PullImageHolder(View itemView) {
            super(itemView);
            ll_homelistItem_text_only = itemView.findViewById(R.id.ll_homelistItem_text_only);
            tv_homelistItem_text_content = itemView.findViewById(R.id.tv_homelistItem_text_content);
        }
    }

    class RightImageHolder extends RecyclerView.ViewHolder {

        public RightImageHolder(View itemView) {
            super(itemView);
        }
    }


    class ThreeImageHolder extends RecyclerView.ViewHolder {

        public ThreeImageHolder(View itemView) {
            super(itemView);
        }
    }
}
