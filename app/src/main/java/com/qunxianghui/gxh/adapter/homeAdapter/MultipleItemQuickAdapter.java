package com.qunxianghui.gxh.adapter.homeAdapter;


import android.util.Log;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.item.MultipleItem;

import java.util.List;


public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

private  List<HomeNewListBean.DataBean> newLataList;

    public MultipleItemQuickAdapter(List<MultipleItem> data, List<HomeNewListBean.DataBean> newsList) {
        super(data);
        this.newLataList = newsList;
        //必须绑定type和layout的关系
        addItemType(MultipleItem.FIRST_TYPE, R.layout.item_text_text);
        addItemType(MultipleItem.SECOND_TYPE, R.layout.item_three_img);
        addItemType(MultipleItem.NORMAL_TYPE, R.layout.recyclerview_item_test);
    }


    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.FIRST_TYPE:
                Log.i("tag", "FIRST_TYPE===============" + helper.getLayoutPosition());
                break;
            case MultipleItem.SECOND_TYPE:
                Log.i("tag", "SECOND_TYPE===============" + helper.getLayoutPosition());
                break;
            case MultipleItem.NORMAL_TYPE:
                helper.setImageResource(R.id.iv_item_img, R.mipmap.ic_launcher)
                        .setText(R.id.tv_title, item.getData().getTitle())
                        .setText(R.id.tv_content, item.getData().getContent());
                break;
        }
    }
}
