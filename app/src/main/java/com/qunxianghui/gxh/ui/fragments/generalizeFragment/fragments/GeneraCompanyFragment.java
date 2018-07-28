package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneralizeCompanyStaticsBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraCompanyFragment extends BaseFragment implements View.OnClickListener {
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
    Unbinder unbinder;

    private String selfcompayname;

    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.genera_company;
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
        vpGeneralizeCompanyMain.addOnPageChangeListener(viewPagerListenter);
    }

    @Override
    public void initViews(View view) {

        /*获取企业推广的数据*/
        HoldReneraCompanyData();
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MonthFragment());
        fragments.add(new MonthFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeCompanyMain.setAdapter(adapter);
        /** 禁止滑动*/
        //        vpGeneralizeCompanyMain.setScroll(false);
        /**增加缓存页面的数量*/
        vpGeneralizeCompanyMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
        rgGeneraCompanyPaihang.check(R.id.rb_genera_company_yuebang);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences spCompany = mActivity.getSharedPreferences("conpanyname", 0);
        selfcompayname = spCompany.getString("selfcompayname", "");
    }

    private void HoldReneraCompanyData() {
        OkGo.<String>post(Constant.GENERALIZE_COMPANY_STATICS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body().toString() != null) {
                    parseGeneraLizeStaticsData(response.body());
                }

            }
        });
    }

    /*解析企业推广的数据*/
    private void parseGeneraLizeStaticsData(String body) {
        final GeneralizeCompanyStaticsBean generalizeCompanyStaticsBean = GsonUtils.jsonFromJson(body, GeneralizeCompanyStaticsBean.class);
        if (generalizeCompanyStaticsBean.getCode() == 0) {
            final GeneralizeCompanyStaticsBean.DataBean dataBean = generalizeCompanyStaticsBean.getData();
            final int staff_cnt = dataBean.getStaff_cnt();  //规模
            final String ad_prize = dataBean.getAd_prize();  //广告费
            final String view_cnt = dataBean.getView_cnt(); //文章曝光
            final int article_cnt = dataBean.getArticle_cnt(); //文章总数
            final String forward_cnt = dataBean.getForward_cnt();  //文章转发
            final String click_cnt = dataBean.getClick_cnt();   //广告点击
            final String click_rate = dataBean.getClick_rate();   //广告点击率
            final String forward_rate = dataBean.getForward_rate(); //文章转发率
            tvGeneralizeCompanyMoneyCount.setText("节省广告费：" + ad_prize + " 元" + " 规模:" + String.valueOf(staff_cnt));
            tvArticleExposureCount.setText(view_cnt);
            tvArticleCount.setText(String.valueOf(article_cnt));
            tvArticleTransmitCount.setText(forward_cnt);
            tvAdverClickCount.setText(click_cnt);
            tvAdverClickRate.setText(click_rate);
            tvArticleTransmitRate.setText(forward_rate);
            tvGeneracompanyName.setText(selfcompayname);
            SharedPreferences spCompanymessage = mActivity.getSharedPreferences("companymessage", Context.MODE_PRIVATE);
            SharedPreferences.Editor spCompanymessageEditor = spCompanymessage.edit();
            spCompanymessageEditor.putString("selfcompayname", selfcompayname);
            spCompanymessageEditor.putInt("staff_cnt", staff_cnt);
            spCompanymessageEditor.commit();
        } else if (generalizeCompanyStaticsBean.getCode() == 1000) {
            toActivity(LoginActivity.class);
        }
    }

    /**
     * ==================viewpager滑动监听=====================
     */
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

    @Override
    protected void initListeners() {
        super.initListeners();

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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
