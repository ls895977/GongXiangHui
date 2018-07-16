package com.qunxianghui.gxh.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.fragments.issureFragment.IssureFragment;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.mineFragment.MineFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.ll_mine)
    LinearLayout llMine;
    @BindView(R.id.ll_home)
    LinearLayout llHome;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.ll_issue)
    LinearLayout llIssue;
    @BindView(R.id.ll_generation)
    LinearLayout llGeneration;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.iv_home)
    ImageView ivHome;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_issue)
    ImageView ivIssue;
    @BindView(R.id.tv_issue)
    TextView tvIssue;
    @BindView(R.id.iv_generation)
    ImageView ivGeneration;
    @BindView(R.id.tv_generation)
    TextView tvGeneration;
    @BindView(R.id.iv_mine)
    ImageView ivMine;
    @BindView(R.id.tv_mine)
    TextView tvMine;
    private long exitTime;
    private MainBroadCast receiver;
    static final String INTENT_BROADCAST_HIDE_TAB = "android.intent.action.HIDE_TAB";
    private HomeFragment mHomeFragment;
    private LocationFragment mLocationFragment;
    private IssureFragment mIssureFragment;
    private MineFragment mMineFragment;
    private GeneralizeFragment mGeneralizeFragment;

    private FragmentManager supportFragmentManager;

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
        ivHome.setBackgroundResource(R.drawable.ic_home_checked);
        tvHome.setTextColor(getResources().getColor(R.color.home_text_color));

    }

    @Override
    protected void initListeners() {

    }

    @Override
    protected void initDatas() {
//        receiver = new MainBroadCast() {
//            @Override
//            public void onReceive(Context context, Intent intent) {
//                //Toast.makeText(this,"Broad",Toast.LENGTH_LONG).show();
//                //super.onReceive(context, intent);
//                if (intent.getAction().equalsIgnoreCase(INTENT_BROADCAST_HIDE_TAB)) {
//                    boolean hide = intent.getBooleanExtra("hide", false);
//                    if (hide == true) {
//                        llMain.setVisibility(View.GONE);
//                    } else {
//                        llMain.setVisibility(View.VISIBLE);
//                    }
//                }
//            }
//        };
//        IntentFilter filter = new IntentFilter();
//        filter.addAction(INTENT_BROADCAST_HIDE_TAB);
//        registerReceiver(receiver, filter);
//        UserUtil.getInstance();
    }
    private void selectedFragment(int position) {
        supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = supportFragmentManager.beginTransaction();

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
                    Bundle bundle = new Bundle();
                    bundle.putInt("tabHeight", llMain.getMeasuredHeight());
                    mLocationFragment.setArguments(bundle);
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
     * @param keyCode
     * @param event
     * @return =======
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

    @OnClick({R.id.ll_home, R.id.ll_location, R.id.ll_generation, R.id.ll_issue, R.id.ll_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_home:
                selectedFragment(0);

                ivHome.setBackgroundResource(R.drawable.ic_home_checked);
                ivLocation.setBackgroundResource(R.drawable.ic_find_normal);
                ivGeneration.setBackgroundResource(R.drawable.ic_journey_normal);
                ivMine.setBackgroundResource(R.drawable.ic_mine_normal);

                tvHome.setTextColor(getResources().getColor(R.color.home_text_color));
                tvLocation.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvIssue.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvGeneration.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvMine.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                break;
            case R.id.ll_location:
                selectedFragment(1);
                ivHome.setBackgroundResource(R.drawable.ic_home_normal);
                ivLocation.setBackgroundResource(R.drawable.ic_find_checked);
                ivGeneration.setBackgroundResource(R.drawable.ic_journey_normal);
                ivMine.setBackgroundResource(R.drawable.ic_mine_normal);
                tvHome.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvLocation.setTextColor(getResources().getColor(R.color.home_text_color));
                tvIssue.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvGeneration.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvMine.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                break;
            case R.id.ll_issue:
                toActivity(PublishActivity.class);
//                selectedFragment(2);
                ivHome.setBackgroundResource(R.drawable.ic_home_normal);
                ivLocation.setBackgroundResource(R.drawable.ic_find_normal);
                ivGeneration.setBackgroundResource(R.drawable.ic_journey_normal);
                ivMine.setBackgroundResource(R.drawable.ic_mine_normal);

                tvHome.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvLocation.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvIssue.setTextColor(getResources().getColor(R.color.home_text_color));
                tvGeneration.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvMine.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                break;
            case R.id.ll_generation:
                selectedFragment(3);
                ivHome.setBackgroundResource(R.drawable.ic_home_normal);
                ivLocation.setBackgroundResource(R.drawable.ic_find_normal);
                ivGeneration.setBackgroundResource(R.drawable.ic_journey_checked);
                ivMine.setBackgroundResource(R.drawable.ic_mine_normal);

                tvHome.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvLocation.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvIssue.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvGeneration.setTextColor(getResources().getColor(R.color.home_text_color));
                tvMine.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                break;
            case R.id.ll_mine:
                selectedFragment(4);
                ivHome.setBackgroundResource(R.drawable.ic_home_normal);
                ivLocation.setBackgroundResource(R.drawable.ic_find_normal);
                ivGeneration.setBackgroundResource(R.drawable.ic_journey_normal);
                ivMine.setBackgroundResource(R.drawable.ic_mine_checked);

                tvHome.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvLocation.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvIssue.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvGeneration.setTextColor(getResources().getColor(R.color.home_text_nomal_color));
                tvMine.setTextColor(getResources().getColor(R.color.home_text_color));
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
