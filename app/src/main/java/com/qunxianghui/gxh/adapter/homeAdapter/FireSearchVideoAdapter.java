package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeVideoSearchBean;

import java.util.List;


public class FireSearchVideoAdapter extends BaseRecycleViewAdapter<HomeVideoSearchBean.DataBean> {

    public FireSearchVideoAdapter(Context context, List<HomeVideoSearchBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, HomeVideoSearchBean.DataBean dataBean) {
        holder.setText(R.id.tv_simple_1_line, dataBean.getTitle());
    }

    @Override
    protected int getItemView() {
        return R.layout.hostory_item;
    }
}
