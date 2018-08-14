package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.generaAdapter.GeneralizeSortAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class GeneraRankMonthSortFragment extends BaseFragment {

    @BindView(R.id.recycler_generalize_month_sort)
    RecyclerView recyclerGeneralizeMonthSort;

    private int mTotal;
    private int mMonth;
    private String mQueryType;

    public GeneraRankMonthSortFragment(String type, int total, int month) {
        this.mQueryType = type;
        this.mTotal = total;
        this.mMonth = month;
    }

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
        GetRequest<String> post = OkGo.get(Constant.GENERALIZE_PAIHANG_URL);
        if (mTotal == 0) post.params("month", mMonth);
        else post.params("total", mTotal);
        post
                .params("type", mQueryType)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        EmployeePaiHangBean employeePaiHangBean = GsonUtils.jsonFromJson(response.body(), EmployeePaiHangBean.class);
                        if (employeePaiHangBean.code == 200) {
                            List<EmployeePaiHangBean.EmployeePaiHang.DataBean> dataList = employeePaiHangBean.data.staff_list;
                            GeneralizeSortAdapter generalizeSortAdapter = new GeneralizeSortAdapter(mActivity, dataList);
                            recyclerGeneralizeMonthSort.setAdapter(generalizeSortAdapter);
                        }
                    }
                });
    }

}
