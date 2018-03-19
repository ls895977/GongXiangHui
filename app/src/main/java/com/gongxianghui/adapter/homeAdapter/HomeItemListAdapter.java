package com.gongxianghui.adapter.homeAdapter;


import android.content.Context;

import com.gongxianghui.R;
import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeItemListAdapter extends BaseRecycleViewAdapter {


    public HomeItemListAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
        holder.setText(R.id.tv_recycler_top_des, "今天老百姓啊，真啊真高兴");
        holder.setText(R.id.tv_recycler_bottom_science, "科技");
        holder.setText(R.id.tv_recycler_bottom_science2, "科技");
        holder.setText(R.id.tv_recycler_bottom_science3, "科技");
    }

    @Override
    protected int getItemView() {
        return R.layout.recycler_home_item;
    }
}
