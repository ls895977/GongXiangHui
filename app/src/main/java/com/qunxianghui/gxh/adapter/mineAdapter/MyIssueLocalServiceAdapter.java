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
    public MyIssueLocalServiceAdapter(Context context, List<MineIssueLocalServiceBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, final MineIssueLocalServiceBean.DataBean dataBean) {
        holder.setText(R.id.tv_myissue_localservice_title, dataBean.getCompany_name());
        holder.setText(R.id.tv_myissue_localservice_phone,dataBean.getMobile());
        holder.setText(R.id.tv_myissue_localservice_call,dataBean.getTel());
        ImageView mLocalServicePic = holder.getView(R.id.tv_myissue_localservice_pic);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getImages()).apply(options).into(mLocalServicePic);

        if (isShow) {
            //videoImag.setClickable(false);
            holder.getView(R.id.ch_delete).setVisibility(View.VISIBLE);
        } else {
            //videoImag.setClickable(true);
            holder.getView(R.id.ch_delete).setVisibility(View.GONE);
        }
        CheckBox checkBox = holder.getView(R.id.ch_delete);
        if (dataBean.isChecked() == true) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataBean.isChecked() == true) {
                    dataBean.setChecked(false);
                } else {
                    dataBean.setChecked(true);
                }
            }
        });
    }

    @Override
    protected int getItemView() {
        return R.layout.item_myissue_localservice;
    }
}
