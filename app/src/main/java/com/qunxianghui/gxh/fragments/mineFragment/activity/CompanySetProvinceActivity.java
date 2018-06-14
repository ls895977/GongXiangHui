package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.ProvinceComPanySetAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.ProvinceBean;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;import butterknife.ButterKnife;


public class CompanySetProvinceActivity extends BaseActivity {
    @BindView(R.id.recycler_companyset_province)
    RecyclerView recyclerCompanysetProvince;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_companyset_province;
    }

    @Override
    protected void initViews() {
        recyclerCompanysetProvince.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    }

    @Override
    protected void initDatas() {
        requestProvinceData();

    }

    /**
     * 请求省份
     */

    private void requestProvinceData() {
        OkGo.<String>post(com.qunxianghui.gxh.config.Constant.FETCH_PROVINCE_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseProvinceData(response.body());
            }
        });


    }

    //解析省份
    private void parseProvinceData(String body) {
        final ProvinceBean provinceBean = GsonUtils.jsonFromJson(body, ProvinceBean.class);

        if (provinceBean.getCode() == 0) {
            final List<ProvinceBean.DataBean> dataList = provinceBean.getData();
            final ProvinceComPanySetAdapter provinceComPanySetAdapter = new ProvinceComPanySetAdapter(mContext, dataList);
            recyclerCompanysetProvince.setAdapter(provinceComPanySetAdapter);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
