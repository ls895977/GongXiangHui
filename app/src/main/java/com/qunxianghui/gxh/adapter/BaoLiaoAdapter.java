package com.qunxianghui.gxh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.imagepicker.bean.ImageItem;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.BaoLiaoBean;

import java.util.ArrayList;
import java.util.List;

public class BaoLiaoAdapter extends RecyclerView.Adapter<BaoLiaoAdapter.BaoLiaoViewHolder> {

    private Context mContext;
    public List<BaoLiaoBean> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener mListener;
    private boolean isAdded;   //是否额外添加了最后一个图片

    private BaoLiaoItemAdapter mCurrentAdapter;
    private int mCurrentPosition;

    public ArrayList<ImageItem> getImages() {
        return mCurrentAdapter.getImages();
    }

    public void setImages(ArrayList<ImageItem> images) {
        mData.get(mCurrentPosition).mList = images;
        mCurrentAdapter.setImages(mCurrentPosition,images);
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position, int type);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    public BaoLiaoAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = new ArrayList<>();
        mData.add(new BaoLiaoBean());
    }

    public List<BaoLiaoBean> getData(){
        return mData;
    }

    public void addItem(int index) {
        mData.add(index + 1, new BaoLiaoBean());
        notifyDataSetChanged();
    }

    public void remove(int index) {
        if (mData.size() == 1) {
            return;
        }
        mData.remove(index);
        notifyDataSetChanged();
    }

    @Override
    public BaoLiaoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaoLiaoViewHolder(mInflater.inflate(R.layout.item_baoliao, parent, false));
    }

    @Override
    public void onBindViewHolder(final BaoLiaoViewHolder holder, int position) {
        BaoLiaoBean baoLiaoBean = mData.get(position);
        final BaoLiaoItemAdapter adapter = new BaoLiaoItemAdapter(mContext,position ,baoLiaoBean.mList, 1);
        holder.mRv.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaoLiaoItemAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (mListener != null)
                    mCurrentPosition = holder.getAdapterPosition();
                    mCurrentAdapter = adapter;
                    mListener.onItemClick(holder.mRv, mCurrentPosition, position);
            }
        });
        if (!TextUtils.isEmpty(baoLiaoBean.mEt)) {
            holder.mEtContent.setText(baoLiaoBean.mEt);
        } else {
            holder.mEtContent.setText("");
        }
        holder.mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) mListener.onItemClick(v, holder.getAdapterPosition(), 0);
            }
        });
        holder.mEtContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mData.get(holder.getAdapterPosition()).mEt = s.toString();
            }
        });
        holder.mTvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null)
                    mListener.onItemClick(v, holder.getAdapterPosition(), 0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class BaoLiaoViewHolder extends RecyclerView.ViewHolder {

        private ImageView mIvDelete;
        private EditText mEtContent;
        private RecyclerView mRv;
        private TextView mTvAdd;
        private int mPosition;

        private BaoLiaoViewHolder(View itemView) {
            super(itemView);
            mIvDelete = itemView.findViewById(R.id.iv_delete);
            mEtContent = itemView.findViewById(R.id.et_content);
            mRv = itemView.findViewById(R.id.rv);
            mTvAdd = itemView.findViewById(R.id.tv_add);
        }

    }
}
