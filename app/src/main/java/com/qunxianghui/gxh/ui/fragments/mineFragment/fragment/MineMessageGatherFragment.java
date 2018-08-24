package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MinePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/14 0014.
 */

public class MineMessageGatherFragment extends BaseFragment {
    @BindView(R.id.xrecycler_mymessage_tiezi)
    XRecyclerView xrecyclerMymessageTiezi;
    Unbinder unbinder;
    private List<TestMode.DataBean.ListBean> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_minemessagegather;
    }

    @Override
    public void initData() {
        OkGo.<TestMode>get(Constant.LOCATION_NEWS_LIST_URL)
                .execute(new JsonCallback<TestMode>() {
                    @Override
                    public void onSuccess(Response<TestMode> response) {
                        parseMyPostNewsData(response.body());
                    }
                });
    }

    private void parseMyPostNewsData(TestMode locationListBean) {
        dataList = locationListBean.getData().getList();
        if (locationListBean.getCode() == 0) {
            for (int i = 0; i < dataList.size(); i++) {
                TestMode.DataBean.ListBean listBean = dataList.get(i);
                if (listBean.getClick_like().size() > 0) {
                    final MinePostAdapter minePostAdapter = new MinePostAdapter(mActivity, dataList);
                    xrecyclerMymessageTiezi.setAdapter(minePostAdapter);
                }
            }
        }
    }


    @Override
    public void initViews(View view) {
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false);
        xrecyclerMymessageTiezi.setLayoutManager(linearLayoutManager);
    }

}
