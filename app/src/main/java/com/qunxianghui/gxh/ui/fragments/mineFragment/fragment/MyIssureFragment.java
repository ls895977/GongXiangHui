package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MyIssureFragment extends BaseFragment {
    @Override
    protected void onLoadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_issure;
    }

    @Override
    public void initData() {
        OkGo.<String>get(Constant.GET_DISCLOSS_INFO_URL)
                .execute(new JsonCallback<String>() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        com.orhanobut.logger.Logger.e("我的爆料+++++"+response.body().toString());
                    }
                });

    }

    @Override
    public void initViews(View view) {

    }
}
