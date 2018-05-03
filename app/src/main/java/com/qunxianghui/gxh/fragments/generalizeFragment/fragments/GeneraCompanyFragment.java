package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    NoScrollViewPager vpGeneralizeCompanyMain;
    Unbinder unbinder;

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
    }

    @Override
    public void initViews(View view) {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MonthFragment());

        fragments.add(new GeneraPushFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeCompanyMain.setAdapter(adapter);
        /** 禁止滑动*/
        vpGeneralizeCompanyMain.setScroll(false);
        /**增加缓存页面的数量*/
        vpGeneralizeCompanyMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
        rgGeneraCompanyPaihang.check(R.id.rb_genera_company_yuebang);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
