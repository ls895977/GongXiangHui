package com.qunxianghui.gxh.guide;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import com.qunxianghui.gxh.guide.fragment.FirstPageFragment;
import com.qunxianghui.gxh.guide.fragment.SecondPageFragment;
import com.qunxianghui.gxh.guide.fragment.ThirdPageFragment;

import java.util.ArrayList;
import java.util.List;

public class GuideViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = GuideViewPagerAdapter.class.getName();

    private List<Fragment> mFragments = new ArrayList<>();

    public GuideViewPagerAdapter(FragmentManager fm) {
        super(fm);

        mFragments.add(new FirstPageFragment());
        mFragments.add(new SecondPageFragment());
        mFragments.add(new ThirdPageFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }
}
