package com.gongxianghui.fragments.homeFragment.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.activity.NewsDetailActivity;
import com.gongxianghui.activity.ProtocolActivity;
import com.gongxianghui.adapter.MainViewPagerAdapter;
import com.gongxianghui.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.gongxianghui.adapter.homeAdapter.CarListAdapter;
import com.gongxianghui.adapter.homeAdapter.HomeSalerListAdapter;
import com.gongxianghui.adapter.homeAdapter.MarqueenMessageAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.bean.home.MainPageBean;
import com.gongxianghui.config.Constant;
import com.gongxianghui.fragments.homeFragment.activity.CarDetailActivity;
import com.gongxianghui.utils.GsonUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class OrderStatusFragment extends BaseFragment {
    @BindView(R.id.rv_saler_list)
    RecyclerView rvSalerList;
    Unbinder unbinder;
    private List<String> mDatas;

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

