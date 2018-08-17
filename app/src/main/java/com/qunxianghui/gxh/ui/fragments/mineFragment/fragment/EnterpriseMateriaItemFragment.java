package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.EnterpriseMaterialAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.EnterpriseMaterialActivity;
import com.qunxianghui.gxh.widget.CustomLoadMoreView;

import java.util.List;

import butterknife.BindView;

public class EnterpriseMateriaItemFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private EnterpriseMaterialAdapter mAdapter;
    private int mLastPosition = -1;
    private int mPage;
    private int mCount = 10;
    // 0为贴片 1为大图通栏 3为通栏 其他统一
    private int mType;
    private int mPosition;

    public static List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enterprise_materia_item;
    }

    @Override
    public void initViews(View view) {
        mPosition = getArguments().getInt("position");
        mType = getArguments().getInt("type");
        mAdapter = new EnterpriseMaterialAdapter(R.layout.item_enterprise_material, mType);
        if (mType == 1 || mType == 3) {
            mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            mRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }
        mAdapter.bindToRecyclerView(mRv);
    }

    @Override
    public void initData() {
        OkGo.<EnterpriseMaterial>get(Constant.ENTERPRISE_MATERIAL)
                .params("position", mPosition)
                .params("ad_type", mType)
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
                if ((EnterpriseMaterialActivity.sType == 9 && mPosition == 1) || EnterpriseMaterialActivity.sType == mType) {
                    EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert companyAdvert = mAdapter.getData().get(position);
                    View select;
                    if (EnterpriseMaterialActivity.sIsMultiSelect) {
                        companyAdvert.isSelect = !companyAdvert.isSelect;
                        if (companyAdvert.isSelect)
                            mList.add(companyAdvert);
                        else
                            mList.remove(companyAdvert);
                        switch (mType) {
                            case 0:
                                select = mAdapter.getViewByPosition(position, R.id.iv_select_tiepian);
                                break;
                            case 1:
                                select = mAdapter.getViewByPosition(position, R.id.iv_select_big);
                                break;
                            case 3:
                                select = mAdapter.getViewByPosition(position, R.id.iv_select_tonglang);
                                break;
                            default:
                                select = mAdapter.getViewByPosition(position, R.id.iv_select_other);
                                break;
                        }
                        if (select != null) {
                            select.setVisibility(companyAdvert.isSelect ? View.VISIBLE : View.GONE);
                        }
                        return;
                    }
                    if (mLastPosition != -1 && mLastPosition != position) {
                        mAdapter.getData().get(mLastPosition).isSelect = false;
                        switch (mType) {
                            case 0:
                                select = mAdapter.getViewByPosition(mLastPosition, R.id.iv_select_tiepian);
                                break;
                            case 1:
                                select = mAdapter.getViewByPosition(mLastPosition, R.id.iv_select_big);
                                break;
                            case 3:
                                select = mAdapter.getViewByPosition(mLastPosition, R.id.iv_select_tonglang);
                                break;
                            default:
                                select = mAdapter.getViewByPosition(mLastPosition, R.id.iv_select_other);
                                break;
                        }
                        if (select != null) {
                            select.setVisibility(View.GONE);
                        }
                    }
                    mList.clear();
                    mList.add(companyAdvert);
                    mLastPosition = position;
                    companyAdvert.isSelect = !companyAdvert.isSelect;
                    switch (mType) {
                        case 0:
                            select = mAdapter.getViewByPosition(position, R.id.iv_select_tiepian);
                            break;
                        case 1:
                            select = mAdapter.getViewByPosition(position, R.id.iv_select_big);
                            break;
                        case 3:
                            select = mAdapter.getViewByPosition(position, R.id.iv_select_tonglang);
                            break;
                        default:
                            select = mAdapter.getViewByPosition(position, R.id.iv_select_other);
                            break;
                    }
                    if (select != null) {
                        select.setVisibility(companyAdvert.isSelect ? View.VISIBLE : View.GONE);
                    }
                    mList.clear();
                    if (companyAdvert.isSelect) {
                        mList.add(companyAdvert);
                    }
                } else {
                    asyncShowToast("当前广告类型不可选择");
                }
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

}
