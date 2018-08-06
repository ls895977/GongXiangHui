package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;

import java.util.List;

public class AdvanceAdapter extends BaseRecycleViewAdapter<AddAdvanceBean.DataBean> {

    public AdvanceAdapter(Context context, List<AddAdvanceBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, AddAdvanceBean.DataBean dataBean) {

        ImageView mCompanyPic = holder.getView(R.id.iv_company_advan);
        holder.setText(R.id.tv_iv_company_advan_title, dataBean.getTitle());
        holder.setText(R.id.tv_iv_company_advan_content, dataBean.getDescribe());

        RequestOptions options = new RequestOptions();
        options.centerCrop();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        Glide.with(mContext).load(dataBean.getImage_array().get(0)).apply(options).into(mCompanyPic);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_company_advance;
    }
}
