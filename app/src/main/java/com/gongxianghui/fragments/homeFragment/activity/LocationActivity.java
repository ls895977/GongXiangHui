package com.gongxianghui.fragments.homeFragment.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;

import com.alibaba.fastjson.JSON;
import com.gongxianghui.R;
import com.gongxianghui.adapter.homeAdapter.TreeRecyclerAdapter;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.bean.home.CityBean;
import com.gongxianghui.item.ItemHelperFactory;
import com.gongxianghui.item.ProvinceItemParent;
import com.gongxianghui.item.TreeItem;
import com.gongxianghui.widget.TitleBuilder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/4/10 0010.
 */

public class LocationActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.recycler_home_location)
    RecyclerView recyclerHomeLocation;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_home_location;
    }

    @Override
    protected void initViews() {
        recyclerHomeLocation.setLayoutManager(new GridLayoutManager(mContext, 3));
        recyclerHomeLocation.setItemAnimator(new DefaultItemAnimator());
        recyclerHomeLocation.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
//                outRect.top = 10;
                if (view.getLayoutParams() instanceof RecyclerView.LayoutParams) {
                    GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams) view.getLayoutParams();
                    int spanIndex = layoutParams.getSpanIndex();//在一行中所在的角标 第几列
                    if (spanIndex != ((GridLayoutManager) parent.getLayoutManager()).getSpanCount() - 1) {
                        outRect.right = 5;
                    }

                }
            }
        });

//        List<CityBean> cityBean = (List<CityBean>) GsonUtil.parseJsonWithGson(mActivity.getResources().getString(R.string.location),CityBean.class );
        List<CityBean> cityBean = JSON.parseArray(getResources().getString(R.string.location), CityBean.class);
        List<TreeItem> treeItemList = ItemHelperFactory.createTreeItemList((List) cityBean, ProvinceItemParent.class, null);
        TreeRecyclerAdapter treeRecyclerAdapter = new TreeRecyclerAdapter();

        treeRecyclerAdapter.setDatas(treeItemList);
        recyclerHomeLocation.setAdapter(treeRecyclerAdapter);
    }

    @Override
    protected void initDatas() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("当前地区-西湖区");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {

    }
}
