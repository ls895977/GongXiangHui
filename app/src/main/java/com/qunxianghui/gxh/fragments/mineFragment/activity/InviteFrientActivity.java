package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.RefreshRecyclerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */

public class InviteFrientActivity extends BaseActivity {


    @BindView(R.id.demo_recycler)
    RecyclerView demoRecycler;
    @BindView(R.id.demo_swiperefreshlayout)
    SwipeRefreshLayout demoSwiperefreshlayout;
    private LinearLayoutManager linearLayoutManager;
    private List<String> newDatas;
    private int lastVisibleItem;
    private RefreshRecyclerAdapter refreshRecyclerAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_invite_friend;
    }

    @Override
    protected void initViews() {
        //设置刷新时动画的颜色，可以设置4个
        demoSwiperefreshlayout.setProgressBackgroundColorSchemeResource(R.color.white);
        demoSwiperefreshlayout.setColorSchemeResources(android.R.color.background_light,
                android.R.color.holo_red_light
                , android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        demoSwiperefreshlayout.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        demoRecycler.setLayoutManager(linearLayoutManager);


    }

    @Override
    protected void initDatas() {
        /**
         * 设置适配器
         */
        refreshRecyclerAdapter = new RefreshRecyclerAdapter(mContext);


        demoSwiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                newDatas = new ArrayList<>();
                                for (int i = 0; i < 5; i++) {
                                    int index = i + 1;
                                    newDatas.add("new Item" + index);
                                }
                                refreshRecyclerAdapter.addItem(newDatas);
                                demoSwiperefreshlayout.setRefreshing(false);
                                asyncShowToast("更新了五条数据...");
                            }
                }, 3000);
            }
        });

        demoRecycler.setAdapter(refreshRecyclerAdapter);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        demoRecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE&&lastVisibleItem+1==refreshRecyclerAdapter.getItemCount()){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            List<String> newDatas=new ArrayList<>();
                            for (int i=0;i<5;i++){
                                int index=i+1;
                                newDatas.add("more item"+index);
                            }
                            refreshRecyclerAdapter.addMoreItem(newDatas);

                        }
                    },1000);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}



