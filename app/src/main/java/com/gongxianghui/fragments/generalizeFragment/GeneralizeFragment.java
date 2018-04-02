package com.gongxianghui.fragments.generalizeFragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.gongxianghui.R;
import com.gongxianghui.adapter.homeAdapter.TreeRecyclerAdapter;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.bean.home.CityBean;
import com.gongxianghui.item.ItemHelperFactory;
import com.gongxianghui.item.ProvinceItemParent;
import com.gongxianghui.item.TreeItem;
import com.gongxianghui.utils.GsonUtil;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class GeneralizeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.rl_content)
    RecyclerView recyclerView;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_generalize;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 3));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
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
        recyclerView.setAdapter(treeRecyclerAdapter);
    }

    @Override
    public void onClick(View v) {

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
