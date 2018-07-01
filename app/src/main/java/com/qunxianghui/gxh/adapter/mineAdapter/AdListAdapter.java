package com.qunxianghui.gxh.adapter.mineAdapter;

import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.mine.AdListBean;
import com.qunxianghui.gxh.fragments.mineFragment.activity.AdvertisConmmengtActivity;
import com.qunxianghui.gxh.utils.GlideApp;
import com.qunxianghui.gxh.utils.GlideRequest;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/6/11.
 */

public class AdListAdapter extends RecyclerView.Adapter<AdListAdapter.ViewHolder> {

    private AdListener listener;
    public List<AdListBean.DataBean> mList = new ArrayList<>();
    private int mType;

    public AdListAdapter setType(int type) {
        this.mType = type;
        return this;
    }

    public void setDatas(List<AdListBean.DataBean> datas) {
        if (datas != null) {
            mList.clear();
            mList.addAll(datas);
        }
    }

    public void setAdOnClickListen(AdListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_addadver_bigimag, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        AdListBean.DataBean dataBean = mList.get(position);
        GlideRequest<Drawable> glide = GlideApp.with(MyApplication.getMyApplicaiton()).load(dataBean.images).
                placeholder(R.mipmap.user_moren).
                error(R.mipmap.user_moren);
        switch (mType) {
            case 1:
                holder.mItemAdlist1.setVisibility(View.VISIBLE);
                glide.into(holder.mItemAdlist1);
                break;
            case 3:
                holder.mItemAdlist3.setVisibility(View.VISIBLE);
                glide.into(holder.mItemAdlist3);
                break;
            case 6:
                holder.mItemAdlist6.setVisibility(View.VISIBLE);
                glide.into(holder.mItemAdlist6);
                break;
            case 2:
            case 4:
            case 5:
                holder.mRlLayout.setVisibility(View.VISIBLE);
                glide.into(holder.mIvAvator);
                if (mType == 2) {
                    holder.mTvName.setText(String.format("%s%s", "姓名: ", dataBean.settings.name == null ? "" : dataBean.settings.name));
                    holder.mTvNumber.setText(String.format("%s%s", "电话: ", dataBean.settings.mobile == null ? "" : dataBean.settings.mobile));
                    holder.mTvAddress.setText(String.format("%s%s", "地址: ", dataBean.settings.address == null ? "" :dataBean.settings.address));
                }else if( mType == 4){
                    holder.mTvName.setText(String.format("%s%s", "二维码名称: ", dataBean.settings.name == null ? "" : dataBean.settings.name));
                    holder.mTvNumber.setText(String.format("%s%s", "介绍: ", dataBean.settings.intro == null ? "" : dataBean.settings.intro));
                }else if(mType == 5){
                    holder.mTvName.setText(String.format("%s%s", "昵称: ", dataBean.settings.nick == null ? "" : dataBean.settings.nick));
                    holder.mTvNumber.setText(String.format("%s%s", "QQ: ", dataBean.settings.qq == null ? "" : dataBean.settings.qq));
                    holder.mTvAddress.setText(String.format("%s%s", "介绍: ", dataBean.settings.intro == null ? "" :dataBean.settings.intro));
                }

                break;
        }

        holder.mCbAddbigimgAddlunbo.setChecked(dataBean.is_slide == 1);

        holder.mTvAddbigimgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onEditClick(holder.getAdapterPosition() - 1);
            }
        });

        holder.mTvAddbigimgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onDeleteClick(holder.getAdapterPosition() -1 );
            }
        });

        holder.mCbAddbigimgAddlunbo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listener.onAddCarousel(holder.getAdapterPosition() - 1, isChecked);
            }
        });

        if (AdvertisConmmengtActivity.sIsFromNews) {
            holder.mTvAddbigimgToUsed.setVisibility(View.VISIBLE);
            holder.mTvAddbigimgToUsed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onUsed(holder.getAdapterPosition() - 1);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_adlist_1)
        ImageView mItemAdlist1;
        @BindView(R.id.item_adlist_3)
        ImageView mItemAdlist3;
        @BindView(R.id.item_adlist_6)
        ImageView mItemAdlist6;
        @BindView(R.id.iv_avator)
        ImageView mIvAvator;
        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_number)
        TextView mTvNumber;
        @BindView(R.id.tv_address)
        TextView mTvAddress;
        @BindView(R.id.rl_layout)
        RelativeLayout mRlLayout;
        @BindView(R.id.tv_addbigimg_edit)
        TextView mTvAddbigimgEdit;
        @BindView(R.id.tv_addbigimg_delete)
        TextView mTvAddbigimgDelete;
        @BindView(R.id.tv_addbigimg_to_used)
        TextView mTvAddbigimgToUsed;
        @BindView(R.id.cb_addbigimg_addlunbo)
        CheckBox mCbAddbigimgAddlunbo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdListener {
        void onEditClick(int p);

        void onDeleteClick(int p);

        void onAddCarousel(int p, boolean ischecked);

        void onUsed(int p);
    }
}
