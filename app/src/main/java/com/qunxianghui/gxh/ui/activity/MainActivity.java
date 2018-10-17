package com.qunxianghui.gxh.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
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
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.mine.NewMessageCountBean;
import com.qunxianghui.gxh.broadcast.MainBroadCast;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.dialog.OnekeyIssueDialog;
import com.qunxianghui.gxh.ui.fragments.generalizeFragment.GeneralizeFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.HomeFragment;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.VideoUploadActivity;
import com.qunxianghui.gxh.ui.fragments.locationFragment.LocationFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.MineFragment;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.SystemUtil;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    @BindView(R.id.tv_minemessage_count)
    TextView tvMinemessageCount;

    private long exitTime;
    private MainBroadCast receiver;
    private View mCurrentView;
    private Fragment mCurrentFragment;
    private FragmentManager mFragmentManager;
    private List<Fragment> mFragments;
    private OnekeyIssueDialog dialog;
    public static int sMsgCount;

    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        MyApplication.mMainActivity = this;
        mFragments = Arrays.asList(new Fragment[]{new HomeFragment(), new LocationFragment(), new GeneralizeFragment(), new MineFragment()});
        mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();

        mCurrentFragment = mFragments.get(0);
//        hideSavedFragment();
        if (mCurrentFragment.isAdded()) {
            mFragmentTransaction.show(mCurrentFragment);
        } else {
            mFragmentTransaction.add(R.id.content, mCurrentFragment).commitNowAllowingStateLoss();
        }

        mFragmentManager.executePendingTransactions();
        mCurrentView = mTvHome;
        mTvHome.setSelected(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PermissionGen.needPermission(MainActivity.this, 105,
                    new String[]{
                            Manifest.permission.CAMERA,
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }
            );
            boolean hasLocationPermission =
                    ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
            if (hasLocationPermission) {
                OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
            }
        } else {
            OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        requestMessageCount();
    }

    /*请求我的消息数量*/
    private void requestMessageCount() {
        OkGo.<NewMessageCountBean>get(Constant.MINE_NEWMESSAGE_COUNT_URL).execute(new JsonCallback<NewMessageCountBean>() {
            @Override
            public void onSuccess(Response<NewMessageCountBean> response) {
                if (response.body().getCode() == 200) {
                    sMsgCount = response.body().getData();
                    if (sMsgCount == 0) {
                        tvMinemessageCount.setVisibility(View.GONE);
                    } else {
                        tvMinemessageCount.setVisibility(View.VISIBLE);
                        tvMinemessageCount.setText(String.valueOf(sMsgCount));
                    }
                    if (mFragments.get(3).isAdded())
                        mFragments.get(3).onResume();
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        ((BaseFragment) mFragments.get(0)).initData();
        if (mFragments.get(1).isAdded()) {
            ((BaseFragment) mFragments.get(1)).initData();
        }
        if (!LoginMsgHelper.isLogin()) {
            onViewClicked(mTvHome);
        } else {
            ((BaseFragment) mFragments.get(2)).initData();
            ((BaseFragment) mFragments.get(3)).initData();
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
                fragment = mFragments.get(0);
                break;
            case R.id.tv_location:
                mTvLocation.setSelected(true);
                fragment = mFragments.get(1);
                break;
            case R.id.ll_issue:
                fragment = mCurrentFragment;
                mTvIssue.setSelected(true);
                break;
            case R.id.tv_generation:
                mTvGeneration.setSelected(true);
                fragment = mFragments.get(2);
                break;
            case R.id.tv_mine:
                mTvMine.setSelected(true);
                fragment = mFragments.get(3);
                break;

        }
        requestMessageCount();

        if (fragment != mCurrentFragment) {
            if (fragment != null && fragment.isAdded()) {
                fragmentTransaction.show(fragment).hide(mCurrentFragment);
            } else if (fragment != null) {
                fragmentTransaction.add(R.id.content, fragment).hide(mCurrentFragment);
            }
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
            dialog = new OnekeyIssueDialog(MainActivity.this);
        }
        dialog.blurBg().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length != 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            OkGo.getInstance().getCommonHeaders().put("X-deviceId", SystemUtil.getIMEI(getApplicationContext()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

