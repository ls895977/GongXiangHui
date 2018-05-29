package com.qunxianghui.gxh.adapter.homeAdapter;


import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.item.MultipleItem;

import java.util.List;


public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

private  List<HomeNewListBean> newLataList;

    public MultipleItemQuickAdapter(List<MultipleItem> data, List<HomeNewListBean> newsList) {
        super(data);
        this.newLataList = newsList;

        //必须绑定type和layout的关系
        addItemType(MultipleItem.FIRST_TYPE, R.layout.item_text_text);
        addItemType(MultipleItem.SECOND_TYPE, R.layout.item_three_img);
        addItemType(MultipleItem.NORMAL_TYPE, R.layout.item_text_text);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
    int position = helper.getLayoutPosition();
        switch (helper.getItemViewType()) {
            case MultipleItem.FIRST_TYPE:
                Log.i("tag", "FIRST_TYPE===============" + helper.getLayoutPosition());
                break;
            case MultipleItem.SECOND_TYPE:
                Log.i("tag", "SECOND_TYPE===============" + helper.getLayoutPosition());
                break;
            case MultipleItem.NORMAL_TYPE:
                helper.setText(R.id.tv_homelistItem_text_content, item.getData().getTitle())
                        .setText(R.id.tv_recycler_bottom_science, item.getData().getContentfrom())
                .setText(R.id.tv_recycler_bottom_science2,item.getData().getCount())
                .setText(R.id.tv_recycler_bottom_science3,item.getData().getLongtime());
                break;
        }
    }
}
