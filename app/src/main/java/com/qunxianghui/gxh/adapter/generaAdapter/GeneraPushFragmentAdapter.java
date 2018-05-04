package com.qunxianghui.gxh.adapter.generaAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

public class GeneraPushFragmentAdapter extends BaseRecycleViewAdapter {
    public GeneraPushFragmentAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
        holder.setText(R.id.tv_genera_push_industy, "互推企业");
        holder.setText(R.id.tv_genera_education_organiza, "教育机构");
    }

    @Override
    protected int getItemView() {
        return R.layout.genera_push_item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
