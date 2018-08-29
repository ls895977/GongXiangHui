package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.GuessBean;

import java.util.List;


/**
 * Created by Scout
 * Created on 2017/11/16 18:25.
 */

    public class FireSearchAdapter extends BaseRecycleViewAdapter<GuessBean.DataBean> {

    public FireSearchAdapter(Context context, List<GuessBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, GuessBean.DataBean dataBean) {
        holder.setText(R.id.tv_simple_1_line,dataBean.getTitle());
    }

    @Override
    protected int getItemView() {
        return R.layout.hostory_item;
    }
}
