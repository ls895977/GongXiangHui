package com.qunxianghui.gxh.ui.fragments.issureFragment;

import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class IssureFragment extends BaseFragment {
    private static IssureFragment issureFragment;

    @Override
    protected void onLoadData() {
    }


    @Override
    public void onResume() {
        super.onResume();
        if (!LoginMsgHelper.isLogin(getContext())) {
            toActivity(LoginActivity.class);
            mActivity.finish();
            return;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issure;
    }

    @Override
    public void initViews(View view) {

    }

    public static IssureFragment getInstance() {
        if(issureFragment == null){
            issureFragment = new IssureFragment();
        }
        return issureFragment;
    }

}
