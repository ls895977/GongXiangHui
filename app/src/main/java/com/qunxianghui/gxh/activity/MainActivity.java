package com.qunxianghui.gxh.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MainViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.issureFragment.IssureFragment;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.utils.UserUtil;
import com.qunxianghui.gxh.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity{
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

    private IntentFilter intentFilter;
    private MainBroadCast receiver;
    static final String INTENT_BROADCAST_HIDE_TAB = "android.intent.action.HIDE_TAB";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        //initDatas();
        initViewPagers();
    }

    private void initViewPagers() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.getInstance()); //首尔
        fragments.add(LocationFragment.getInstance()); //本地圈
        fragments.add(IssureFragment.getInstance()); //发布fragment
        fragments.add(GeneralizeFragment.getInstance()); //推广页面
        fragments.add(MineFragment.getInstance()); //我的界面
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
//                        toActivity(PublishActivity.class);  之前的逻辑  现在换一下
                        vpMain.setCurrentItem(2, false);
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

        receiver = new MainBroadCast(){
            @Override
            public void onReceive(Context context, Intent intent) {
                //Toast.makeText(this,"Broad",Toast.LENGTH_LONG).show();
                //super.onReceive(context, intent);
                if(intent.getAction().equalsIgnoreCase(INTENT_BROADCAST_HIDE_TAB)){
                    boolean hide = intent.getBooleanExtra("hide",false);
                    if(hide == true){
                        rgMain.setVisibility(View.GONE);
                    }else {
                        rgMain.setVisibility(View.VISIBLE);
                    }
                }
            }
        };

        IntentFilter filter=new IntentFilter();
        filter.addAction(INTENT_BROADCAST_HIDE_TAB);
        registerReceiver(receiver, filter);

        UserUtil.getInstance();
    }

    /**
     * 二次点击返回
     * @param keyCode
     * @param event
     * @return
     */
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



//    @Override
//    public void onReceive(Context context, Intent intent) {
//
//    }
}
