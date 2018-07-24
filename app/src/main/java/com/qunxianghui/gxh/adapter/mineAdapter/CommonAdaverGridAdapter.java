package com.qunxianghui.gxh.adapter.mineAdapter;


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

public class CommonAdaverGridAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private int[] images;
    private String[] text;
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public CommonAdaverGridAdapter(Context context, int[] images, String[] text) {
        this.images = images;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolde(layoutInflater.inflate(R.layout.common_adver_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TabViewHolde holde = (TabViewHolde) holder;
        holde.iv .setImageResource(images[position]);
        holde.name.setText(text[position]);
        holde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onpicItemClick(holde.getAdapterPosition());
                }
            }
        });
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

    public interface OnItemClickListener{
        void onpicItemClick(int position);
    }

}
