package com.qunxianghui.gxh.adapter.homeAdapter;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.qunxianghui.gxh.db.ChannelItem;
import com.qunxianghui.gxh.ui.fragments.homeFragment.HotPointFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Description：ViewPager适配器
 * <p>
 * Created by Mjj on 2016/11/18.
 */

public class NewsFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentManager mFm;
    private List<ChannelItem> mTitleList;

    public NewsFragmentPagerAdapter(FragmentManager mFm,List<ChannelItem> titleList) {
        super(mFm);
        this.mFm = mFm;
        this.mTitleList = titleList;
        int count = mTitleList.size();
        HotPointFragment newFragment;
        Bundle bundle;
        for (int i = 0; i < count; i++) {
            newFragment = new HotPointFragment();
            bundle = new Bundle();
            bundle.putInt("channel_id", mTitleList.get(i).getId());
            bundle.putString("channel_name", mTitleList.get(i).getName());
            newFragment.setArguments(bundle);
            mFragments.add(newFragment);
        }
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public Fragment getItem(int position) {

        return mFragments.get(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.mFragments != null) {
            FragmentTransaction ft = mFm.beginTransaction();
            for (Fragment f : this.mFragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            mFm.executePendingTransactions();
        }
        this.mFragments = fragments;
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position).name;
    }

}
