package com.qunxianghui.gxh.fragments.mineFragment.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MinePostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.location.TestMode;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.locationFragment.adapter.NineGridTest2Adapter;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    public void initDatas() {
        initMyPostTieZi();

    }

    private void initMyPostTieZi() {


        OkGo.<String>get(Constant.LOCATION_NEWS_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseMyPostNewsData(response.body());

            }
        });
    }

    private void parseMyPostNewsData(String body) {


        Logger.i("Location" + body.toString());
        final TestMode locationListBean = GsonUtils.jsonFromJson(body, TestMode.class);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void onLoadData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
