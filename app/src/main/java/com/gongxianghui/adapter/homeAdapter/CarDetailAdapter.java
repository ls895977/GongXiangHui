package com.gongxianghui.adapter.homeAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gongxianghui.R;
import com.gongxianghui.utils.GlideApp;

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
        GlideApp.with(mContext).load(mImageViews[position % mImageViews.length]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(view);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
