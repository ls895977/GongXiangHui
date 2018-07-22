package com.qunxianghui.gxh.fragments.generalizeFragment;


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
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.fragments.generalizeFragment.fragments.GeneraCompanyFragment;
import com.qunxianghui.gxh.fragments.generalizeFragment.fragments.GeneraPersonalFragment;
import com.qunxianghui.gxh.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment implements View.OnClickListener {
    private static GeneralizeFragment generalizeFragment;
    @BindView(R.id.ll_genera)
    View mLlGenera;
    @BindView(R.id.rl_person)
    View mRlPerson;
    @BindView(R.id.rg_generalize_main)
    RadioGroup rgGeneralizeMain;
    @BindView(R.id.vp_generalize_main)
    NoScrollViewPager vpGeneralizeMain;
    @BindView(R.id.rb_genera_personal)
    RadioButton rbGeneraPersonal;
    @BindView(R.id.rb_genera_company)
    RadioButton rbGeneraCompany;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalizes;
    }
    @Override
    public void initDatas() {
        if (LoginMsgHelper.isLogin(getContext())) {
            if (SPUtils.getBoolean(getActivity(), SpConstant.IS_COMPANY, false)) {
                mLlGenera.setVisibility(View.VISIBLE);
                vpGeneralizeMain.setVisibility(View.VISIBLE);
                mRlPerson.setVisibility(View.GONE);
                final List<Fragment> fragments = new ArrayList<>();
                fragments.add(new GeneraPersonalFragment());
                fragments.add(new GeneraCompanyFragment());

                MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
                vpGeneralizeMain.setAdapter(adapter);
                /** 禁止滑动*/
                vpGeneralizeMain.setScroll(true);
                /**增加缓存页面的数量*/
                vpGeneralizeMain.setOffscreenPageLimit(fragments.size()-1);
                /**默认显示第一个选项卡*/
                rgGeneralizeMain.check(R.id.rb_genera_personal);
            } else {
                mLlGenera.setVisibility(View.GONE);
                vpGeneralizeMain.setVisibility(View.GONE);
                mRlPerson.setVisibility(View.VISIBLE);
                getChildFragmentManager().beginTransaction().add(R.id.rl_person, new GeneraPersonalFragment()).commit();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
        }
    }

    @Override
    public void initViews(View view) {
    }

    @Override
    protected void initListeners() {
        rgGeneralizeMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_genera_personal:
                        vpGeneralizeMain.setCurrentItem(0, false);
                        break;
                    case R.id.rb_genera_company:

                        vpGeneralizeMain.setCurrentItem(1, false);

                        break;

                }
            }
        });
        vpGeneralizeMain.addOnPageChangeListener(viewPagerListenter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    PageChangeListener viewPagerListenter = new PageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbGeneraPersonal.setChecked(true);
                    rbGeneraCompany.setChecked(false);

                    break;
                case 1:
                    rbGeneraPersonal.setChecked(false);
                    rbGeneraCompany.setChecked(true);


                    break;

            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public static GeneralizeFragment getInstance() {
        if (generalizeFragment == null) {
            generalizeFragment = new GeneralizeFragment();
        }
        return generalizeFragment;
    }
}