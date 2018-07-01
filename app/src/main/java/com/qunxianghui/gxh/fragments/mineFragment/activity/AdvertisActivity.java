package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
    ViewPager viewPager;

    private boolean mIsFromEdit;
    public static int sCurrentPosition = 0;
    private String[] titles = new String[]{"大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "贴图广告"};
    private int[] mImgs = {R.mipmap.adv_select_big_img, R.mipmap.adv_select_card, R.mipmap.adv_select_san, R.mipmap.adv_select_san, R.mipmap.adv_select_qq, R.mipmap.adv_select_video};
    private List<BaseFragment> fragments = new ArrayList<>();

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
        tvSaveAddAdverList.setOnClickListener(this);
        iv_top_savedverBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        for (int i = 0; i < titles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(titles[i]).setIcon(mImgs[i]));
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
        if (AdvertisConmmengtActivity.sCurrentPosition != 0) {
            viewPager.setCurrentItem(AdvertisConmmengtActivity.sCurrentPosition, false);
        }
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (mIsFromEdit) {
                    finish();
                }
                sCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

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
