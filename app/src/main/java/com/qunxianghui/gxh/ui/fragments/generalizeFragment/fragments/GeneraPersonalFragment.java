package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyGeneralizePersonAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.generalize.GeneraLizePersonTopBean;
import com.qunxianghui.gxh.bean.generalize.GeneraPersonStaticBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPersonalFragment extends BaseFragment {

    @BindView(R.id.xrecycler_genera_personal_list)
    RecyclerView mXrecyclerGeneraPersonalList;
    @BindView(R.id.ll_bg_load)
    View mLoadView;

    private MyGeneralizePersonAdapter myGeneralizePersonAdapter;
    private boolean mIsFirst = true;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_genera_personl;
    }

    @Override
    public void initData() {
        myGeneralizePersonAdapter = new MyGeneralizePersonAdapter(new ArrayList<GeneraPersonStaticBean.DataBean>());
        mXrecyclerGeneraPersonalList.setAdapter(myGeneralizePersonAdapter);
        View header = LayoutInflater.from(getContext()).inflate(R.layout.fragment_genera_personal_header, null);
        myGeneralizePersonAdapter.addHeaderView(header);
    }

    @Override
    public void onStart() {
        super.onStart();
        requestTop();
    }

    private void requestTop() {
        OkGo.<GeneraLizePersonTopBean>get(Constant.GENERALIZE_RERSON_STATIS_URL)
                .execute(new JsonCallback<GeneraLizePersonTopBean>() {
                    @Override
                    public void onSuccess(Response<GeneraLizePersonTopBean> response) {
                        parseGeneraPersonTopData(response.body());
                        if (mIsFirst) {
                            mIsFirst = false;
                            requestListInfo();
                        }
                    }

                    @Override
                    public void onError(Response<GeneraLizePersonTopBean> response) {
                        super.onError(response);
                        mLoadView.setVisibility(View.GONE);
                    }
                });
    }

    private void parseGeneraPersonTopData(GeneraLizePersonTopBean generaLizePersonTopBean) {
        if (generaLizePersonTopBean.getCode() == 0) {
            LinearLayout header = myGeneralizePersonAdapter.getHeaderLayout();
            GeneraLizePersonTopBean.DataBean data = generaLizePersonTopBean.getData();
            ((TextView) header.findViewById(R.id.tv_genera_person_exposure)).setText(data.getView_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_count)).setText(data.getClick_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_transmit)).setText(data.getShare_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_rate)).setText(data.getClick_rate());
            ((TextView) header.findViewById(R.id.tv_generalize_company_des)).setText(data.getAd_prize());
        }
    }

    private void requestListInfo() {
        OkGo.<GeneraPersonStaticBean>post(Constant.GENERALIZE_PERSON_LIST_URL)
                .execute(new JsonCallback<GeneraPersonStaticBean>() {
                    @Override
                    public void onSuccess(Response<GeneraPersonStaticBean> response) {
                        mLoadView.setVisibility(View.GONE);
                        parseGeneralizePersonData(response.body());
                    }
                });
    }

    private void parseGeneralizePersonData(GeneraPersonStaticBean generaPersonStaticBean) {
        if (generaPersonStaticBean.getCode() == 0) {
            final List<GeneraPersonStaticBean.DataBean> dataList = generaPersonStaticBean.getData();
            myGeneralizePersonAdapter.setNewData(dataList);
            myGeneralizePersonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    GeneraPersonStaticBean.DataBean dataBean = dataList.get(position);
                    int uuid = (int) dataBean.data_uuid;
                    String video_url = dataBean.video_url;
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("uuid", uuid);
                    intent.putExtra("token", SPUtils.getString(SpConstant.ACCESS_TOKEN, ""));
                    intent.putExtra("url", video_url != null ? Constant.VIDEO_DETAIL_URL : Constant.HOME_NEWS_DETAIL_URL);
                    intent.putStringArrayListExtra("images", (ArrayList<String>) dataBean.images);
                    intent.putExtra("title", dataBean.title);
                    if (TextUtils.isEmpty(video_url)) {
                        intent.putExtra("position", 0);
                        intent.putExtra("descrip", dataBean.content);
                    } else {
                        intent.putExtra("position", 4);
                        intent.putExtra("descrip", dataBean.description);
                    }
                    startActivity(intent);
                }
            });
        }
    }
}
