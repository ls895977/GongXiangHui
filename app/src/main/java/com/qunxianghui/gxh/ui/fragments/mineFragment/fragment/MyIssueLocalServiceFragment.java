package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyIssueLocalServiceAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssueLocalServiceFragment extends BaseFragment {
    @BindView(R.id.xrecycler_mylocal_servive)
    XRecyclerView mXecyclerMylocalServive;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_myissue_localservice;
    }

    @Override
    public void initViews(View view) {
        mXecyclerMylocalServive.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initData() {
        RequestMyIssueLocalServicesData();
    }

    @Override
    protected void initListeners() {
        mXecyclerMylocalServive.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXecyclerMylocalServive.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXecyclerMylocalServive.refreshComplete();
            }
        });
    }

    /*请求本地服务的数据*/
    private void RequestMyIssueLocalServicesData() {
        OkGo.<String>post(Constant.MYISSURE_LOCAL_SERVICE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseLocalServiceData(response.body());

            }
        });

    }

    /*解析本地服务的数据*/
    private void parseLocalServiceData(String body) {

        MineIssueLocalServiceBean myIssueGoodSelectBean = GsonUtils.jsonFromJson(body, MineIssueLocalServiceBean.class);
        int code = myIssueGoodSelectBean.getCode();
        List<MineIssueLocalServiceBean.DataBean> localServiceData = myIssueGoodSelectBean.getData();
        if (code == 200) {
            MyIssueLocalServiceAdapter myIssueLocalServiceAdapter = new MyIssueLocalServiceAdapter(mActivity, localServiceData);
            mXecyclerMylocalServive.setAdapter(myIssueLocalServiceAdapter);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
