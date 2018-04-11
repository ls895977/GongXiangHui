package com.gongxianghui.item;



import com.gongxianghui.R;
import com.gongxianghui.bean.home.CityBean;

import java.util.List;

/**
 * Created by baozi on 2016/12/8.
 */
public class ProvinceItemParent extends TreeItemGroup<CityBean> {
    @Override
    public List<TreeItem> initChildList(CityBean data) {
        return ItemHelperFactory.createTreeItemList(data.getCitys(), CountyItemParent.class, this);
    }

    @Override
    public int initLayoutId() {
        return R.layout.itme_one;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());


    }

}
