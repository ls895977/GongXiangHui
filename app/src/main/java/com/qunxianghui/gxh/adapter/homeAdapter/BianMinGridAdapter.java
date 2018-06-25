package com.qunxianghui.gxh.adapter.homeAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;


/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class BianMinGridAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private int[] images;
    private String[] text;
    private OnClickListener mOnClickListener;

    public BianMinGridAdapter(Context context, int[] images, String[] text) {
        this.images = images;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolde(layoutInflater.inflate(R.layout.bianmin_service_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TabViewHolde holde = (TabViewHolde) holder;
        holde.iv .setImageResource(images[position]);
        holde.name.setText(text[position]);
        holde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListener != null) {
                    mOnClickListener.onClick(holde.getAdapterPosition());
                }
            }
        });
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.mOnClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    private static class TabViewHolde extends RecyclerView.ViewHolder {
        TextView name;
        ImageView iv;

        TabViewHolde(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_bianmin_text);
            iv = itemView.findViewById(R.id.iv_bianmin_image);
        }
    }

    public interface OnClickListener{
        void onClick(int position);
    }

}
