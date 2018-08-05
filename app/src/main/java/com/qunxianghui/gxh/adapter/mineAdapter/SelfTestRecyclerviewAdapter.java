package com.qunxianghui.gxh.adapter.mineAdapter;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.mine.SelfTestBran;
import com.qunxianghui.gxh.item.BaseRecyclerAdapter;
import com.qunxianghui.gxh.item.ViewHolder;

/**
 * Created by user on 2018/6/10.
 */

public class SelfTestRecyclerviewAdapter extends BaseRecyclerAdapter<SelfTestBran> {


    @Override
    public int getLayoutId(int position) {
        return R.layout.simple_1_line_text;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, SelfTestBran selfTestBran, int position) {
        holder.setText(R.id.tv_simple_1_line, "自己写的");
    }
}
