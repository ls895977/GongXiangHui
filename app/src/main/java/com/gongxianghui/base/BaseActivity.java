package com.gongxianghui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.gongxianghui.interfaces.PermissionListener;
import com.gongxianghui.utils.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public abstract class BaseActivity extends FragmentActivity {
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
        initListeners();
        initDatas();
        //  MyApplication.appManager.addActivity(this);

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + TAG);
    }

    /**
     * 销毁时从Activity管理栈中移除
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
        mResources = null;
//        MyApplication.appManager.finishActivity(this);  现在用不到 应该用到地图的时候用到  现在加上的话 报空指针


    }

//    /**
//     * 需要登陆才能跳转的aty
//     */
//    protected void toActivity(Class<?> target, boolean needSignin) {
//        toActivity(target, null, needSignin);
//    }
//
//    /**
//     * 需要登陆才能跳转的aty
//     */
//    protected void toActivity(Class<?> target, Bundle bundle, boolean needSignin) {
//        MyApplication.nextBundle = bundle;
//        if (needSignin) {
//            final SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo(mContext);
//            if (signInfo == null) {
//                Toast.makeText(BaseActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
//                toActivity(LoginActivity.class, bundle);
//                MyApplication.next = target;
//            } else toActivity(target, bundle);
//        } else toActivity(target, bundle);
//    }


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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(mContext, text, Toast.LENGTH_SHORT).show();

            }
        });
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
    protected abstract void initViews();

    /**
     * 初始化数据
     */
    protected abstract void initDatas();

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
