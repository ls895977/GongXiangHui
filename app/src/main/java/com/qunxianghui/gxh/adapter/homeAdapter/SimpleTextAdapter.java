package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/4 0004.
 */

public class SimpleTextAdapter extends BaseRecycleViewAdapter<String> {
    public SimpleTextAdapter(Context context, List datas) {
        super(context, datas);
    }


    @Override
    protected void convert(MyViewHolder holder, int position, String s) {
        holder.setText(R.id.tv_simple_1_line, s);

    }

    @Override
    protected int getItemView() {
        return  R.layout.search_history_item;
    }


}
