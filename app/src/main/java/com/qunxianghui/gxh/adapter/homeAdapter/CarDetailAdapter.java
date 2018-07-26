package com.qunxianghui.gxh.adapter.homeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;

/**
 * Created by Administrator on 2018/4/11 0011.
 */

public class CarDetailAdapter extends PagerAdapter {
    private Context mContext;
    private String[] mImageViews;

    public CarDetailAdapter(Context mContext, String[] mImageViews) {
        this.mContext = mContext;
        this.mImageViews = mImageViews;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ImageView view = (ImageView) View.inflate(mContext, R.layout.vp_item, null);

        RequestOptions options = new RequestOptions();
        options.placeholder(R.mipmap.ic_launcher);
        options.placeholder(R.mipmap.ic_launcher);
        Glide.with(mContext).load(mImageViews[position % mImageViews.length]).apply(options).into(view);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
