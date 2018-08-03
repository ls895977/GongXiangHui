package com.qunxianghui.gxh.adapter.mineAdapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class AdvertPagerAdapter extends PagerAdapter {

    private List<View> mViews = new ArrayList<>();

    @Override
    public int getItemPosition(Object object) {
        int index = mViews.indexOf(object);
        if (index == -1)
            return POSITION_NONE;
        else
            return index;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View v = mViews.get(position);
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public int addView(View v) {
        return addView(v, mViews.size());
    }

    public int addView(View v, int position) {
        mViews.add(position, v);
        return position;
    }

    public int removeView(ViewPager pager, View v) {
        return removeView(pager, mViews.indexOf(v));
    }

    public int removeView(ViewPager pager, int position) {
        pager.setAdapter(null);
        mViews.remove(position);
        pager.setAdapter(this);
        return position;
    }

    public View getView(int position) {
        return mViews.get(position);
    }
}
