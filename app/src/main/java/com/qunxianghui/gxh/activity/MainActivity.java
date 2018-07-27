package com.qunxianghui.gxh.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
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

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.fragments.homeFragment.activity.UpLoadVideoActivity;
import com.qunxianghui.gxh.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CheckBoxActivity;
import com.qunxianghui.gxh.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.utils.FastBlurUtility;
import com.qunxianghui.gxh.utils.UserUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    static final String INTENT_BROADCAST_HIDE_TAB = "android.intent.action.HIDE_TAB";

    @BindView(R.id.content)
    FrameLayout mContent;
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.iv_issue)
    ImageView mIvIssue;
    @BindView(R.id.tv_issue)
    TextView mTvIssue;
    @BindView(R.id.ll_issue)
    LinearLayout mLlIssue;
    @BindView(R.id.tv_generation)
    TextView mTvGeneration;
    @BindView(R.id.tv_mine)
    TextView mTvMine;
    @BindView(R.id.ll_main)
    LinearLayout mLlMain;

    private long exitTime;
    private MainBroadCast receiver;
    private View mCurrentView;
    private Fragment mCurrentFragment;
    private Fragment[] mFragments = new Fragment[4];
    private FragmentManager mFragmentManager;
    private Dialog dialog;

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        mFragments[0] = new HomeFragment();
        mFragments[1] = new LocationFragment();
        mFragments[2] = new GeneralizeFragment();
        mFragments[3] = new MineFragment();
        mCurrentFragment = mFragments[0];
        fragmentTransaction.add(R.id.content, mCurrentFragment).commitAllowingStateLoss();
        mCurrentView = mTvHome;
        mTvHome.setSelected(true);
    }

    @Override
    protected void initDatas() {
        receiver = new MainBroadCast() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equalsIgnoreCase(INTENT_BROADCAST_HIDE_TAB)) {
                    boolean hide = intent.getBooleanExtra("hide", false);
                    if (hide) {
                        mLlMain.setVisibility(View.GONE);
                    } else {
                        mLlMain.setVisibility(View.VISIBLE);
                    }
                }

                try {
                    unregisterReceiver(receiver);
                } catch (Exception e) {
                    if (e.getMessage().contains("Receiver not registered")) {

                    } else {
                        throw e;
                    }

                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(INTENT_BROADCAST_HIDE_TAB);
        registerReceiver(receiver, filter);
        UserUtil.getInstance();
    }

    @OnClick({R.id.tv_home, R.id.tv_location, R.id.ll_issue, R.id.tv_generation, R.id.tv_mine})
    public void onViewClicked(View view) {
        if (mCurrentView == view) return;
        mCurrentView = view;
        mTvHome.setSelected(false);
        mTvLocation.setSelected(false);
        mTvIssue.setSelected(false);
        mTvGeneration.setSelected(false);
        mTvMine.setSelected(false);
        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        Fragment fragment = null;
        switch (view.getId()) {
            case R.id.tv_home:
                mTvHome.setSelected(true);
                fragment = mFragments[0];
                break;
            case R.id.tv_location:
                mTvLocation.setSelected(true);
                fragment = mFragments[1];
                break;
            case R.id.ll_issue:
                showOneKeyIssuePop();
                mTvIssue.setSelected(true);
                break;
            case R.id.tv_generation:
                mTvGeneration.setSelected(true);
                fragment = mFragments[2];
                break;
            case R.id.tv_mine:
                mTvMine.setSelected(true);
                fragment = mFragments[3];
                break;
        }
        if (fragment != null && fragment.isAdded()) {
            fragmentTransaction.show(fragment).hide(mCurrentFragment);
            mCurrentFragment = fragment;
        } else if (fragment != null) {
            fragmentTransaction.add(R.id.content, fragment).hide(mCurrentFragment);
            mCurrentFragment = fragment;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    Intent intent = new Intent(mContext, UpLoadVideoActivity.class);
                    intent.putParcelableArrayListExtra("videodata", (ArrayList<? extends Parcelable>) selectList);
                    startActivity(intent);
                    break;
            }
        }
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

    /*弹出一键发布的pop*/
    private void showOneKeyIssuePop() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.pop_onekey_issue, null);
        RelativeLayout rl_iv_onekey_issue_video = view.findViewById(R.id.rl_iv_onekey_issue_video);
        RelativeLayout rl_onekey_issue_localcircle = view.findViewById(R.id.rl_onekey_issue_localcircle);
        RelativeLayout rl_onekey_issue_baoliao = view.findViewById(R.id.rl_onekey_issue_baoliao);
        RelativeLayout rl_onekey_issue_localservice = view.findViewById(R.id.rl_onekey_issue_localservice);
        RelativeLayout rl_onekey_issue_choiceness = view.findViewById(R.id.rl_onekey_issue_choiceness);
        ImageView iv_onekey_issue_close = view.findViewById(R.id.iv_onekey_issue_close);
        RelativeLayout pop_ll = view.findViewById(R.id.pop_ll);
        LinearLayout pop_block = view.findViewById(R.id.pop_ll_block);
        pop_ll.setBackground(new BitmapDrawable(FastBlurUtility.getBlurBackgroundDrawer(this)));
        // 设置style 控制默认dialog带来的边距问题
        dialog = new Dialog(mContext, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_iv_onekey_issue_video:
                        FitchVideo();
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
////                        toActivity(GuidActivity.class);
//                        toActivity(GuidSlideActivity.class);

                        toActivity(CheckBoxActivity.class);
                        break;
                    case R.id.iv_onekey_issue_close:
                        dialog.dismiss();
                        break;
                    case R.id.pop_ll_block:
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
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
        pop_block.setOnClickListener(listener);
        //获取当前activity所在的窗体
        final Window dialogWindow = dialog.getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
//        lp.alpha = 0.9f;
        lp.width = (int) display.getWidth();  //设置宽度
        lp.height = WindowManager.LayoutParams.FILL_PARENT;//设置dialog高度
        lp.y = 3;  //设置dialog距离底部的距离
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    /*获取系统的视频和录像*/
    private void FitchVideo() {
        PictureSelector.create(MainActivity.this)
                .openGallery(PictureMimeType.ofVideo())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }
}

