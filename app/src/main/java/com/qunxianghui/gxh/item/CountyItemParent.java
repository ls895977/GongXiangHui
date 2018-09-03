package com.qunxianghui.gxh.item;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.ProvinceBean;

import java.util.List;

/**
 */
public class CountyItemParent extends TreeItemGroup<ProvinceBean.CityBean> {

    @Override
    public List<TreeItem> initChildList(ProvinceBean.CityBean data) {
        return ItemHelperFactory.createItems(data.areas,  this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.item_two;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, getData().cityName);
        holder.setImageResource(R.id.iv_twoStep_rightIcon, isExpand() ? R.mipmap.common_gray_s_row_down : R.mipmap.common_black_row_right);
    }
}
