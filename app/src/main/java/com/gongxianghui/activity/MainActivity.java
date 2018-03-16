package com.gongxianghui.activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.gongxianghui.R;
import com.gongxianghui.adapter.MainViewPagerAdapter;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.fragments.generalizeFragment.GeneralizeFragment;
import com.gongxianghui.fragments.homeFragment.HomeFragment;
import com.gongxianghui.fragments.locationFragment.LocationFragment;
import com.gongxianghui.fragments.mineFragment.MineFragment;
import com.gongxianghui.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.vp_main)
    NoScrollViewPager vpMain;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.rb_location)
    RadioButton rbLocation;
    @BindView(R.id.rb_fabu)
    RadioButton rbFabu;
    @BindView(R.id.rb_generalize)
    RadioButton rbGeneralize;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
    @Override
    protected void initViews() {
        initViewPagers();
    }

    private void initViewPagers() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new LocationFragment());
        fragments.add(new GeneralizeFragment());
        fragments.add(new MineFragment());
        final MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpMain.setAdapter(adapter);
        /**禁止滑动*/
        vpMain.setScroll(false);
        /** 增加缓存页面的数量*/
        vpMain.setOffscreenPageLimit(fragments.size() - 1);
        /** 默认选中第一个选项卡*/
        rgMain.check(R.id.rb_home);
    }

    @Override
    protected void initListeners() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        vpMain.setCurrentItem(0, false);
                        break;
                    case R.id.rb_location:
                        vpMain.setCurrentItem(1, false);
                        break;
                    case R.id.rb_generalize:
                        vpMain.setCurrentItem(2, false);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(3, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void initDatas() {

    }


}
