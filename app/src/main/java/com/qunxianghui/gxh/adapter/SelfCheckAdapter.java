package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.ArrayList;
import java.util.List;

public class SelfCheckAdapter extends RecyclerView.Adapter<SelfCheckAdapter.MyViewHolder> {
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private Context mContext;
    private OnItemClickListener mOnItemClickListener;
    private List<MyLiveList> mMyLiveLists;

    public SelfCheckAdapter(Context context) {
        mContext = context;
    }

    public void notifyAdapter(List<MyLiveList> myLiveLists, boolean isAdd) {
        if (!isAdd) {
            this.mMyLiveLists = myLiveLists;

        } else {
            this.mMyLiveLists.addAll(myLiveLists);
        }
        notifyDataSetChanged();
    }

    public List<MyLiveList> getMyLiveLists() {
        if (mMyLiveLists == null) {
            mMyLiveLists = new ArrayList<>();
        }
        return mMyLiveLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_live, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        MyLiveList myLive = mMyLiveLists.get(holder.getAdapterPosition());
        holder.mTvTitle.setText(myLive.getTitle());
        holder.mTvSource.setText(myLive.getSource());
        if (mEditMode == MYLIVE_MODE_CHECK) {
            holder.mCheckBox.setVisibility(View.GONE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
            if (myLive.isSelect()) {
                holder.mCheckBox.setImageResource(R.mipmap.ic_checked);

            } else {
                holder.mCheckBox.setImageResource(R.mipmap.ic_uncheck);
            }
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(holder.getAdapterPosition(), mMyLiveLists);
            }

        });


    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<MyLiveList> myLiveList);
    }

    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMyLiveLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView mRadioImg;
        public TextView mTvTitle;
        public TextView mTvSource;
        public RelativeLayout mRootView;
        public ImageView mCheckBox;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mRadioImg = itemView.findViewById(R.id.tv_title);
            mTvSource = itemView.findViewById(R.id.tv_title);
            mRootView = itemView.findViewById(R.id.tv_title);
            mCheckBox = itemView.findViewById(R.id.tv_title);
        }
    }
}
