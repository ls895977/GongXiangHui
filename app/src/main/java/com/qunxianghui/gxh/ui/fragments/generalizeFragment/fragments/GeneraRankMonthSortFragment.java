package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.generaAdapter.GeneralizeSortAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.EmployeePaiHangBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;
import com.qunxianghui.gxh.widget.RoundImageView;

import java.util.List;

import butterknife.BindView;

@SuppressLint("ValidFragment")
public class GeneraRankMonthSortFragment extends BaseFragment {

    @BindView(R.id.recycler_generalize_month_sort)
    RecyclerView recyclerGeneralizeMonthSort;
    @BindView(R.id.ll_bg_load)
    View mLoadView;

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

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor(){
        Window window = mActivity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }
    @Override
    protected void setStatusBarTextColor(){
        StatusBarColorUtil.setStatusTextColor(false,mActivity);
    }

    @Override
    protected void onLoadData() {
        GetRequest<EmployeePaiHangBean> post = OkGo.get(Constant.GENERALIZE_PAIHANG_URL);
        if (mTotal == 0) post.params("month", mMonth);
        else post.params("total", mTotal);
        post.params("type", mQueryType);
        post.execute(new JsonCallback<EmployeePaiHangBean>() {
            @Override
            public void onSuccess(Response<EmployeePaiHangBean> response) {
                mLoadView.setVisibility(View.GONE);
                EmployeePaiHangBean employeePaiHangBean = response.body();
                if (employeePaiHangBean.code == 200) {
                    List<EmployeePaiHangBean.EmployeePaiHang.DataBean> dataList = employeePaiHangBean.data.staff_list;
                    View header = LayoutInflater.from(getContext()).inflate(R.layout.item_generalize_sort_header, null);
                    TextView tvRank = header.findViewById(R.id.tv_rank);
                    RoundImageView img = header.findViewById(R.id.iv_generalize_person_head);
                    TextView tvName = header.findViewById(R.id.iv_generalize_person_name);
                    TextView tvCount = header.findViewById(R.id.tv_generalize_person_count);
                    Glide.with(GeneraRankMonthSortFragment.this)
                            .load(employeePaiHangBean.data.my_data.member_avatar)
                            .apply(new RequestOptions().placeholder(R.mipmap.default_img).error(R.mipmap.default_img).centerCrop())
                            .into(img);
                    tvName.setText(employeePaiHangBean.data.my_data.member_name);
                    String textSource = "<font color='#D81717'>%s</font><font color='#999999'>次</font>";
                    tvCount.setText(Html.fromHtml(String.format(textSource, employeePaiHangBean.data.my_data.cnt)));
                    if (1 == employeePaiHangBean.data.my_data.ranking) {
                        tvRank.setBackground(ContextCompat.getDrawable(mActivity, R.mipmap.genera_company_sort_one));
                    } else if (2 == employeePaiHangBean.data.my_data.ranking) {
                        tvRank.setBackground(ContextCompat.getDrawable(mActivity, R.mipmap.genera_company_sort_two));
                    } else if (3 == employeePaiHangBean.data.my_data.ranking) {
                        tvRank.setBackground(ContextCompat.getDrawable(mActivity, R.mipmap.genera_company_sort_three));
                    } else {
                        tvRank.setText(String.format("%s.", employeePaiHangBean.data.my_data.ranking));
                    }

                    GeneralizeSortAdapter generalizeSortAdapter = new GeneralizeSortAdapter(dataList);
                    generalizeSortAdapter.addHeaderView(header);
                    recyclerGeneralizeMonthSort.setAdapter(generalizeSortAdapter);
                }
            }

            @Override
            public void onError(Response<EmployeePaiHangBean> response) {
                super.onError(response);
                mLoadView.setVisibility(View.GONE);
            }
        });
    }

}
