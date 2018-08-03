package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.bean.home.MainPageBean;

import java.util.List;


/**
 * Created by Scout
 * Created on 2017/11/12 17:28.
 */

public class CarListAdapter extends BaseRecycleViewAdapter<MainPageBean.DataBean.CarListBean> {
    public CarListAdapter(Context context, List<MainPageBean.DataBean.CarListBean> datas) {
        super(context, datas);
    }

    @Override
    protected void convert(MyViewHolder holder, int position, MainPageBean.DataBean.CarListBean bean) {
        final String carName = bean.getCarName();
        final String pics = bean.getPics();
        final double price = bean.getPrice();
        final String addr = bean.getAddr();
        final String orderCount = bean.getOrderCount();
        /*收藏暂无*/
        holder.setText(R.id.tv_car_name, carName);
        holder.setText(R.id.tv_car_location, addr);
        holder.setText(R.id.tv_car_price, "￥" + price + "/天");
        holder.setText(R.id.tv_car_detail, String.format("接单%s次 收藏0次", orderCount));
        final ImageView imageView = holder.getView(R.id.iv_car_pic);


        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        Glide.with(mContext).load(pics).apply(options).into(imageView);

        ImageView iv_type = holder.getView(R.id.iv_car_type);
        iv_type.setBackgroundResource(bean.getUseType() == 1 ? R.mipmap.car_type1 : R.mipmap.car_type_2);
    }

    @Override
    protected int getItemView() {
        return R.layout.car_list_item;
    }

}
