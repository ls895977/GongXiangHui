package com.qunxianghui.gxh.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.fragments.issureFragment.IssureFragment;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.utils.UserUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
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

    private MainBroadCast receiver;
    static final String INTENT_BROADCAST_HIDE_TAB = "android.intent.action.HIDE_TAB";
    private HomeFragment mHomeFragment;
    private LocationFragment mLocationFragment;
    private IssureFragment mIssureFragment;
    private MineFragment mMineFragment;
    private GeneralizeFragment mGeneralizeFragment;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initViewPagers();
    }


    private void initViewPagers() {
        /** 默认选中第一个选项卡*/
        selectedFragment(0);
    }

    @Override
    protected void initListeners() { }

    @Override
    protected void initDatas() {
        receiver = new MainBroadCast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Toast.makeText(this,"Broad",Toast.LENGTH_LONG).show();
                //super.onReceive(context, intent);
                if (intent.getAction().equalsIgnoreCase(INTENT_BROADCAST_HIDE_TAB)) {
                    boolean hide = intent.getBooleanExtra("hide", false);
                    if (hide == true) {
                        rgMain.setVisibility(View.GONE);
                    } else {
                        rgMain.setVisibility(View.VISIBLE);
                    }
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(INTENT_BROADCAST_HIDE_TAB);
        registerReceiver(receiver, filter);
        UserUtil.getInstance();
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (position) {
            //首页
            case 0:
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content, mHomeFragment);
                } else {
                    transaction.show(mHomeFragment);
                }
                break;

            //本地圈
            case 1:
                if (mLocationFragment == null) {
                    mLocationFragment = new LocationFragment();
                    transaction.add(R.id.content, mLocationFragment);
                } else {
                    transaction.show(mLocationFragment);
                }
                break;

            //发布
            case 2:
                if (mIssureFragment == null) {
                    mIssureFragment = new IssureFragment();
                    transaction.add(R.id.content, mIssureFragment);
                } else {
                    transaction.show(mIssureFragment);
                }
                break;

            //推广页面
            case 3:
                if (mGeneralizeFragment == null) {
                    mGeneralizeFragment = new GeneralizeFragment();
                    transaction.add(R.id.content, mGeneralizeFragment);
                } else {
                    transaction.show(mGeneralizeFragment);
                }
                break;
            //我的界面
            case 4:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.content, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * ==================先全部隐藏=====================
     */
    private void hideFragment(FragmentTransaction transaction) {
        if (mHomeFragment != null)
            transaction.hide(mHomeFragment);
        if (mLocationFragment != null)
            transaction.hide(mLocationFragment);
        if (mIssureFragment != null)
            transaction.hide(mIssureFragment);
        if (mGeneralizeFragment != null)
            transaction.hide(mGeneralizeFragment);
        if (mMineFragment != null)
            transaction.hide(mMineFragment);
    }


    /**
     * 二次点击返回
     *
     *
     * @param keyCode
     * @param event
     * @return =======
     *
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {

            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;

        }
        return super.onKeyDown(keyCode, event);
    }

    @OnClick({R.id.rb_location, R.id.rb_fabu, R.id.rb_generalize, R.id.rb_mine, R.id.rb_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                selectedFragment(0);
                break;
            case R.id.rb_location:
                selectedFragment(1);
                break;
            case R.id.rb_fabu:
                //                        toActivity(PublishActivity.class);  之前的逻辑  现在换一下
                selectedFragment(2);
                break;
            case R.id.rb_generalize:
                selectedFragment(3);
                break;
            case R.id.rb_mine:
                selectedFragment(4);
                break;
        }
    }
}
