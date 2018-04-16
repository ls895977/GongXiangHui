package com.gongxianghui.adapter.homeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class BianMinGridAdapter extends BaseRecycleViewAdapter {
    public BianMinGridAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {

    }

    @Override
    protected int getItemView() {
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
