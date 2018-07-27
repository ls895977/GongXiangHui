package com.qunxianghui.gxh.ui.fragments.locationFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineTabViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.AbleNewSearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener, TabLayout.OnTabSelectedListener {
    @BindView(R.id.loaction_top_nav)
    RelativeLayout loactionTopNav;
    @BindView(R.id.local_view_pager)
    ViewPager LocalViewPager;
    @BindView(R.id.location_line_layout)
    LinearLayout locationLineLayout;
    @BindView(R.id.loactionn_fragment_relative_layout)
    LinearLayout loactionnFragmentRelativeLayout;

    @BindView(R.id.tv_localcircle_location)
    TextView tvLocalcircleLocation;
    Unbinder unbinder;
    public static final int CITY_SELECT_RESULT_FRAG = 0x0000032;
    @BindView(R.id.localcircle_tablayout)
    TabLayout localcircleTablayout;
    private String[] titles = new String[]{"实时", "搞笑", "体育", "娱乐"};
    private List<Fragment> fragments = new ArrayList<>();
    private MineTabViewPagerAdapter mineTabViewPagerAdapter;
    @Override
    public int getLayoutId() {
        mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.fragment_location;
    }

    @Override
    public void initDatas() {
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());
        fragments.add(new LocationDetailFragment());

        mineTabViewPagerAdapter = new MineTabViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        LocalViewPager.setAdapter(mineTabViewPagerAdapter);
        localcircleTablayout.setupWithViewPager(LocalViewPager);
        LocalViewPager.setOffscreenPageLimit(fragments.size());

    }

    @Override
    public void initViews(View view) {
        //设置tabLayout的一个显示方式
        localcircleTablayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //循环注入标签
        for (String tab : titles) {
            localcircleTablayout.addTab(localcircleTablayout.newTab().setText(tab));
        }
    }
    @Override
    protected void initListeners() {
        localcircleTablayout.setOnTabSelectedListener(this);
        tvLocalcircleLocation.setOnClickListener(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);

        unbinder= ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.tv_localcircle_location:
                intent = new Intent(mActivity, AbleNewSearchActivity.class);
                startActivityForResult(intent, CITY_SELECT_RESULT_FRAG);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case CITY_SELECT_RESULT_FRAG:
                if (resultCode == RESULT_OK) {
                    String city = getActivity().getSharedPreferences("location", MODE_PRIVATE).getString("currcity", "");
                    tvLocalcircleLocation.setText(city);
                }
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        LocalViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}

