package com.qunxianghui.gxh.fragments.generalizeFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizePersonTopBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.generalizeFragment.fragments.GeneraCompanyFragment;
import com.qunxianghui.gxh.fragments.generalizeFragment.fragments.GeneraPersonalFragment;
import com.qunxianghui.gxh.fragments.generalizeFragment.fragments.GeneraPushFragment;
import com.qunxianghui.gxh.listener.PageChangeListener;
import com.qunxianghui.gxh.utils.GsonUtils;
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
    @BindView(R.id.rg_generalize_main)
    RadioGroup rgGeneralizeMain;
    @BindView(R.id.vp_generalize_main)
    NoScrollViewPager vpGeneralizeMain;

    @BindView(R.id.rb_genera_personal)
    RadioButton rbGeneraPersonal;

    @BindView(R.id.rb_genera_company)
    RadioButton rbGeneraCompany;

    @BindView(R.id.rb_genera_push)
    RadioButton rbGeneraPush;

    @BindView(R.id.iv_genera_back)
    ImageView ivGeneraBack;
    @BindView(R.id.tv_genera_edit)
    TextView tvGeneraEdit;

    Unbinder unbinder;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalizes;
    }

    @Override
    public void initDatas() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GeneraPersonalFragment());
        fragments.add(new GeneraCompanyFragment());
        fragments.add(new GeneraPushFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getChildFragmentManager(), fragments);
        vpGeneralizeMain.setAdapter(adapter);
        /** 禁止滑动*/
        vpGeneralizeMain.setScroll(true);
        /**增加缓存页面的数量*/
        vpGeneralizeMain.setOffscreenPageLimit(fragments.size() - 1);
        /**默认显示第一个选项卡*/
        rgGeneralizeMain.check(R.id.rb_genera_personal);


    }



    @Override
    public void initViews(View view) {

        initViewPagers();
    }

    @Override
    protected void initListeners() {


        ivGeneraBack.setOnClickListener(this);
        tvGeneraEdit.setOnClickListener(this);
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
                    case R.id.rb_genera_push:
                        vpGeneralizeMain.setCurrentItem(2, false);
                        break;

                }
            }
        });
        vpGeneralizeMain.addOnPageChangeListener(viewPagerListenter);
    }

    private void initViewPagers() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_genera_back:

                break;
            case R.id.tv_genera_edit:
                Toast.makeText(mActivity, "这里实现编辑逻辑", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * ==================viewPager滑动监听=====================
     */
    PageChangeListener viewPagerListenter = new PageChangeListener() {
        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    rbGeneraPersonal.setChecked(true);
                    rbGeneraCompany.setChecked(false);
                    rbGeneraPush.setChecked(false);

                    break;
                case 1:

                    rbGeneraPersonal.setChecked(false);
                    rbGeneraCompany.setChecked(true);
                    rbGeneraPush.setChecked(false);

                    break;
                case 2:

                    rbGeneraPersonal.setChecked(false);
                    rbGeneraCompany.setChecked(false);
                    rbGeneraPush.setChecked(true);

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
}
