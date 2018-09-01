package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.dialog.OnekeyIssueDialog;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.VideoUploadActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.UserUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import kr.co.namee.permissiongen.PermissionGen;

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
    private OnekeyIssueDialog dialog;

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        MyApplication.mMainActivity = this;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.needPermission(MainActivity.this, 105,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }
            );
        }
    }

    @Override
    protected void initData() {
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
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction(INTENT_BROADCAST_HIDE_TAB);
        registerReceiver(receiver, filter);
        UserUtil.getInstance();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((BaseFragment) mFragments[0]).initData();
        if (mFragments[1].isAdded()) {
            ((BaseFragment) mFragments[1]).initData();
        }
        if (!LoginMsgHelper.isLogin()) {
            onViewClicked(mTvHome);
        } else {
            ((BaseFragment) mFragments[2]).initData();
            ((BaseFragment) mFragments[3]).initData();
        }
    }

    @OnClick({R.id.tv_home, R.id.tv_location, R.id.ll_issue, R.id.tv_generation, R.id.tv_mine})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.ll_issue) {
            if (!LoginMsgHelper.isLogin()) {
                toActivity(LoginActivity.class);
                return;
            }
            showOneKeyIssuePop();
        }
        if (mCurrentView == view) {
            return;
        }
        if ((view.getId() == R.id.tv_generation || view.getId() == R.id.tv_mine) && !LoginMsgHelper.isLogin()) {
            toActivity(LoginActivity.class);
            return;
        }
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
//                showOneKeyIssuePop();
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
        if (fragment != mCurrentFragment) {
            if (fragment != null && fragment.isAdded()) {
                fragmentTransaction.show(fragment).hide(mCurrentFragment);
                mCurrentFragment = fragment;
            } else if (fragment != null) {
                fragmentTransaction.add(R.id.content, fragment).hide(mCurrentFragment);
                mCurrentFragment = fragment;
            }
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
                    Intent intent = new Intent(mContext, VideoUploadActivity.class);
                    intent.putExtra("videoPath", selectList.get(0).getPath());
                    startActivityForResult(intent, 0x0011);
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
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*弹出一键发布的pop*/
    private void showOneKeyIssuePop() {
        if (dialog == null) {
            dialog = new OnekeyIssueDialog(MainActivity.this, R.style.ActionSheetDialogStyle);
        }
        dialog.blurBg().show();
    }
}

