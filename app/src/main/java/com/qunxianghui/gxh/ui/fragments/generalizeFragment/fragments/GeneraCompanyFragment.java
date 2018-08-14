package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.TabEntity;
import com.qunxianghui.gxh.bean.generalize.GeneralizeCompanyStaticsBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraCompanyFragment extends BaseFragment {

    @BindView(R.id.segment_tab)
    SegmentTabLayout mSegmentTab;
    @BindView(R.id.tabLayout)
    CommonTabLayout mTabLayout;
    @BindView(R.id.vp_generalize_company_main)
    ViewPager vpGeneralizeCompanyMain;
    @BindView(R.id.tv_generalize_company_money_count)
    TextView tvGeneralizeCompanyMoneyCount;
    @BindView(R.id.tv_article_exposure_count)
    TextView tvArticleExposureCount;
    @BindView(R.id.tv_article_count)
    TextView tvArticleCount;
    @BindView(R.id.tv_article_transmit_count)
    TextView tvArticleTransmitCount;
    @BindView(R.id.tv_adver_click_count)
    TextView tvAdverClickCount;
    @BindView(R.id.tv_adver_click_rate)
    TextView tvAdverClickRate;
    @BindView(R.id.tv_article_transmit_rate)
    TextView tvArticleTransmitRate;
    @BindView(R.id.tv_generacompany_name)
    TextView tvGeneracompanyName;

    private String[] mTabTitles = {"文章", "曝光", "点击", "转发"};
    private String[] mTitles = {"7月", "总榜"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.icon_company_article_selector, R.mipmap.icon_company_exposure_selector,
            R.mipmap.icon_company_click_selector, R.mipmap.icon_company_transpond_selector};
    private int[] mIconSelectIds = {
            R.mipmap.icon_company_article_normal, R.mipmap.icon_company_exposure_normal,
            R.mipmap.icon_company_click_normal, R.mipmap.icon_company_transpond_normal};

    private String selfcompayname;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_genera_company;
    }

    @Override
    public void initData() {
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
        mSegmentTab.setTabData(mTitles);
        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mIconUnselectIds[i], mIconSelectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        /*获取企业推广的数据*/
        HoldReneraCompanyData();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GeneraRankMonthSortFragment("view_cnt"));
        fragments.add(new GeneraRankMonthSortFragment("click_cnt"));
        fragments.add(new GeneraRankMonthSortFragment("forward_cnt"));
        fragments.add(new GeneraRankMonthSortFragment("article_cnt"));
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeCompanyMain.setAdapter(adapter);
        /** 禁止滑动*/
        //        vpGeneralizeCompanyMain.setScroll(false);
        /**增加缓存页面的数量*/
        vpGeneralizeCompanyMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
//        rgGeneraCompanyPaihang.check(R.id.rb_genera_company_yuebang);
    }

    @Override
    protected void initListeners() {
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                vpGeneralizeCompanyMain.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        vpGeneralizeCompanyMain.addOnPageChangeListener(new PageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences spCompany = mActivity.getSharedPreferences("companymessage", 0);
        selfcompayname = spCompany.getString("selfcompanyname", "");
        tvGeneracompanyName.setText(selfcompayname);
    }

    private void HoldReneraCompanyData() {
        OkGo.<String>post(Constant.GENERALIZE_COMPANY_STATICS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseGeneraLizeStaticsData(response.body());
            }
        });
    }

    /*解析企业推广的数据*/
    private void parseGeneraLizeStaticsData(String body) {
        final GeneralizeCompanyStaticsBean generalizeCompanyStaticsBean = GsonUtils.jsonFromJson(body, GeneralizeCompanyStaticsBean.class);
        if (generalizeCompanyStaticsBean.getCode() == 0) {
            GeneralizeCompanyStaticsBean.DataBean dataBean = generalizeCompanyStaticsBean.getData();
            tvGeneralizeCompanyMoneyCount.setText(String.format("节省广告费: %s 元 规模: %s人", dataBean.ad_prize, dataBean.staff_cnt));
            tvArticleExposureCount.setText(String.format("%s次", dataBean.view_cnt));
            tvArticleCount.setText(String.format("%s篇", dataBean.article_cnt));
            tvArticleTransmitCount.setText(String.format("%s次", dataBean.forward_cnt));
            tvAdverClickCount.setText(String.format("%s次", dataBean.click_cnt));
            tvAdverClickRate.setText(dataBean.click_rate);
            tvArticleTransmitRate.setText(dataBean.forward_rate);
            SharedPreferences spCompanymessage = mActivity.getSharedPreferences("companymessage", Context.MODE_PRIVATE);
            SharedPreferences.Editor spCompanymessageEditor = spCompanymessage.edit();
            spCompanymessageEditor.putString("staff_cnt", dataBean.staff_cnt);
            spCompanymessageEditor.apply();
        }
    }

}
