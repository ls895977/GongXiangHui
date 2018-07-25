package com.qunxianghui.gxh.adapter.locationAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;

import java.util.List;

public class LocationGridAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<String> imageList;
    private ImageOnClickListener listener;


    public LocationGridAdapter(Context context, List<String> imageList) {

        this.context = context;
        this.imageList = imageList;

        layoutInflater = LayoutInflater.from(context);
    }

    public void setListener(ImageOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        myGridHolder holder = null;
        if (convertView == null) {
            holder = new myGridHolder();
            convertView = layoutInflater.inflate(R.layout.location_grid_item, null);
            holder.iv = convertView.findViewById(R.id.iv_location_gridimage);
            convertView.setTag(holder);
        } else {
            holder = (myGridHolder) convertView.getTag();
        }
        //holder.iv.setTag(position);


        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(v,position);
            }
        });


        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.default_img);
        options.error(R.mipmap.default_img);
        options.centerCrop();
        Glide.with(context).load(imageList.get(position)).apply(options).into(holder.iv);


        return convertView;
    }

    private static class myGridHolder {
        ImageView iv;
    }

    public interface ImageOnClickListener {

        void onClick(View v, int position);

    }


}
