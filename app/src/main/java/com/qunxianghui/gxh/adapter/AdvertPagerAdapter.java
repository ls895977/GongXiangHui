package com.qunxianghui.gxh.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by user on 2018/7/22.
 */

public class AdvertPagerAdapter extends PagerAdapter {

    private List<View> mViewList;

    public AdvertPagerAdapter(List<View> viewList) {
        this.mViewList = viewList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = mViewList.get(position);
        view.setTag(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
