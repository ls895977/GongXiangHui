package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.MyCheckboxAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/7/23.
 */

public class CheckBoxActivity extends BaseActivity {
    @BindView(R.id.recyclerview_checkbox)
    RecyclerView recyclerviewCheckbox;
    private MyCheckboxAdapter myCheckboxAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_checkbox;
    }

    @Override
    protected void initViews() {
        recyclerviewCheckbox.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));;
        myCheckboxAdapter = new MyCheckboxAdapter();
        recyclerviewCheckbox.setAdapter(myCheckboxAdapter);
    }


    public void btnAll(View view) {
        myCheckboxAdapter.All();
    }

    public void btnner(View view) {
        myCheckboxAdapter.neverall();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
