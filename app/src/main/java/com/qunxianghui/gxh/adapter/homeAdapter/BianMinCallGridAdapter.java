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

public class BianMinCallGridAdapter extends RecyclerView.Adapter {
    private LayoutInflater layoutInflater;
    private Context mContext;
    private int[] imagesHead;
    private int[] imagesCall;
    private String[] NameText;
    private String[] CallText;
    private OnItemClickListener mOnItemClickListener;

    public BianMinCallGridAdapter(Context context, int[] imagesHead, int[] imagesCall, String[] nameText, String[] callText) {
        mContext = context;
        this.imagesHead = imagesHead;
        this.imagesCall = imagesCall;
        NameText = nameText;
        CallText = callText;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TabViewHolder(layoutInflater.inflate(R.layout.bianmin_servicecall_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final TabViewHolder holde = (TabViewHolder) holder;
        holde.ivCallImage.setImageResource(imagesHead[position]);
        holde.ivPhoneCall.setImageResource(imagesCall[position]);
        holde.Iconname.setText(NameText[position]);
        holde.NameCall.setText(NameText[position]);

        holde.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onpicItemClick(holde.getAdapterPosition());
                }
            }
        });
    }

    public void setOnClickListener(OnItemClickListener onClickListener) {
        this.mOnItemClickListener = onClickListener;
    }

    @Override
    public int getItemCount() {
        return NameText.length;
    }

    public static class TabViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_bianmin_iconname)
        TextView Iconname;
        @BindView(R.id.tv_bianmin_namecall)
        TextView NameCall;
        @BindView(R.id.iv_bianmin_call_image)
        ImageView ivCallImage;
        @BindView(R.id.tv_bianmin_phonecall)
        ImageView ivPhoneCall;

        TabViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onpicItemClick(int position);
    }

}
