package com.qunxianghui.gxh.item;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.ProvinceBean;

import java.util.List;
/**
 * Created by baozi on 2016/12/8.
 */
public class ProvinceItemParent extends TreeItemGroup<ProvinceBean> {

    @Override
    public List<TreeItem> initChildList(ProvinceBean data) {
        return ItemHelperFactory.createItems(data.getCitys(), this);
    }
    @Override
    protected int initLayoutId() {
        return R.layout.itme_one;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getProvinceName());
    }


}
