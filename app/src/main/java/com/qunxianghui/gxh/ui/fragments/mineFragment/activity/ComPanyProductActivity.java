package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComPanyProductActivity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.xrecycler_activity_product)
    XRecyclerView mXrecyclerActivityProduct;
    @BindView(R.id.bt_add_product)
    Button mBtAddProduct;

    private List<String> mTitle = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coreproduct;
    }

    @Override
    protected void initViews() {
        mXrecyclerActivityProduct.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
//        mTitle.add("xxxxxx");
//        AdvanceAdapter advanceAdapter = new AdvanceAdapter(mContext, mTitle);
//        mXrecyclerActivityProduct.setAdapter(advanceAdapter);
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mBtAddProduct.setOnClickListener(this);
        mXrecyclerActivityProduct.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXrecyclerActivityProduct.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXrecyclerActivityProduct.refreshComplete();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_add_product:
                toActivity(AddProductActivity.class);
                break;
        }
    }
}
