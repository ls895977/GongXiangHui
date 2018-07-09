package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment1;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment2;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment3;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment4;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment5;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment6;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_top_savedverBack)
    ImageView iv_top_savedverBack;
    @BindView(R.id.tv_addAdver_savelist)
    TextView tvSaveAddAdverList;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    NoScrollViewPager viewPager;

    private boolean mIsFromEdit;
    public static int sCurrentPosition = 0;
    private String[] titles = new String[]{"大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "贴图广告"};

    private List<BaseFragment> fragments = new ArrayList<>();
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    private TabLayout.Tab six;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advertise;
    }

    @Override
    protected void initViews() {
        mIsFromEdit = getIntent().getBooleanExtra("isComingFromColum", false);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        viewPager.setScroll(false);
        tvSaveAddAdverList.setOnClickListener(this);
        iv_top_savedverBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (String tab : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        fragments.add(new Fragment6());
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager.setAdapter(adapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout.setupWithViewPager(viewPager);
        //指定tab的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);
        five = tabLayout.getTabAt(4);
        six = tabLayout.getTabAt(5);
        //设置tab的图标
        one.setIcon(R.mipmap.adv_select_big_img);
        two.setIcon(R.mipmap.adv_select_card);
        three.setIcon(R.mipmap.adv_select_san);
        four.setIcon(R.mipmap.adv_select_san);
        five.setIcon(R.mipmap.adv_select_qq);
        six.setIcon(R.mipmap.adv_select_video);

        if (AdvertisConmmengtActivity.sCurrentPosition != 0) {
            viewPager.setCurrentItem(AdvertisConmmengtActivity.sCurrentPosition, false);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (mIsFromEdit) {
                    finish();
                }
                sCurrentPosition = tab.getPosition();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addAdver_savelist:
                Log.v("植入广告点击保存", this.toString());
                fragments.get(sCurrentPosition).commitData();
                break;
            case R.id.iv_top_savedverBack:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }

}
