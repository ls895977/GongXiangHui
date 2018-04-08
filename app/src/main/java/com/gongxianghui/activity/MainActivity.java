package com.gongxianghui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.adapter.MainViewPagerAdapter;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.fragments.generalizeFragment.GeneralizeFragment;
import com.gongxianghui.fragments.homeFragment.HomeFragment;
import com.gongxianghui.fragments.homeFragment.activity.BaoLiaoActivity;
import com.gongxianghui.fragments.issureFragment.IssureFragment;
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
    private long exitTime;

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
        fragments.add(new IssureFragment());
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
                    case R.id.rb_fabu:
                        toActivity(BaoLiaoActivity.class);
                        break;
                    case R.id.rb_generalize:
                        vpMain.setCurrentItem(3, false);
                        break;
                    case R.id.rb_mine:
                        vpMain.setCurrentItem(4, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK&&event.getAction()==KeyEvent.ACTION_DOWN){

            if ((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            }else {
                finish();
                System.exit(0);
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }
}
