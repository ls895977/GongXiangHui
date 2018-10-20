package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;

import java.util.List;

public class MyIssueLocalServiceAdapter extends BaseRecycleViewAdapter<MineIssueLocalServiceBean.DataBean> {

    public boolean isShow = false;
    private Callback mCallback;

    public void setCallback(Callback callback) {
        this.mCallback = callback;
    }

    public MyIssueLocalServiceAdapter(Context context, List<MineIssueLocalServiceBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, final int position, final MineIssueLocalServiceBean.DataBean dataBean) {
        holder.setText(R.id.tv_myissue_localservice_title, dataBean.getCompany_name());
        holder.setText(R.id.tv_myissue_localservice_phone, dataBean.getMobile());
        holder.setText(R.id.tv_myissue_localservice_call, dataBean.getTel());
        ImageView mLocalServicePic = holder.getView(R.id.tv_myissue_localservice_pic);
        Glide.with(mContext).load(dataBean.getImages()).apply(new RequestOptions()
                .placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop()).into(mLocalServicePic);
        holder.getView(R.id.ch_delete).setVisibility(isShow ? View.VISIBLE : View.GONE);

        final CheckBox checkBox = holder.getView(R.id.ch_delete);
        checkBox.setChecked(dataBean.isChecked());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBean.setChecked(!dataBean.isChecked());
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isShow) {
                    if (mCallback != null) mCallback.callback(dataBean.getId());
                    mCallback.onItemClick(position);
                } else {
                    checkBox.performClick();
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_myissue_localservice;
    }

    public interface Callback {
        void callback(int id);
        void onItemClick(int position);

    }




}
