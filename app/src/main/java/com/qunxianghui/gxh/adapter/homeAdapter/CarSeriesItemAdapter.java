package com.qunxianghui.gxh.adapter.homeAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.home.CarSeriesBean;

import java.util.List;



public class CarSeriesItemAdapter extends BaseAdapter {
    private Context context;
    private List<CarSeriesBean.DataBean> objects;
    private LayoutInflater layoutInflater;

    public CarSeriesItemAdapter(Context context, List<CarSeriesBean.DataBean> objects) {
        this.context = context;
        this.objects = objects;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public CarSeriesBean.DataBean getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.car_series_item, null);
            convertView.setTag(new ViewHolder(convertView));
        }
        initializeViews((CarSeriesBean.DataBean) getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(CarSeriesBean.DataBean object, ViewHolder holder) {
        holder.tvRentcarCarseries.setText(object.getName());
    }

    public void reset(List<CarSeriesBean.DataBean> news) {
        objects.clear();
        objects.addAll(news);
        notifyDataSetChanged();
    }

    protected class ViewHolder {
        private TextView tvRentcarCarseries;

        public ViewHolder(View view) {
            tvRentcarCarseries = (TextView) view.findViewById(R.id.tv_rentcar_carseries);

        }
    }
}
