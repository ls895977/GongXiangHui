package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MineIssueDiscloseAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.mine.MyIssueDiscloseBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MyIssueDiscloseFragment extends BaseFragment {
    @BindView(R.id.recycler_mineissue_disclose)
    RecyclerView recyclerMineissueDisclose;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_issue_disclose;
    }

    @Override
    public void initDatas() {
        OkGo.<String>post(Constant.GET_ISSURE_DISCLOSS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
               ParseIssureDiscloseData(response.body());
            }
        });

    }

    private void ParseIssureDiscloseData(String body) {
        final MyIssueDiscloseBean myIssueDiscloseBean = GsonUtils.jsonFromJson(body, MyIssueDiscloseBean.class);
        if (myIssueDiscloseBean.getCode()==0){
            final List<MyIssueDiscloseBean.DataBean> dataList = myIssueDiscloseBean.getData();
            final MineIssueDiscloseAdapter mineIssueDiscloseAdapter = new MineIssueDiscloseAdapter(mActivity, dataList);
            recyclerMineissueDisclose.setAdapter(mineIssueDiscloseAdapter);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
