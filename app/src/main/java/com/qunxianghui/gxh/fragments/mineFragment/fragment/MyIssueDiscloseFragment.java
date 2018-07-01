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
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueDiscloseAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssueDiscloseFragment extends BaseFragment {
    @BindView(R.id.recycler_mineissue_disclose)
    XRecyclerView recyclerMineissueDisclose;
    Unbinder unbinder;
    private int count = 0;
    private boolean mIsFirst = true;
    private boolean mIsRefresh = false;
    private List<MyIssueDiscloseBean.DataBean> dataList=new ArrayList<>();
    private MineIssueDiscloseAdapter mineIssueDiscloseAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issue_disclose;
    }

    @Override
    public void initDatas() {
        RequestMyIssueDisClose();
    }

    /**
     * 请求我发布中的我的爆料
     */
    private void RequestMyIssueDisClose() {
        OkGo.<String>post(Constant.GET_ISSURE_DISCLOSS_URL)
                .params("limit", 10)
                .params("skip", count)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ParseIssureDiscloseData(response.body());
            }
        });
    }

    private void ParseIssureDiscloseData(String body) {
        final MyIssueDiscloseBean myIssueDiscloseBean = GsonUtils.jsonFromJson(body, MyIssueDiscloseBean.class);
        if (mIsRefresh){
            mIsRefresh=false;
            dataList.clear();
        }
        dataList.addAll(myIssueDiscloseBean.getData());
        count=dataList.size();
        if (myIssueDiscloseBean.getCode() == 0) {
           if (mIsFirst){
               mIsFirst=false;
               mineIssueDiscloseAdapter = new MineIssueDiscloseAdapter(mActivity, dataList);
               recyclerMineissueDisclose.setAdapter(mineIssueDiscloseAdapter);
           }
            recyclerMineissueDisclose.refreshComplete();

            mineIssueDiscloseAdapter.notifyDataSetChanged();
            mineIssueDiscloseAdapter.notifyItemRangeChanged(count,dataList.size());

        }
    }

    @Override
    public void initViews(View view) {
        recyclerMineissueDisclose.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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
    protected void initListeners() {
        super.initListeners();
        recyclerMineissueDisclose.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                count=0;
                mIsRefresh=true;
                RequestMyIssueDisClose();

            }

            @Override
            public void onLoadMore() {
                RequestMyIssueDisClose();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
