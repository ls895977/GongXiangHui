package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.FireSearchBean;

import java.util.List;


/**
 * Created by Scout
 * Created on 2017/11/16 18:25.
 */

public class FireSearchAdapter extends BaseRecycleViewAdapter<FireSearchBean.DataBean> {

    public FireSearchAdapter(Context context, List<FireSearchBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, FireSearchBean.DataBean fireSearchBean) {
        holder.setText(R.id.tv_fire_search_item, fireSearchBean.getName());
    }

    @Override
    protected int getItemView() {
        return R.layout.fire_search_item;
    }
}
