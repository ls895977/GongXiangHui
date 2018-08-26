package com.qunxianghui.gxh.item;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
/**
 */
public class AreaItem extends TreeItem<ProvinceBean.CityBean.AreasBean> {

    public static Callback sCallback;

    @Override
    protected int initLayoutId() {
        return R.layout.item_three;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder) {
        holder.setText(R.id.tv_content, data.areaName);
    }

    @Override
    public int getSpanSize() {
        return 1;
    }

    @Override
    public void onClick(ViewHolder viewHolder) {
        if (sCallback != null) {
            sCallback.callback(data);
        }
    }

    public interface Callback{
        void callback(ProvinceBean.CityBean.AreasBean areasBean);
    }
}
