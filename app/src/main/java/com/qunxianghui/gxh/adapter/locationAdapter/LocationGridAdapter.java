package com.qunxianghui.gxh.adapter.locationAdapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.ArrayList;
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


        GlideApp.with(context).load(imageList.get(position))
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.iv);

        return convertView;
    }

    private static class myGridHolder {
        ImageView iv;
    }

    public interface ImageOnClickListener {

        void onClick(View v, int position);

    }


}
