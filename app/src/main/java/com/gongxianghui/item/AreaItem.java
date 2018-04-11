package com.gongxianghui.item;


import com.gongxianghui.R;
import com.gongxianghui.bean.home.CityBean;

/**
 */
public class AreaItem extends TreeItem<CityBean.CitysBean.AreasBean> {


    @Override
    public int initLayoutId() {
        return R.layout.item_three;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());


    }
}
