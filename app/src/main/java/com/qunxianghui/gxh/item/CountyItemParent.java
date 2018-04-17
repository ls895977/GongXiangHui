package com.qunxianghui.gxh.item;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.CityBean;

import java.util.List;

/**
 */
public class CountyItemParent extends TreeItemGroup<CityBean.CitysBean> {

    @Override
    public List<TreeItem> initChildList(CityBean.CitysBean data) {
        return ItemHelperFactory.createTreeItemList(data.getAreas(), AreaItem.class, this);
    }


    @Override
    public int initLayoutId() {
        return R.layout.item_two;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, getData().getCityName());




    }
//
//    @Override
//    public boolean isCanExpand() {
//        return data.getCityName().equals("朝阳区");
//    }
}
