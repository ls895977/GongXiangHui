package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.QueryBean;

import java.util.List;

public class QueryResultAdapter extends BaseRecycleViewAdapter<QueryBean.DataBean> {
    public QueryResultAdapter(Context context, List datas) {
        super(context, datas);
    }



    @Override
    protected void convert(MyViewHolder holder, int position, QueryBean.DataBean dataBean) {
        holder.setText(R.id.tv_simple_1_line, dataBean.getCarName());
    }

    @Override
    protected int getItemView() {
        return R.layout.simple_1_line_text;
    }


}
