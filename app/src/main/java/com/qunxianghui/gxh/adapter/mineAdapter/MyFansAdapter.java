package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineFansBean;

import java.util.List;

public class MyFansAdapter extends BaseRecycleViewAdapter<MineFansBean.DataBean> {

    public MyFansAdapter(Context context, List<MineFansBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineFansBean.DataBean dataBean) {
        final ImageView headImave = holder.getView(R.id.iv_mine_fans_head);

        holder.setText(R.id.tv_mine_fans_title,dataBean.getMember_name());


        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.placeholder(R.mipmap.default_img);
        Glide.with(mContext).load(dataBean.getMember_avatar()).apply(options).into(headImave);
    }

    @Override
    protected int getItemView() {
        return R.layout.fragment_myfans_item;
    }
}
