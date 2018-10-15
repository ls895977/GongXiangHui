package com.qunxianghui.gxh.adapter.baseAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Scout
 * Created on 2017/10/24 9:13.
 */

abstract public class BaseRecycleViewAdapter<T> extends
        RecyclerView.Adapter<BaseRecycleViewAdapter.MyViewHolder> {

    protected Context mContext;
    protected List<T> mDatas;
    protected OnItemClickListener mClickListener;

    public BaseRecycleViewAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(mContext).inflate(getItemView(), parent, false);
        final MyViewHolder holder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mClickListener != null)
                    mClickListener.onItemClick(v, holder.getLayoutPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleViewAdapter.MyViewHolder holder, int position) {
        convert(holder, position, mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void refresh(List<T> newDatas) {
        mDatas.clear();
        add(newDatas);
    }

    public void add(List<T> newDatas) {
        if (newDatas == null) return;
        mDatas.addAll(newDatas);
        notifyDataSetChanged();
    }

    /*在该方法内进行视图处理*/
    protected abstract void convert(MyViewHolder holder, int position, T t);

    /*返回需要显示的子视图*/
    protected abstract int getItemView();

    /*ViewHolder的基础封装*/
    public class MyViewHolder extends RecyclerView.ViewHolder {
        SparseArray<View> mViews;

        public MyViewHolder(View itemView) {
            super(itemView);
            mViews = new SparseArray<>();
        }

        public <T extends View> T getView(int id) {
            T view = (T) mViews.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViews.put(id, view);
            }
            return view;
        }

        public void setText(int id, @NonNull String text) {
            TextView tv = getView(id);
            tv.setText(text == null ? "" : text);
        }

        public void setImageRes(int id, @NonNull int imgRes) {
            ImageView iv = getView(id);
            iv.setImageResource(imgRes);
        }

    }

    /*实现的单击事件*/
    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

}
