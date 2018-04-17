package com.qunxianghui.gxh.fragments.homeFragment.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.MyViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.fragments.homeFragment.fragments.OrderStatusFragment;
import com.qunxianghui.gxh.widget.TitleBuilder;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * Created by Administrator on 2018/3/15 0015.
 */
public class SalerActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    //TabLayout标签
    private String[] titles = new String[]{"全部", "租房", "二手房", "招聘", "二手车"};
    private List<Fragment> fragments = new ArrayList<>();
    @BindView(R.id.saler_tab_layou)
    TabLayout salerTabLayou;
    @BindView(R.id.saler_view_pager)
    ViewPager salerViewPager;
    private MyViewPagerAdapter viewPagerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.acticity_saler;
         /*该页面的实现逻辑全部在SalerStatusFragment，此页面无需再做过多操作*/
    }

    @Override
    protected void initViews() {
        //设置TabLayout标签的显示方式
        salerTabLayou.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab : titles) {
            salerTabLayou.addTab(salerTabLayou.newTab().setText(tab));
        }
    }
    @Override
    protected void initDatas() {

        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("优惠");
        //设置TabLayout点击事件
        salerTabLayou.setOnTabSelectedListener(this);
        fragments.add(new OrderStatusFragment());
        fragments.add(new OrderStatusFragment());
        fragments.add(new OrderStatusFragment());
        fragments.add(new OrderStatusFragment());
        fragments.add(new OrderStatusFragment());

        viewPagerAdapter = new MyViewPagerAdapter(this.getSupportFragmentManager(), titles, fragments);
        salerViewPager.setAdapter(viewPagerAdapter);
        salerTabLayou.setupWithViewPager(salerViewPager);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        salerViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
