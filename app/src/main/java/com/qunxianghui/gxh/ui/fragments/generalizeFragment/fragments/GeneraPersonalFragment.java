package com.qunxianghui.gxh.ui.fragments.generalizeFragment.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import com.qunxianghui.gxh.ui.activity.NewsDetailActivity;
import com.qunxianghui.gxh.utils.StatusBarColorUtil;

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

    @SuppressLint("NewApi")
    @Override
    protected void setStatusBarColor(){
        //Window window = mActivity.getWindow();
        //window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //window.setStatusBarColor(getResources().getColor(R.color.style_status_color));
    }
    @Override
    public void initData() {
        myGeneralizePersonAdapter = new MyGeneralizePersonAdapter(new ArrayList<GeneraPersonStaticBean.DataBean>());
        mXrecyclerGeneraPersonalList.setAdapter(myGeneralizePersonAdapter);
//        GeneraLizePersonTopBean.DataBean data = generaLizePersonTopBean.getData();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.fragment_genera_personal_header, null);
//        ((TextView) header.findViewById(R.id.tv_genera_person_exposure)).setText(data.getView_cnt());
//        ((TextView) header.findViewById(R.id.tv_genera_person_click_count)).setText(data.getClick_cnt());
//        ((TextView) header.findViewById(R.id.tv_genera_person_transmit)).setText(data.getShare_cnt());
//        ((TextView) header.findViewById(R.id.tv_genera_person_click_rate)).setText(data.getClick_rate());
//        ((TextView) header.findViewById(R.id.tv_generalize_company_des)).setText(data.getAd_prize());
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
                        if (mIsFirst){
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
//            View header = LayoutInflater.from(getContext()).inflate(R.layout.fragment_genera_personal_header, null);
            ((TextView) header.findViewById(R.id.tv_genera_person_exposure)).setText(data.getView_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_count)).setText(data.getClick_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_transmit)).setText(data.getShare_cnt());
            ((TextView) header.findViewById(R.id.tv_genera_person_click_rate)).setText(data.getClick_rate());
            ((TextView) header.findViewById(R.id.tv_generalize_company_des)).setText(data.getAd_prize());
//            myGeneralizePersonAdapter.addHeaderView(header);
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
                    int uuid = dataList.get(position).data_uuid;
                    String video_url = dataList.get(position).video_url;
                    Intent intent = new Intent(mActivity, NewsDetailActivity.class);
                    intent.putExtra("uuid", uuid);
                    intent.putExtra("url", video_url != null ? Constant.VIDEO_DETAIL_URL : Constant.HOME_NEWS_DETAIL_URL);
                    intent.putExtra("descrip", dataList.get(position).content);
                    startActivity(intent);
                }
            });
        }
    }
}
