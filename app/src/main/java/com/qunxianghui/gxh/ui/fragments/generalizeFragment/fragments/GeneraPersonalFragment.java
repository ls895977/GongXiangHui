package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyGeneralizePersonAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizePersonTopBean;
import com.qunxianghui.gxh.bean.generalize.GeneraPersonStaticBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPersonalFragment extends BaseFragment {

    @BindView(R.id.xrecycler_genera_personal_list)
    RecyclerView mXrecyclerGeneraPersonalList;

    private MyGeneralizePersonAdapter myGeneralizePersonAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_genera_personl;
    }

    @Override
    public void initData() {
        myGeneralizePersonAdapter = new MyGeneralizePersonAdapter(new ArrayList<GeneraPersonStaticBean.DataBean>());
        mXrecyclerGeneraPersonalList.setAdapter(myGeneralizePersonAdapter);
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
            GeneraLizePersonTopBean.DataBean data = generaLizePersonTopBean.getData();
            View header = LayoutInflater.from(getContext()).inflate(R.layout.fragment_genera_personal_header, null);
            ((TextView) header.findViewById(R.id.tv_genera_person_exposure)).setText(data.getView_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_count)).setText(data.getClick_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_transmit)).setText(data.getShare_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_rate)).setText(data.getClick_rate());
            ((TextView) header.findViewById(R.id.tv_generalize_company_des)).setText(data.getAd_prize());
            myGeneralizePersonAdapter.addHeaderView(header);
        }
        OkGo.<String>post(Constant.GENERALIZE_PERSON_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseGeneralizePersonData(response.body());
            }
        });
    }

    private void parseGeneralizePersonData(String body) {
        final GeneraPersonStaticBean generaPersonStaticBean = GsonUtils.jsonFromJson(body, GeneraPersonStaticBean.class);
        if (generaPersonStaticBean.getCode() == 0) {
            final List<GeneraPersonStaticBean.DataBean> dataList = generaPersonStaticBean.getData();
            myGeneralizePersonAdapter.setNewData(dataList);
            myGeneralizePersonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    String url = dataList.get(position).url;
                    int uuid = dataList.get(position).data_uuid;
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("url", url);
                    intent.putExtra("uuid", uuid);
                    startActivity(intent);
                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        onLoadData();
    }

}
