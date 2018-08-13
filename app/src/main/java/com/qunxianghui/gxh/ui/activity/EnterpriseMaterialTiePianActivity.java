package com.qunxianghui.gxh.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.EnterpriseMaterialAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.widget.CustomLoadMoreView;

import butterknife.BindView;
import butterknife.OnClick;

public class EnterpriseMaterialTiePianActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.rv)
    RecyclerView mRv;

    private EnterpriseMaterialAdapter mAdapter;
    private int mLastPosition = -1;
    private int mPage;
    private int mCount = 10;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterprise_material_tiepian;
    }

    @Override
    protected void initViews() {
        mAdapter = new EnterpriseMaterialAdapter(R.layout.item_enterprise_material_tiepian, 0);
        mAdapter.bindToRecyclerView(mRv);
    }

    @Override
    protected void initData() {
        OkGo.<EnterpriseMaterial>get(Constant.ENTERPRISE_MATERIAL)
                .params("position", 4)
                .params("page", mPage)
                .params("num", mCount)
                .execute(new JsonCallback<EnterpriseMaterial>() {
                    @Override
                    public void onSuccess(Response<EnterpriseMaterial> response) {
                        if (response.body() != null && response.body().code == 200) {
                            mAdapter.addData(response.body().data.companyAdList);
                            if (response.body().data.companyAdList.size() < mCount) {
                                mAdapter.loadMoreEnd();
                            } else {
                                mPage++;
                                mAdapter.loadMoreComplete();
                            }
                        } else {
                            mAdapter.setEnableLoadMore(false);
                        }
                    }
                });
    }

    @Override
    protected void initListeners() {
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mLastPosition != -1 && mLastPosition != position) {
                    mAdapter.getData().get(mLastPosition).isSelect = false;
                    if (mAdapter.getViewByPosition(mLastPosition, R.id.iv_select) != null)
                        mAdapter.getViewByPosition(mLastPosition, R.id.iv_select).setVisibility(View.GONE);
                }
                mLastPosition = position;
                EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = mAdapter.getData().get(position);
                companyAdvert.isSelect = !companyAdvert.isSelect;
                mAdapter.getViewByPosition(position, R.id.iv_select).setVisibility(companyAdvert.isSelect ? View.VISIBLE : View.GONE);
            }
        });
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.tv_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_save:
                if (mLastPosition == -1) {
                    asyncShowToast("请选择一个广告");
                    return;
                }
                Intent intent = new Intent();
                intent.putExtra("info", mAdapter.getData().get(mLastPosition));
                setResult(0x0022, intent);
                finish();
                break;
        }
    }
}
