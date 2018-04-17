package com.qunxianghui.gxh.adapter.homeAdapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;


/**
 * Created by Administrator on 2018/4/16 0016.
 */


public class BianMinGridAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private int[] images;
    private String[] text;

    public BianMinGridAdapter(Context context, int[] images, String[] text) {
        this.context = context;
        this.images = images;
        this.text = text;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        myViewHolde holder = null;
        if (convertView == null) {
            holder = new myViewHolde();
            convertView = layoutInflater.inflate(R.layout.bianmin_service_item, null);
            holder.iv= convertView.findViewById(R.id.iv_bianmin_image);
            holder.name  = convertView.findViewById(R.id.tv_bianmin_text);
            convertView.setTag(holder);
        }else {
            holder= (myViewHolde) convertView.getTag();
        }

        holder.iv .setImageResource(images[position]);
        holder.name.setText(text[position]);

        return convertView;
    }

    private static class myViewHolde {
        TextView name;
        ImageView iv;
    }

}
