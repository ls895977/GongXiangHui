package com.qunxianghui.gxh.adapter.homeAdapter;


import android.content.Context;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class HomeSalerListAdapter extends BaseRecycleViewAdapter {


    public HomeSalerListAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
        holder.setText(R.id.title_tv, "赵龙涛");
        holder.setText(R.id.context_tv, "赵龙涛");
    }

    @Override
    protected int getItemView() {
        return R.layout.recyclerview_item;
    }
}
