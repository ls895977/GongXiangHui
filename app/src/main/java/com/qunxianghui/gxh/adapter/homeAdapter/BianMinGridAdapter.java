package com.qunxianghui.gxh.adapter.homeAdapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class BianMinGridAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private int[] images;
    private String[] text;
    private OnItemClickListener mOnItemClickListener;

    public BianMinGridAdapter(Context context, int[] images, String[] text) {
        this.images = images;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolder(layoutInflater.inflate(R.layout.bianmin_service_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TabViewHolder holde = (TabViewHolder) holder;
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

    public void setOnClickListener(OnItemClickListener onClickListener){
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bianmin_text)
        TextView name;
        @BindView(R.id.iv_bianmin_image)
        ImageView iv;

        TabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onpicItemClick(int position);
    }

}
