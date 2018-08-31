package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.TabEntity;
import com.qunxianghui.gxh.bean.generalize.GeneralizeCompanyStaticsBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.PageChangeListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraCompanyFragment extends BaseFragment {

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
    @BindView(R.id.tv_month)
    TextView mTvMonth;
    @BindView(R.id.tv_total)
    TextView mTvTotal;

    private String[] mTabTitles = {"文章", "曝光", "点击", "转发"};
    private String[] mType = {"article_cnt", "view_cnt", "click_cnt", "forward_cnt"};
    private int[] mIconUnselectIds = {
            R.mipmap.icon_company_article_selector, R.mipmap.icon_company_exposure_selector,
            R.mipmap.icon_company_click_selector, R.mipmap.icon_company_transpond_selector};
    private int[] mIconSelectIds = {
            R.mipmap.icon_company_article_normal, R.mipmap.icon_company_exposure_normal,
            R.mipmap.icon_company_click_normal, R.mipmap.icon_company_transpond_normal};

    @Override
    public int getLayoutId() {
        return R.layout.fragment_genera_company;
    }

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor(){
        //Window window = mActivity.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }

    @Override
    public void initViews(View view) {
        ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
        for (int i = 0; i < mTabTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mIconUnselectIds[i], mIconSelectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        vpGeneralizeCompanyMain.setOffscreenPageLimit(mType.length - 1);
        mTvTotal.setSelected(true);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        mTvMonth.setText(month + "月");
        setViewpager(1, month);
    }

    private void setViewpager(int total, int month) {
        List<Fragment> fragments = new ArrayList<>();
        for (String aMType : mType) {
            fragments.add(new GeneraRankMonthSortFragment(aMType, total, month));
        }
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeCompanyMain.setAdapter(adapter);
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
    public void onStart() {
        super.onStart();
        holdReneraCompanyData();
    }

    private void holdReneraCompanyData() {
        OkGo.<GeneralizeCompanyStaticsBean>post(Constant.GENERALIZE_COMPANY_STATICS_URL)
                .execute(new JsonCallback<GeneralizeCompanyStaticsBean>() {
                    @Override
                    public void onSuccess(Response<GeneralizeCompanyStaticsBean> response) {
                        if (response.body().getCode() == 0) {
                            GeneralizeCompanyStaticsBean.DataBean dataBean = response.body().getData();
                            tvGeneralizeCompanyMoneyCount.setText(String.format("节省广告费: %s 元 规模: %s人", dataBean.ad_prize, dataBean.staff_cnt));
                            tvArticleExposureCount.setText(String.format("%s次", dataBean.view_cnt));
                            tvArticleCount.setText(String.format("%s篇", dataBean.article_cnt));
                            tvArticleTransmitCount.setText(String.format("%s次", dataBean.forward_cnt));
                            tvAdverClickCount.setText(String.format("%s次", dataBean.click_cnt));
                            tvGeneracompanyName.setText(dataBean.getCompany_name());
                            tvAdverClickRate.setText(dataBean.click_rate);
                            tvArticleTransmitRate.setText(dataBean.forward_rate);
                            SharedPreferences spCompanymessage = mActivity.getSharedPreferences("companymessage", Context.MODE_PRIVATE);
                            SharedPreferences.Editor spCompanymessageEditor = spCompanymessage.edit();
                            spCompanymessageEditor.putString("staff_cnt", dataBean.staff_cnt);
                            spCompanymessageEditor.apply();
                        }
                    }
                });
    }

    @OnClick({R.id.tv_month, R.id.tv_total})
    public void onViewClicked(View view) {
        view.setSelected(true);
        switch (view.getId()) {
            case R.id.tv_month:
                selectMonth();
                mTvTotal.setSelected(false);
                return;
            case R.id.tv_total:
                mTvMonth.setSelected(false);
                setViewpager(1, 0);
                break;
        }
        mTabLayout.setCurrentTab(0);
    }

    private void selectMonth() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(i + "月");
        }
        OptionsPickerView mChooseType = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                int month = options1 + 1;
                if (month > Calendar.getInstance().get(Calendar.MONTH) + 1) {
                    mTvMonth.setSelected(false);
                    mTvTotal.setSelected(true);
                    asyncShowToast("当前选择月份过大～～");
                    return;
                }
                mTvMonth.setText(month + "月");
                setViewpager(0, month);
                mTabLayout.setCurrentTab(0);
            }
        }).setCancelColor(Color.parseColor("#676767"))
                .setSubmitColor(Color.parseColor("#D81717"))
                .build();
        mChooseType.setNPicker(list, null, null);
        mChooseType.show();
    }

}
