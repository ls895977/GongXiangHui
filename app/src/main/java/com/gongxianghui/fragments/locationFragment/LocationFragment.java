package com.gongxianghui.fragments.locationFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseFragment;
import com.gongxianghui.fragments.homeFragment.activity.BaoLiaoActivity;
import com.gongxianghui.fragments.locationFragment.activity.PublishStateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/3/9 0009.
 */

public class LocationFragment extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_location_mine_fabu)
    TextView tvLocationMineFabu;
    Unbinder unbinder;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_location;
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void initViews(View view) {

    }

    @Override
    protected void initListeners() {
        tvLocationMineFabu.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_location_mine_fabu:
                toActivity(BaoLiaoActivity.class);
                break;
        }
    }
}
