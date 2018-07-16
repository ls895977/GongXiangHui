package com.qunxianghui.gxh.fragments.generalizeFragment.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizeCompanyPushBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPushFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_generapush_companyname)
    TextView tvGenerapushCompanyname;
    @BindView(R.id.tv_generalize_company_des)
    TextView tvGeneralizeCompanyDes;
    @BindView(R.id.bt_genera_push_address)
    Button btGeneraPushAddress;
    Unbinder unbinder;
    @BindView(R.id.bt_genera_push_company)
    Button btGeneraPushCompany;
    private String selfcompayname;
    private int staff_cnt;
    private SharedPreferences spCompany;

    @Override
    public int getLayoutId() {
        return R.layout.genera_push_eachother;
    }
    @Override
    public void initDatas() {
    }
    @Override
    public void initViews(View view) {
        SharedPreferences spCompany = mActivity.getSharedPreferences("conpanyname", 0);
        selfcompayname = spCompany.getString("selfcompayname", "");
        spCompany = mActivity.getSharedPreferences("companymessage", 0);
        staff_cnt = spCompany.getInt("staff_cnt", 0);
        RequestCompanyPushData();
    }

    @Override
    public void onResume() {
        super.onResume();
        tvGenerapushCompanyname.setText(selfcompayname);
        tvGeneralizeCompanyDes.setText(String.valueOf("规模:" + staff_cnt));
    }
    private void RequestCompanyPushData() {
        OkGo.<String>post(Constant.GENERALIZE_COMPANY_PUSH_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                ParseGeneraCompanyData(response.body());
            }
        });
    }
    private void ParseGeneraCompanyData(String body) {
        GeneraLizeCompanyPushBean generaLizeCompanyPushBean = GsonUtils.jsonFromJson(body, GeneraLizeCompanyPushBean.class);
        int code = generaLizeCompanyPushBean.getCode();
        GeneraLizeCompanyPushBean.DataBean dataList = generaLizeCompanyPushBean.getData();

        if (code == 0) {
            String company_name = dataList.getCompany_list().get(0).getCompany_name();

            btGeneraPushCompany.setText(company_name);
        }
    }
    @Override
    protected void onLoadData() {
    }
    @Override
    protected void initListeners() {
        super.initListeners();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
