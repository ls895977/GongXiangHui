package com.qunxianghui.gxh.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.issureFragment.IssureFragment;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CheckBoxActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.UserUtil;

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
    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.ll_home_main)
    LinearLayout llHomeMain;
    private long exitTime;
    private MainBroadCast receiver;
    static final String INTENT_BROADCAST_HIDE_TAB = "android.intent.action.HIDE_TAB";
    private HomeFragment mHomeFragment;
    private LocationFragment mLocationFragment;
    private IssureFragment mIssureFragment;
    private MineFragment mMineFragment;
    private GeneralizeFragment mGeneralizeFragment;
    private FragmentManager supportFragmentManager;
    private Dialog dialog;
    private View view;

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
        receiver = new MainBroadCast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase(INTENT_BROADCAST_HIDE_TAB)) {
                    boolean hide = intent.getBooleanExtra("hide", false);
                    if (hide == true) {
                        llMain.setVisibility(View.GONE);
                    } else {
                        llMain.setVisibility(View.VISIBLE);
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
                    SPUtils.saveInt(mContext, "tabHeight", llMain.getMeasuredHeight());
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
                showOneKeyIssuePop();

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


    /*弹出一键发布的pop*/
    private void showOneKeyIssuePop() {
        view = LayoutInflater.from(mContext).inflate(R.layout.pop_onekey_issue, null);
        RelativeLayout rl_iv_onekey_issue_video = view.findViewById(R.id.rl_iv_onekey_issue_video);
        RelativeLayout rl_onekey_issue_localcircle = view.findViewById(R.id.rl_onekey_issue_localcircle);
        RelativeLayout rl_onekey_issue_baoliao = view.findViewById(R.id.rl_onekey_issue_baoliao);
        RelativeLayout rl_onekey_issue_localservice = view.findViewById(R.id.rl_onekey_issue_localservice);
        RelativeLayout rl_onekey_issue_choiceness = view.findViewById(R.id.rl_onekey_issue_choiceness);
        ImageView iv_onekey_issue_close = view.findViewById(R.id.iv_onekey_issue_close);
        // 设置style 控制默认dialog带来的边距问题
        dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_iv_onekey_issue_video:
                        RequestVideoBlums();
                        dialog.dismiss();
                        break;
                    case R.id.rl_onekey_issue_localcircle:
                        toActivity(PublishActivity.class);
                        dialog.dismiss();
                        break;
                    case R.id.rl_onekey_issue_baoliao:
                        toActivity(BaoLiaoActivity.class);
                        dialog.dismiss();
                        break;
                    case R.id.rl_onekey_issue_localservice:
                        toActivity(CompanySetActivity.class);
                        dialog.dismiss();
                        break;
                    case R.id.rl_onekey_issue_choiceness:
//                        toActivity(GuidActivity.class);
                        toActivity(GuidSlideActivity.class);

//                        toActivity(CheckBoxActivity.class);
                        break;
                    case R.id.iv_onekey_issue_close:
                        dialog.dismiss();
                        break;
                }
            }
        };
        rl_iv_onekey_issue_video.setOnClickListener(listener);
        rl_onekey_issue_localcircle.setOnClickListener(listener);
        rl_onekey_issue_baoliao.setOnClickListener(listener);
        rl_iv_onekey_issue_video.setOnClickListener(listener);
        rl_onekey_issue_localservice.setOnClickListener(listener);
        rl_onekey_issue_choiceness.setOnClickListener(listener);
        iv_onekey_issue_close.setOnClickListener(listener);
        //获取当前activity所在的窗体
        final Window dialogWindow = dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
        lp.alpha = 0.9f;

        lp.width = (int) display.getWidth();  //设置宽度
        lp.y = 3;  //设置dialog距离底部的距离
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);

        //将属性设置给窗体
        dialogWindow.setAttributes(lp);

        dialog.show();
    }

    private void RequestVideoBlums() {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
