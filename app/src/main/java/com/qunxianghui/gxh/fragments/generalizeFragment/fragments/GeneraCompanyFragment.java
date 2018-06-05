package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.listener.PageChangeListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraCompanyFragment extends BaseFragment {

    @BindView(R.id.view_company)
    View viewCompany;
    @BindView(R.id.rb_genera_company_yuebang)
    RadioButton rbGeneraCompanyYuebang;
    @BindView(R.id.rb_genera_company_zongbang)
    RadioButton rbGeneraCompanyZongbang;
    @BindView(R.id.rg_genera_company_paihang)
    RadioGroup rgGeneraCompanyPaihang;
    @BindView(R.id.vp_generalize_company_main)
    ViewPager vpGeneralizeCompanyMain;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.genera_company;
    }

    @Override
    public void initDatas() {
        rgGeneraCompanyPaihang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_genera_company_yuebang:
                        vpGeneralizeCompanyMain.setCurrentItem(0, false);
                        break;
                    case R.id.rb_genera_company_zongbang:
                        vpGeneralizeCompanyMain.setCurrentItem(1, false);
                        break;
                }
            }
        });


        vpGeneralizeCompanyMain.addOnPageChangeListener(viewPagerListenter);
    }

    @Override
    public void initViews(View view) {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MonthFragment());
        fragments.add(new GeneraPushFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeCompanyMain.setAdapter(adapter);
        /** 禁止滑动*/
        //        vpGeneralizeCompanyMain.setScroll(false);
        /**增加缓存页面的数量*/
        vpGeneralizeCompanyMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
        rgGeneraCompanyPaihang.check(R.id.rb_genera_company_yuebang);
    }


    /** ==================viewpager滑动监听===================== */
    PageChangeListener viewPagerListenter = new PageChangeListener() {
        @Override
        public void onPageSelected(int position) {

            if (position == 0) {
                rbGeneraCompanyYuebang.setChecked(true);
                rbGeneraCompanyZongbang.setChecked(false);
            } else {
                rbGeneraCompanyZongbang.setChecked(true);
                rbGeneraCompanyYuebang.setChecked(false);
            }
        }

    };

}
