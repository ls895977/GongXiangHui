package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverTiseCommenFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisConmmengtActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_top_addAdverBack)
    ImageView ivTopAddAdverBack;
    @BindView(R.id.tv_addAdver_list)
    TextView tvAddAdverList;
    @BindView(R.id.tabLayout_adver_commen)
    TabLayout tabLayout_adver_commen;
    @BindView(R.id.viewPager_adver_commen)
    ViewPager viewPager_adver_commen;

    public static boolean sIsFromNews = true;
    public static int sCurrentPosition = 0;

    private String[] titles = new String[]{"大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "贴图广告"};
    private int[] mImgs = {R.mipmap.adv_select_big_img, R.mipmap.adv_select_card, R.mipmap.adv_select_san, R.mipmap.adv_select_san, R.mipmap.adv_select_qq, R.mipmap.adv_select_video};
    private List<BaseFragment> fragments = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commen_advertise;
    }

    @Override
    protected void initViews() {
        //设置tabLayout的一个显示方式
        tabLayout_adver_commen.setTabMode(TabLayout.MODE_SCROLLABLE);
        //循环注入标签
        for (int i = 0; i < titles.length; i++) {
            tabLayout_adver_commen.addTab(tabLayout_adver_commen.newTab().setText(titles[i]).setIcon(mImgs[i]));
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        tvAddAdverList.setOnClickListener(this);
        ivTopAddAdverBack.setOnClickListener(this);
        viewPager_adver_commen.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                sCurrentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initDatas() {
        for (int i = 0; i < 6; i++) {
            fragments.add(new AdverTiseCommenFragment(i + 1));
        }
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager_adver_commen.setOffscreenPageLimit(3);
        viewPager_adver_commen.setAdapter(adapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout_adver_commen.setupWithViewPager(viewPager_adver_commen);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addAdver_list:
                AdvertisActivity.sCurrentPosition = sCurrentPosition;
                toActivityWithResult(AdvertisActivity.class, 0x0011);
                break;
            case R.id.iv_top_addAdverBack:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            fragments.get(sCurrentPosition).initDatas();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sIsFromNews = true;
    }
}
