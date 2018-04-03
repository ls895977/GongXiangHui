package com.gongxianghui.fragments.generalizeFragment.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gongxianghui.R;
import com.gongxianghui.adapter.homeAdapter.HomeItemListAdapter;
import com.gongxianghui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/4/3 0003.
 */

public class GeneraPersonalFragment extends BaseFragment {
    private List<String> mDatas;
    @BindView(R.id.recycler_genera_personal_list)
    RecyclerView recyclerGeneraPersonalList;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.genera_personl;
    }

    @Override
    public void initDatas() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mDatas.add(i, i + 1 + "");
        }
        recyclerGeneraPersonalList.setLayoutManager(new LinearLayoutManager(mActivity));
        HomeItemListAdapter adapter = new HomeItemListAdapter(mActivity, mDatas);
        recyclerGeneraPersonalList.setAdapter(adapter);
    }

    @Override
    public void initViews(View view) {

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
