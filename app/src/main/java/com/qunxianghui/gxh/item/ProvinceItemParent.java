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
        return ItemHelperFactory.createItems(data.citys, this);
    }

    @Override
    protected int initLayoutId() {
        return R.layout.itme_one;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.provinceName);
        holder.setImageResource(R.id.iv_row, isExpand() ? R.mipmap.common_gray_s_row_down : R.mipmap.common_black_row_right);
    }

}
