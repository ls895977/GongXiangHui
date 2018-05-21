package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.MultipleItemQuickAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.TestRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.Model;
import com.qunxianghui.gxh.item.MultipleItem;
import com.qunxianghui.gxh.utils.GsonUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {
    private List<String> mDatas = new ArrayList<>();
    @BindView(R.id.invite_recycler_view)
    RecyclerView inviteRecyclerView;
    private List<HomeNewListBean.DataBean> data;

    private List<Model> datas01;
    private List<MultipleItem> datas02;
    private MultipleItemQuickAdapter adapter;
    private List<HomeNewListBean.DataBean> newsDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {
        inviteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        inviteRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));


    }

    @Override
    protected void initDatas() {



        OkGo.<String>get(Constant.HOME_NEWS_LIST_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                parseNewsListData(response.body());

            }
        });

        datas02 = new ArrayList<>();
        //这里我是随机给某一条目加载不同的布局
        for (int i=0;i<newsDataList.size();i++){
            if(i%3==0){
                datas02.add(new MultipleItem(MultipleItem.FIRST_TYPE,null));
            }else if (i%7==0){
                datas02.add(new MultipleItem(MultipleItem.SECOND_TYPE,null));

            }else {
                datas02.add(new MultipleItem(MultipleItem.NORMAL_TYPE,datas01.get(i)));
            }
        }


    }

    private void parseNewsListData(String body) {
        final HomeNewListBean homeNewListBean = GsonUtil.parseJsonWithGson(body, HomeNewListBean.class);
        newsDataList = homeNewListBean.getData();

        requesNewsList(newsDataList);
        //创建适配器
        adapter = new MultipleItemQuickAdapter(datas02,data);

        inviteRecyclerView.setAdapter(adapter);


    }

    private void requesNewsList(List<HomeNewListBean.DataBean> newsDataList) {
        //模拟的假数据
        datas01 = new ArrayList<>();
        Model model;
        for (int i=0;i<newsDataList.size();i++){
            model = new Model();
            model.setTitle(newsDataList.get(i).getTitle());
            model.setLongtime(newsDataList.get(i).getCtime());
            model.setCount(newsDataList.get(i).getView_cnt());
            model.setContentfrom(newsDataList.get(i).getSource());
            datas01.add(model);
        }
    }
}



