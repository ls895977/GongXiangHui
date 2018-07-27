package com.qunxianghui.gxh.ui.fragments.generalizeFragment;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraCompanyFragment;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments.GeneraPersonalFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.ll_genera)
    View mLlGenera;
    @BindView(R.id.rl_person)
    View mRlPerson;
    @BindView(R.id.rg_generalize_main)
    RadioGroup rgGeneralizeMain;
    @BindView(R.id.vp_generalize_main)
    MyViewPager vpGeneralizeMain;
    @BindView(R.id.rb_genera_personal)
    RadioButton rbGeneraPersonal;
    @BindView(R.id.rb_genera_company)
    RadioButton rbGeneraCompany;

    @Override
    public int getLayoutId() { return R.layout.fragment_generalizes; }

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

}