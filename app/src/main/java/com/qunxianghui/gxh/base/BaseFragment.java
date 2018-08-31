package com.qunxianghui.gxh.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.bean.SigninBean;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;
import com.qunxianghui.gxh.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    protected final String TAG = getClass().getSimpleName();
    public FragmentActivity mActivity;
    Unbinder unbinder;

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;
    public boolean isPrepared = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (FragmentActivity) context;

    }

    protected void setStatusBarTextColor(){
        StatusBarColorUtil.setStatusTextColor(true,mActivity);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            setStatusBarTextColor();
        }
    }

    @SuppressLint("NewApi")
    protected void setStatusBarColor(){
        Window window =mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.default_status_color));

    }

    @Override
    public void onResume() {
        super.onResume();
        setStatusBarTextColor();
        //setStatusBarColor();
    }

    /*
     * 返回一个需要展示的View
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    /*界面初始化后即处理数据*/
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        isPrepared = true;
        initViews(view);
        initData();
        initListeners();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 可见
     */
    protected void onVisible() {
        if (isPrepared && isVisible) {
            isPrepared = false;
            onLoadData();
        }
    }

    protected void onLoadData() {
    }

    /**
     * 不可见
     */
    protected void onInvisible() {
    }

    /**
     * 需要登陆才能跳转的aty
     */
    protected void toActivity(Class<?> target, boolean needSignin) {
        toActivity(target, null, needSignin);
    }

    /**
     * 需要登陆才能跳转的aty
     */
    protected void toActivity(Class<?> target, Bundle bundle, boolean needSignin) {
        MyApplication.nextBundle = bundle;
        if (needSignin) {
            final SigninBean.DataBean.MemberBean signInfo = SPUtils.getSignInfo();
            if (signInfo == null) {
                Toast.makeText(mActivity, "请先登录", Toast.LENGTH_SHORT).show();
                toActivity(LoginActivity.class, bundle);
                MyApplication.next = target;
            } else toActivity(target, bundle);
        } else toActivity(target, bundle);
    }

    protected void toActivity(Class<?> target) {
        toActivity(target, null);
    }

    protected void toActivity(Class<?> target, Bundle bundle) {
        final Intent intent = new Intent(mActivity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void toActivityWithResult(Class<?> target, int requestCode) {
        toActivityWithResult(target, null, requestCode);
    }

    protected void toActivityWithResult(Class<?> target, Bundle bundle, int requestCode) {
        final Intent intent = new Intent(mActivity, target);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void asyncShowToast(String text) {
        ToastUtils.showShort(text);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    /*
     * 当Activity初始化之后可以在这里进行一些数据的初始化操作
     */
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }

    /**
     * 子类实现此抽象方法返回View进行展示
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
//    protected abstract void initFindViewById(View view);

    /**
     * 子类在此方法中实现数据的初始化
     */
    public void initData() {
    }

    ;

    /**
     * 初始化控件
     */
    public void initViews(View view) {
    }

    /**
     * 子类可以复写此方法初始化事件
     */
    protected void initListeners() {
    }

    protected void setBackButon(View view, int id) {
        final View back = view.findViewById(id);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public void commitData() {
    }
}
