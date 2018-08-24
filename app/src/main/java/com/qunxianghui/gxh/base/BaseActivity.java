package com.qunxianghui.gxh.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.qunxianghui.gxh.interfaces.PermissionListener;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.ui.dialog.LoginDialog;
import com.qunxianghui.gxh.utils.StatusBarUtil;
import com.qunxianghui.gxh.utils.ToastUtils;
import com.umeng.analytics.MobclickAgent;

import java.util.Observable;
import java.util.Observer;

import butterknife.ButterKnife;


public abstract class BaseActivity extends AppCompatActivity implements Observer {

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
        EventManager.getInstance().addObserver(this);
        ButterKnife.bind(this);
        initViews();
        initData();
        initListeners();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
        Log.d(TAG, "onResume: " + TAG);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventManager.getInstance().deleteObserver(this);
        mContext = null;
        mResources = null;
//        MyApplication.appManager.finishActivity(this);  现在用不到 应该用到地图的时候用到  现在加上的话 报空指针
    }

    @Override
    public void update(Observable o, Object arg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LoginDialog(BaseActivity.this).show();
            }
        });
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
