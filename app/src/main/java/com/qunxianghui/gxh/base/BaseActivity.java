package com.qunxianghui.gxh.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.interfaces.PermissionListener;
import com.qunxianghui.gxh.observer.TokenLoseEvent;
import com.qunxianghui.gxh.ui.dialog.LoginDialog;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity {

    private static PermissionListener mlistener;
    protected String TAG = this.getClass().getSimpleName();
    protected Context mContext;
    protected Resources mResources;

    /**
     * 创建Activity时加到管理栈中
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
        setContentView(getLayoutId());
        mContext = this;
        mResources = getResources();
        ButterKnife.bind(this);
        initViews();
        initData();
        initListeners();
        setStatusBarTextColor();
        //setStatusBarColor();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    protected void setStatusBarTextColor() {
        //Log.d(TAG,"setStatusBarTextColor");
        StatusBarColorUtil.setStatusTextColor(true, this);
    }

    @SuppressLint("NewApi")
    protected void setStatusBarColor() {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.default_status_color));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
        mResources = null;
//        MyApplication.appManager.finishActivity(this);  现在用不到 应该用到地图的时候用到  现在加上的话 报空指针
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(final TokenLoseEvent tokenLoseEvent) {
        LoginDialog.getInstance(BaseActivity.this).setContent(tokenLoseEvent.mContent);
    }

    protected void toActivity(Class<?> target) {
        toActivity(target, null);
    }

    protected void toActivity(Class<?> target, Bundle bundle) {
        final Intent intent = new Intent(mContext, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void toActivityWithResult(Class<?> target, int requestCode) {
        toActivityWithResult(target, null, requestCode);
    }

    protected void toActivityWithResult(Class<?> target, Bundle bundle, int requestCode) {
        final Intent intent = new Intent(mContext, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void asyncShowToast(final String text) {
        ToastUtils.showShort(text);
    }

    /**
     * 初始化监听器
     */
    protected void initListeners() {
    }

    /**
     * 获取布局id
     **/
    protected abstract int getLayoutId();

    /**
     * 初始化view
     **/
    protected void initViews() {
    }

    /**
     * 初始化数据
     */
    protected void initData() {
    }

    protected void setStatusBar() {
        StatusBarUtil.setTransparentForImageView(this, null);
        StatusBarUtil.MIUISetStatusBarLightMode(this, true);
        StatusBarUtil.FlymeSetStatusBarLightMode(this, true);
    }

    protected void setBackButon(int id) {
        final View back = findViewById(id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
