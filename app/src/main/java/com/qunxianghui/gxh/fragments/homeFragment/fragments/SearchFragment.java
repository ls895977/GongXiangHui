package com.qunxianghui.gxh.fragments.homeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.homeAdapter.SearchFragmentAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.SearchBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/5/28  18:07
 * @desc 搜索的页面
 */
public class SearchFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener {


    private static final String TYPE = "type";
    public static final String DATA = "data";

    @BindView(R.id.recyclerview) RecyclerView mRecyclerview;
    private SearchBean mBean;

    /**
     * 子类实现此抽象方法返回View进行展示
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_search;
    }

    /**
     * 子类在此方法中实现数据的初始化
     */
    @Override
    public void initDatas() {

        String string = getArguments().getString(DATA);
        if (string != null) {
            int type = getArguments().getInt(TYPE, 0);
            goNextWorks(string, type);

        }

    }

    /** ==================请求网络===================== */
    private void goNextWorks(String trim, int type) {

        switch (type) {

            //本地咨询
            case 0:
                OkGo.<String>post(Constant.SEARCH_GET_LIST).
                        params("type", type).
                        params("keywords", trim).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                parseData(response.body());
                            }
                        });
                break;

            //全网咨询
            case 1:
                OkGo.<String>post(Constant.SEARCH_GET_LIST).
                        params("type", type).
                        params("keywords", trim).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                parseData(response.body());
                            }
                        });
                break;

            //本地圈
            case 2:
                OkGo.<String>post(Constant.SEARCH_GET_LIST).
                        params("type", type).
                        params("keywords", trim).
                        execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                parseData(response.body());
                            }
                        });
                break;

        }

    }

    //设置数据
    private void parseData(String body) {
        mBean = GsonUtil.parseJsonWithGson(body, SearchBean.class);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        SearchFragmentAdapter adapter = new SearchFragmentAdapter(mBean.getData().getList());
        mRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(this);
        adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
    }

    /**
     * 初始化控件
     */
    @Override
    public void initViews(View view) {

    }



    /**==================初始化fragment=====================*/
    public static SearchFragment newInstance(String data, int type) {
        SearchFragment fragment = new SearchFragment();

        Bundle bundle = new Bundle();
        bundle.putString(SearchFragment.DATA, data);
        bundle.putInt(SearchFragment.TYPE, type);
        fragment.setArguments(bundle);
        return fragment;

    }



    /**==================跳转到详情页面=====================*/
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        final String url = mBean.getData().getList().get(position).getUrl();
        final Intent intent = new Intent(mActivity, NewsDetailActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }
}
