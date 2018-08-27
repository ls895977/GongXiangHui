package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.HomeAirBean;

import java.util.List;

public class HomeAirListAdapter extends BaseRecycleViewAdapter<HomeAirBean.DataBean.ForecastBean> {

    public HomeAirListAdapter(Context context, List<HomeAirBean.DataBean.ForecastBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, HomeAirBean.DataBean.ForecastBean forecastBean) {
        String airListData = forecastBean.getDate();
        String high = forecastBean.getHigh();
        String low = forecastBean.getLow();
        String weather = forecastBean.getDay().getWeather();
        /**展示列表**/
        holder.setText(R.id.tv_airList_days, airListData);
        holder.setText(R.id.tv_airList_air_true, weather);
        holder.setText(R.id.tv_airList_degree, high + low);
    }

    @Override
    protected int getItemView() {
        return R.layout.home_airlist_item;
    }
}
