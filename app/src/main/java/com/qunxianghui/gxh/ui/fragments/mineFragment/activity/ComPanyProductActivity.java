package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.ProductAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ComPanyProductActivity extends BaseActivity {

    @BindView(R.id.xrecycler_activity_product)
    XRecyclerView mXrecyclerActivityProduct;

    private int mSkip;
    private List<AddAdvanceBean.DataBean> mDataList = new ArrayList<>();
    private ProductAdapter mProductAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coreproduct;
    }

    @Override
    protected void initViews() {
        mXrecyclerActivityProduct.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
        mProductAdapter = new ProductAdapter(mContext, mDataList);
        mXrecyclerActivityProduct.setAdapter(mProductAdapter);
        mProductAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(mContext, AddProductActivity.class);
                intent.putExtra("info", mDataList.get(position - 1));
                ComPanyProductActivity.this.startActivityForResult(intent, 0x0011);
            }
        });
    }

    @Override
    protected void initData() {
        OkGo.<AddAdvanceBean>post(Constant.CHECK_COMPANY_CENTER_ADVANCE)
                .params("datatype", 2)
                .params("limit", 10)
                .params("skip", mSkip)
                .execute(new JsonCallback<AddAdvanceBean>() {
                    @Override
                    public void onSuccess(Response<AddAdvanceBean> response) {
                        parseCompanyAdvanceData(response.body());
                    }

                    @Override
                    public void onError(Response<AddAdvanceBean> response) {
                        super.onError(response);
                        mXrecyclerActivityProduct.setLoadingMoreEnabled(false);
                        Logger.e("上传失败了" + response.message());
                    }
                });
    }

    private void parseCompanyAdvanceData(AddAdvanceBean addAdvanceBean) {
        if (addAdvanceBean.getCode() == 200) {
            if (mSkip == 0) {
                mDataList.clear();
                mXrecyclerActivityProduct.refreshComplete();
                mXrecyclerActivityProduct.setLoadingMoreEnabled(true);
            }
            if (addAdvanceBean.getData().size() < 10) {
                mXrecyclerActivityProduct.setLoadingMoreEnabled(false);
            }
            mDataList.addAll(addAdvanceBean.getData());
            mXrecyclerActivityProduct.refreshComplete();
        } else {
            mXrecyclerActivityProduct.setLoadingMoreEnabled(false);
        }
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListeners() {
        mXrecyclerActivityProduct.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mSkip = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                mSkip += 10;
                initData();
            }

        });
    }

    @OnClick({R.id.iv_company_prpduct_back, R.id.bt_add_product})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_company_prpduct_back:
                finish();
                break;
            case R.id.bt_add_product:
                toActivityWithResult(AddProductActivity.class, 0x0011);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x0022) {
            mSkip = 0;
            initData();
        }
    }
}
