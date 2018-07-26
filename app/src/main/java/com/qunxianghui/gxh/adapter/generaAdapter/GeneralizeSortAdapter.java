package com.qunxianghui.gxh.adapter.generaAdapter;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;


public class GeneralizeSortAdapter extends BaseRecycleViewAdapter<EmployeePaiHangBean.DataBean>{


    public GeneralizeSortAdapter(Context context, List<EmployeePaiHangBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, EmployeePaiHangBean.DataBean dataBean) {
         RoundImageView generalizePersonHead = holder.getView(R.id.iv_generalize_person_head);
        holder.setText(R.id.iv_generalize_person_name,dataBean.getMember_name());
        holder.setText(R.id.iv_generalize_person_count,dataBean.getCnt()+"次");


        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher);
        options.error(R.mipmap.ic_launcher);
        Glide.with(mContext).load(dataBean.getMember_avatar()).apply(options).into(generalizePersonHead);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_generalize_sort;
    }
}
