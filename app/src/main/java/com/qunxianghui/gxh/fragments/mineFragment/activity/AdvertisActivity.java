package com.qunxianghui.gxh.fragments.mineFragment.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyFragmentPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment1;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment2;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment3;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment4;
import com.qunxianghui.gxh.fragments.mineFragment.fragment.Fragment5;
import com.qunxianghui.gxh.utils.GlideApp;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/2 0002.
 */

public class AdvertisActivity extends BaseActivity implements View.OnClickListener, TabLayout.OnTabSelectedListener {


    @BindView(R.id.iv_top_savedverBack)
    ImageView iv_top_savedverBack;
    @BindView(R.id.tv_addAdver_savelist)
    TextView tvSaveAddAdverList;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
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
        return R.layout.activity_advertise;
    }

    @Override
    protected void initViews() {
    //设置tabLayout的一个显示方式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //循环注入标签
        for (String tab : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }

        /*
        Intent intent = getIntent();
        isComingFromColum = intent.getBooleanExtra("isComingFromColum",false);
        if( isComingFromColum == true ){
            int index = intent.getIntExtra("index",0);
            if(index == 0){
                String url = intent.getStringExtra("imgUrl");
                String link = intent.getStringExtra("link");
                int ad_id = intent.getIntExtra("ad_id",0);
            }
        }*/
    }


    @Override
    protected void initListeners() {
        super.initListeners();
        tvSaveAddAdverList.setOnClickListener(this);
        iv_top_savedverBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {

        fragments.add(new Fragment1());
        fragments.add(new Fragment2());
        fragments.add(new Fragment3());
        fragments.add(new Fragment4());
        fragments.add(new Fragment5());
        fragments.add(new Fragment5());


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



    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_addAdver_savelist:
//                ToastUtils.showLongToast(mContext,"点击");
                Log.v("hahahahah",this.toString());
                //ToastUtils.showShortToast(this, "保存");
                //Toast.makeText(this,"保存",Toast.LENGTH_LONG);
                fragments.get(viewPager.getCurrentItem()).commitData();
                break;
            case R.id.iv_top_savedverBack:
                setResult(RESULT_CANCELED);
                finish();
                break;
        }
    }




    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
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


}
