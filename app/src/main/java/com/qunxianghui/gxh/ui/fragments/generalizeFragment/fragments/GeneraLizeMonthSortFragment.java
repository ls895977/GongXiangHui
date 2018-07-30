package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

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

@SuppressLint("ValidFragment")
public class GeneraLizeMonthSortFragment extends BaseFragment {

    private String type = "";
    private String queryType = "";

    public GeneraLizeMonthSortFragment(String type) {
        this.queryType = type;
    }

    @BindView(R.id.recycler_generalize_month_sort)
    RecyclerView recyclerGeneralizeMonthSort;

    private String url = Constant.GENERALIZE_PAIHANG_URL + "?type=";

    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalize_month_sort;
    }

    @Override
    public void initViews(View view) {
        onVisible();
    }

    @Override
    protected void onLoadData() {
        Log.d("asdf", System.currentTimeMillis() + "");
        OkGo.<String>post(url)
                .params("type", queryType)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Log.d("asdf", System.currentTimeMillis() + "");
                        parseGeneralizeSortData(response.body());
                        Log.d("asdf", System.currentTimeMillis() + "");
                    }
                });
    }

    private void parseGeneralizeSortData(String body) {
        EmployeePaiHangBean employeePaiHangBean = GsonUtils.jsonFromJson(body, EmployeePaiHangBean.class);
        if (employeePaiHangBean.getCode() == 0) {
            List<EmployeePaiHangBean.DataBean> dataList = employeePaiHangBean.getData();
            GeneralizeSortAdapter generalizeSortAdapter = new GeneralizeSortAdapter(mActivity, dataList);
            recyclerGeneralizeMonthSort.setAdapter(generalizeSortAdapter);
        }
    }

}
