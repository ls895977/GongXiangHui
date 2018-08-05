package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

public class AdvanceAdapter extends BaseRecycleViewAdapter<String> {

    public AdvanceAdapter(Context context, List<String> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, String s) {
        ImageView mAdVanceView = holder.getView(R.id.iv_company_advan);
        mAdVanceView.setBackgroundResource(R.mipmap.bianmin_hotel);

        holder.setText(R.id.tv_iv_company_advan_title,"企业优势");

        holder.setText(R.id.tv_iv_company_advan_content,"ssssssssssssssssssssss");
    }

    @Override
    protected int getItemView() {
        return R.layout.item_company_advance;
    }
}
