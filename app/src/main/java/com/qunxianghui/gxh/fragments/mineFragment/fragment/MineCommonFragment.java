package com.qunxianghui.gxh.fragments.mineFragment.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.NewsDetailActivity;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.HomeItemListAdapter1;
import com.qunxianghui.gxh.adapter.mineAdapter.MyCollectPostAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.HomeNewListBean;
import com.qunxianghui.gxh.bean.mine.CollectBean;
import com.qunxianghui.gxh.bean.mine.MyCollectPostBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/23 0023.
 */

public class MineCommonFragment extends BaseFragment {


    @BindView(R.id.xrecycler_mine_collect_news)
    XRecyclerView xrecycler_mine_collect_news;
    Unbinder unbinder;
    private List<CollectBean.ListBean> data;
    private MyCollectPostAdapter myCollectPostAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine_common;
    }

    @Override
    public void initDatas() {

        OkGo.<String>post(Constant.GET_COLLECT_NEWS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                 parseCollectPostData(response.body());
            }
        });

    }

    private void parseCollectPostData(String body) {
        final MyCollectPostBean myCollectPostBean = GsonUtils.jsonFromJson(body, MyCollectPostBean.class);
        if (myCollectPostBean.getCode()==0){
            final List<MyCollectPostBean.DataBean> dataList = myCollectPostBean.getData();


            myCollectPostAdapter = new MyCollectPostAdapter(mActivity, dataList);
            xrecycler_mine_collect_news.setAdapter(myCollectPostAdapter);


        }
    }


    @Override
    public void initViews(View view) {
        xrecycler_mine_collect_news.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));


    }

    @Override
    protected void initListeners() {
        super.initListeners();
        xrecycler_mine_collect_news.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                myCollectPostAdapter.notifyDataSetChanged();
                xrecycler_mine_collect_news.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrecycler_mine_collect_news.refreshComplete();
            }
        });
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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
