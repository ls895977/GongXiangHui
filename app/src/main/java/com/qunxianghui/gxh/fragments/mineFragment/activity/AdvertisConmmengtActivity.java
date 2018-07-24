package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverCommonBottomFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverCommonTopFragment;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisConmmengtActivity extends BaseActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, ViewPager.OnPageChangeListener {
    @BindView(R.id.rb_adver_bottom)
    RadioButton rbAdverBottom;
    @BindView(R.id.rb_adver_top)
    RadioButton rbAdverTop;
    @BindView(R.id.rb_adver_video_poster)
    RadioButton rbAdverVideoPoster;
    @BindView(R.id.rg_advercommon_main)
    RadioGroup rgAdverCommonMain;
    @BindView(R.id.vp_addadver_common_main)
    NoScrollViewPager vpAddadverCommonMain;
    final List<Fragment> fragments = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_advercomment;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        int adverTag = intent.getIntExtra("adverTag", 0);
        if (adverTag == 1) {
            rbAdverVideoPoster.setVisibility(View.VISIBLE);
        }


    }

    @Override
    protected void initDatas() {
        fragments.add(new AdverCommonBottomFragment());
        fragments.add(new AdverCommonTopFragment());
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpAddadverCommonMain.setAdapter(adapter);
        /*禁止滑动*/
        vpAddadverCommonMain.setScroll(false);
        /**增加缓存页面的数量*/
        vpAddadverCommonMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
        rgAdverCommonMain.check(R.id.rb_adver_bottom);

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!LoginMsgHelper.isLogin(mContext)) {
            toActivity(LoginActivity.class);
            finish();
        }
    }
    @Override
    protected void initListeners() {
        super.initListeners();
        rgAdverCommonMain.setOnCheckedChangeListener(this);
        vpAddadverCommonMain.setOnPageChangeListener(this);
    }
    @Override
    public void onClick(View v) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {

            case R.id.rb_adver_bottom:
                vpAddadverCommonMain.setCurrentItem(0, false);
                break;
            case R.id.rb_adver_top:
                vpAddadverCommonMain.setCurrentItem(1, false);
                break;

        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                rbAdverBottom.setChecked(true);
                rbAdverTop.setChecked(false);
                break;
            case 1:
                rbAdverTop.setChecked(true);
                rbAdverBottom.setChecked(false);

                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
