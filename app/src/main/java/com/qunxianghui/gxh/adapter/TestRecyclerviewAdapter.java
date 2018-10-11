package com.qunxianghui.gxh.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.TestModel;

import java.util.List;

public class TestRecyclerviewAdapter extends BaseQuickAdapter<TestModel,BaseViewHolder> {
    public TestRecyclerviewAdapter(int layoutResId, @Nullable List<TestModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestModel item) {
        helper.setText(R.id.tv_title,item.getTitle())
                .setText(R.id.tv_content,item.getContent())
//                .addOnClickListener(R.id.iv_img)
                .addOnLongClickListener(R.id.iv_img)
                .addOnLongClickListener(R.id.tv_title)
                .setImageResource(R.id.iv_img,R.mipmap.logo);
        /*获取当前条目的position*/

        int position = helper.getLayoutPosition();
    }
}
