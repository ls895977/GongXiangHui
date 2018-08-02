package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.graphics.Color;
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

public class AdvertAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private int[] images;
    private String[] text;
    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public AdvertAdapter(Context context, int[] images, String[] text) {
        this.images = images;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.common_adver_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder holde = (ViewHolder) holder;
        if (position < 2) holde.mTv.setTextColor(Color.parseColor("#D71B24"));
        holde.mIv.setImageResource(images[position]);
        holde.mTv.setText(text[position]);
        if (position == 9) {
            holde.mLl.setBackgroundResource(R.drawable.bg_red_solid_corner);
            holde.mTv.setTextColor(Color.WHITE);
        }
        holde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holde.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        ImageView mIv;
        View mLl;

        ViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.tv_bianmin_text);
            mIv = itemView.findViewById(R.id.iv_bianmin_image);
            mLl = itemView.findViewById(R.id.ll);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
