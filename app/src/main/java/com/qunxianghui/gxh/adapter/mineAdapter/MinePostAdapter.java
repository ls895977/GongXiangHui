package com.qunxianghui.gxh.adapter.mineAdapter;

import android.content.Context;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.location.TestMode;

import java.util.List;

public class MinePostAdapter extends BaseRecycleViewAdapter<TestMode.DataBean.ListBean> {


    public MinePostAdapter(Context context, List<TestMode.DataBean.ListBean> datas) {
        super(context, datas);
    }



    @Override
    protected void convert(MyViewHolder holder, int position, TestMode.DataBean.ListBean listBean) {

        holder.setText(R.id.tv_mine_postnews_person_name,listBean.getMember_name());

    }

    @Override
    protected int getItemView() {
        return R.layout.mine_post_tiezi;
    }
}
