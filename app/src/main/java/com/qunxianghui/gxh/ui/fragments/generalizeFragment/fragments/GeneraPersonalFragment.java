package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.MyGeneralizePersonAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizePersonTopBean;
import com.qunxianghui.gxh.bean.generalize.GeneraPersonStaticBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPersonalFragment extends BaseFragment {
    @BindView(R.id.tv_generalize_company_des)
    TextView tvGeneralizeCompanyDes;
    @BindView(R.id.tv_genera_person_exposure)
    TextView tvGeneraPersonExposure;
    @BindView(R.id.tv_genera_person_click_count)
    TextView tvGeneraPersonClickCount;
    @BindView(R.id.tv_genera_person_transmit)
    TextView tvGeneraPersonTransmit;
    @BindView(R.id.tv_genera_person_click_rate)
    TextView tvGeneraPersonClickRate;
    @BindView(R.id.xrecycler_genera_personal_list)
    RecyclerView xrecyclerGeneraPersonalList;
    Unbinder unbinder;
    private MyGeneralizePersonAdapter myGeneralizePersonAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.genera_personl;
    }
    @Override
    public void initDatas() {
        //显示推广头部的信息
        DisplayPersonData();
    }

    private void DisplayPersonData() {
        OkGo.<String>get(Constant.GENERALIZE_RERSON_STATIS_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                            parseGeneraPersonTopData(response.body());

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        asyncShowToast("账号登陆异常");
                    }
                });
    }

    private void parseGeneraPersonTopData(String body) {
        final GeneraLizePersonTopBean generaLizePersonTopBean = GsonUtils.jsonFromJson(body, GeneraLizePersonTopBean.class);
        if (generaLizePersonTopBean.getCode() == 0) {
            final GeneraLizePersonTopBean.DataBean data = generaLizePersonTopBean.getData();
            tvGeneraPersonExposure.setText(data.getView_cnt());
            tvGeneraPersonClickCount.setText(data.getClick_cnt());
            tvGeneraPersonTransmit.setText(data.getShare_cnt());
            tvGeneraPersonClickRate.setText(data.getClick_rate());
            tvGeneralizeCompanyDes.setText(data.getAd_prize());
        }
    }

    @Override
    public void initViews(View view) {
        xrecyclerGeneraPersonalList.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.VERTICAL,false));
    }

    @Override
    protected void initListeners() {
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
        OkGo.<String>post(Constant.GENERALIZE_PERSON_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseGeneralizePersonData(response.body());
            }
        });
    }

    private void parseGeneralizePersonData(String body) {
        final GeneraPersonStaticBean generaPersonStaticBean = GsonUtils.jsonFromJson(body, GeneraPersonStaticBean.class);
        if (generaPersonStaticBean.getCode()==0){
            final List<GeneraPersonStaticBean.DataBean> dataList = generaPersonStaticBean.getData();
            if (myGeneralizePersonAdapter==null){
                myGeneralizePersonAdapter = new MyGeneralizePersonAdapter(mActivity, dataList);
                xrecyclerGeneraPersonalList.setAdapter(myGeneralizePersonAdapter);
                myGeneralizePersonAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        final String url = dataList.get(position).getUrl();
                        final int uuid = dataList.get(position).getData_uuid();
                        final Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                        intent.putExtra("url", url);
                        intent.putExtra("uuid", uuid);
                        startActivity(intent);
                    }
                });
            }

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
