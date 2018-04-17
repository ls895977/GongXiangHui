package com.qunxianghui.gxh.fragments.homeFragment.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qunxianghui.gxh.R;


import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.homeAdapter.CarListAdapter;

import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.home.MainPageBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.fragments.homeFragment.activity.CarDetailActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class OrderStatusFragment extends BaseFragment {
    @BindView(R.id.rv_saler_list)
    XRecyclerView rvSalerList;
    Unbinder unbinder;
    private List<String> mDatas;
//    /*参数构建*/
//    private ParamsBuilder params = new ParamsBuilder();
    @Override
    public int getLayoutId() {
        return R.layout.fragment_saler_order_status;
    }

    @Override
    public void initDatas() {


//        mDatas = new ArrayList<>();
//        for (int i = 0; i < 50; i++) {
//            mDatas.add(i, i + 1 + "");
//        }
//        rvSalerList.setLayoutManager(new LinearLayoutManager(mActivity));
//        HomeSalerListAdapter adapter = new HomeSalerListAdapter(mActivity, mDatas);
//        adapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View v, int position) {
//                /**
//                 * 跳转详情界面
//                 *
//                 */
//                final Intent intent = new Intent(mActivity, NewsDetailActivity.class);
//                intent.putExtra("url", mDatas.get(position));
//                startActivity(intent);
//            }
//        });
//        rvSalerList.setAdapter(adapter);

        OkGo.<String>get(Constant.API_MAIN_PAGE)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseData(response.body());
                    }
                });
    }
    private void parseData(String body) {
        MainPageBean mainPageBean = GsonUtil.parseJsonWithGson(body, MainPageBean.class);
        MainPageBean.DataBean dataList = mainPageBean.getData();
        final List<MainPageBean.DataBean.CarListBean> carList = dataList.getCarList();

        final CarListAdapter carListAdapter = new CarListAdapter(mActivity,carList);
        carListAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //跳转消息详情页面
                final  Intent intent=new Intent(mActivity,CarDetailActivity.class);
                intent.putExtra("carid",carList.get(position).getId());
                startActivity(intent);

            }
        });
        rvSalerList.setAdapter(carListAdapter);
    }

    @Override
    public void initViews(View view) {
       rvSalerList.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initListeners() {
        rvSalerList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                rvSalerList.refreshComplete();


            }

            @Override
            public void onLoadMore() {
                rvSalerList.refreshComplete();
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

