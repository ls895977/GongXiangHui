package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.ProductAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComPanyProductActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xrecycler_activity_product)
    XRecyclerView mXrecyclerActivityProduct;
    @BindView(R.id.bt_add_product)
    Button mBtAddProduct;

    private List<String> mTitle = new ArrayList<>();
    private List<AddAdvanceBean.DataBean> mDataList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coreproduct;
    }

    @Override
    protected void initViews() {
        mXrecyclerActivityProduct.setLayoutManager(new GridLayoutManager(mContext, 2, GridLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        RequestCompanyProductData();
    }

    /*查看公司产品*/
    private void RequestCompanyProductData() {

        OkGo.<String>post(Constant.CHECK_COMPANY_CENTER_ADVANCE)
                .params("datatype", 2)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        parseCompanyAdvanceData(response.body());

                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        com.orhanobut.logger.Logger.e("上传失败了" + response.message());
                    }
                });

    }

    private void parseCompanyAdvanceData(String body) {
        AddAdvanceBean addAdvanceBean = GsonUtils.jsonFromJson(body, AddAdvanceBean.class);
        int code = addAdvanceBean.getCode();
        mDataList = addAdvanceBean.getData();

        if (code == 200) {
            ProductAdapter productAdapter = new ProductAdapter(mContext, mDataList);
            mXrecyclerActivityProduct.setAdapter(productAdapter);
            productAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View v, int position) {
                    Intent intent = new Intent(mContext, AddProductActivity.class);
                    intent.putExtra("viewTag", 1);
                    intent.putExtra("aboutus_id", mDataList.get(position - 1).getAboutus_id());
                    intent.putExtra("title", mDataList.get(position - 1).getTitle());
                    intent.putExtra("describe", mDataList.get(position - 1).getDescribe());
                    intent.putStringArrayListExtra("image_array", (ArrayList<String>) mDataList.get(position - 1).getImage_array());
                    startActivity(intent);
                }
            });
        }
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
        switch (v.getId()) {
            case R.id.bt_add_product:
                Intent intent = new Intent(mContext, AddProductActivity.class);
                intent.putExtra("viewTag", 2);
                startActivity(intent);
                break;
        }
    }
}
