package com.qunxianghui.gxh.item;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
/**
 */
public class AreaItem extends TreeItem<ProvinceBean.CityBean.AreasBean> {
    @Override
    protected int initLayoutId() {
        return R.layout.item_three;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.getAreaName());
    }

    @Override
    public int getSpanSize() {
        return 1;
    }

}
