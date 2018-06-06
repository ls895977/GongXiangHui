package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.mine.MineMessageSystemBean;

import java.util.List;

public class MineMessageSystemAdapter extends BaseRecycleViewAdapter<MineMessageSystemBean.DataBean> {

    public MineMessageSystemAdapter(Context context, List<MineMessageSystemBean.DataBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MineMessageSystemBean.DataBean dataBean) {
        holder.setText(R.id.tv_mine_message_system_time, dataBean.getCtime());
        holder.setText(R.id.tv_mine_message_system_content, dataBean.getContent());
    }

    @Override
    protected int getItemView() {
        return R.layout.item_mine_message_system;
    }
}
