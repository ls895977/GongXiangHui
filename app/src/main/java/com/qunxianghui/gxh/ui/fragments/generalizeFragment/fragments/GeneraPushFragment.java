package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizeCompanyPushBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import butterknife.BindView;

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
    @BindView(R.id.bt_genera_push_company)
    Button btGeneraPushCompany;


    private String selfcompayname;
    private String staff_cnt;
    private SharedPreferences spCompany;

    @Override
    public int getLayoutId() {
        return R.layout.genera_push_eachother;
    }

    @Override
    public void initViews(View view) {
        SharedPreferences spCompany = mActivity.getSharedPreferences("companymessage", 0);
        selfcompayname = spCompany.getString("selfcompayname", "");
        staff_cnt = spCompany.getString("staff_cnt", "0");
        RequestCompanyPushData();
    }

    @Override
    public void onResume() {
        super.onResume();
        tvGenerapushCompanyname.setText(selfcompayname);
        tvGeneralizeCompanyDes.setText("规模:" + staff_cnt);
    }

    private void RequestCompanyPushData() {
        OkGo.<GeneraLizeCompanyPushBean>post(Constant.GENERALIZE_COMPANY_PUSH_URL)
                .execute(new JsonCallback<GeneraLizeCompanyPushBean>() {
                    @Override
                    public void onSuccess(Response<GeneraLizeCompanyPushBean> response) {
                        ParseGeneraCompanyData(response.body());
                    }
                });
    }

    private void ParseGeneraCompanyData(GeneraLizeCompanyPushBean generaLizeCompanyPushBean) {
        int code = generaLizeCompanyPushBean.getCode();
        GeneraLizeCompanyPushBean.DataBean dataList = generaLizeCompanyPushBean.getData();
        if (code == 0) {
            String company_name = dataList.getCompany_list().get(0).getCompany_name();
            btGeneraPushCompany.setText(company_name);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
