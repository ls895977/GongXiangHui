package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.AdverTiseCommenFragment;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment1;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment2;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment3;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment4;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment5;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisConmmengtActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {


    @BindView(R.id.iv_top_addAdverBack)
    ImageView ivTopAddAdverBack;
    @BindView(R.id.tv_addAdver_list)
    TextView tvAddAdverList;
    @BindView(R.id.tabLayout_adver_commen)
    TabLayout tabLayout_adver_commen;
    @BindView(R.id.viewPager_adver_commen)
    ViewPager viewPager_adver_commen;
    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    private TabLayout.Tab six;


    private String[] titles = new String[]{"大图通栏", "名片广告", "通栏广告", "二维码广告", "QQ广告", "贴图广告"};
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
        for (String tab : titles) {
            tabLayout_adver_commen.addTab(tabLayout_adver_commen.newTab().setText(tab));
        }
    }


    @Override
    protected void initListeners() {
        super.initListeners();
        tvAddAdverList.setOnClickListener(this);
        ivTopAddAdverBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

       /* fragments.add(new AdverTiseCommenFragment());
        fragments.add(new AdverTiseCommenFragment());
        fragments.add(new AdverTiseCommenFragment());
        fragments.add(new AdverTiseCommenFragment());
        fragments.add(new AdverTiseCommenFragment());
        fragments.add(new AdverTiseCommenFragment());*/
        for (int i =0;i<6;i++){
            fragments.add(new AdverTiseCommenFragment(i+1));
        }

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), titles, fragments);
        viewPager_adver_commen.setOffscreenPageLimit(3);
        viewPager_adver_commen.setAdapter(adapter);
        //将TabLayout与ViewPager绑定在一起
        tabLayout_adver_commen.setupWithViewPager(viewPager_adver_commen);


        //指定tab的位置
        one = tabLayout_adver_commen.getTabAt(0);
        two = tabLayout_adver_commen.getTabAt(1);
        three = tabLayout_adver_commen.getTabAt(2);
        four = tabLayout_adver_commen.getTabAt(3);
        five = tabLayout_adver_commen.getTabAt(4);
        six = tabLayout_adver_commen.getTabAt(5);

        //设置tab的图标
        one.setIcon(R.mipmap.adv_select_big_img);
        two.setIcon(R.mipmap.adv_select_card);
        three.setIcon(R.mipmap.adv_select_san);
        four.setIcon(R.mipmap.adv_select_san);
        five.setIcon(R.mipmap.adv_select_qq);
        six.setIcon(R.mipmap.adv_select_video);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addAdver_list:

               // toActivity(AdvertisActivity.class);
                toActivityWithResult(AdvertisActivity.class,0);
                break;
            case R.id.iv_top_addAdverBack:
                finish();
                break;
        }
    }





    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager_adver_commen.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            int index = data.getIntExtra("index", 0);
            viewPager_adver_commen.setCurrentItem(index);
            fragments.get(index).initDatas();
        }
    }
}
