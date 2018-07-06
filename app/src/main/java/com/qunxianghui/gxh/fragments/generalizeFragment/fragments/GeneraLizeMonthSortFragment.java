package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.generaAdapter.GeneralizeSortAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@SuppressLint("ValidFragment")
public class GeneraLizeMonthSortFragment extends BaseFragment {

    private String type = "";
    private String queryType = "";
    public GeneraLizeMonthSortFragment(String type) {
        this.queryType = type;
    }

    @BindView(R.id.recycler_generalize_month_sort)
    RecyclerView recyclerGeneralizeMonthSort;
    Unbinder unbinder;

   private String url = Constant.GENERALIZE_PAIHANG_URL+"?type=";

    @Override
    protected void onLoadData() {
        OkGo.<String>post(url)
                .params("type",queryType)
                .execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseGeneralizeSortData(response.body());

            }
        });

    }

    private void parseGeneralizeSortData(String body) {
        final EmployeePaiHangBean employeePaiHangBean = GsonUtils.jsonFromJson(body, EmployeePaiHangBean.class);
        if (employeePaiHangBean.getCode()==0){
            final List<EmployeePaiHangBean.DataBean> dataList = employeePaiHangBean.getData();

            final GeneralizeSortAdapter generalizeSortAdapter = new GeneralizeSortAdapter(mActivity, dataList);
            recyclerGeneralizeMonthSort.setAdapter(generalizeSortAdapter);

        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalize_month_sort;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
        recyclerGeneralizeMonthSort.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
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
