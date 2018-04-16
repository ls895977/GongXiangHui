package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageAdapter extends BaseRecycleViewAdapter {
    public MineMessageAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, Object o) {
//        holder.setText(R.id.tv_mineMessage_headtitle,"臭居居" );
//        holder.setText(R.id.tv_mineMessage_headContent,"图不错" );
//        holder.setText(R.id.tv_mineMessage_personLocation,"浙江杭州" );
//        holder.setText(R.id.tv_mineMessage_issueTime,"2小时前" );
//        holder.setText(R.id.tv_mineMessage_response,"回复" );
    }

    @Override
    protected int getItemView() {
        return R.layout.mine_message_list;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
