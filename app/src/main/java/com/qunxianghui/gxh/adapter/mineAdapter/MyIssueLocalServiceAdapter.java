package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;

import java.util.List;

public class MyIssueLocalServiceAdapter extends BaseRecycleViewAdapter<MineIssueLocalServiceBean.DataBean> {
    public MyIssueLocalServiceAdapter(Context context, List<MineIssueLocalServiceBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineIssueLocalServiceBean.DataBean dataBean) {
        holder.setText(R.id.tv_myissue_localservice_title, "发布的标题");
        holder.setText(R.id.tv_myissue_localservice_phone,"18356265985");
        holder.setText(R.id.tv_myissue_localservice_call,"18356265985");
        ImageView mLocalServicePic = holder.getView(R.id.tv_myissue_localservice_pic);
        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(mContext).load(dataBean.getImages()).apply(options).into(mLocalServicePic);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_myissue_localservice;
    }
}
