package com.qunxianghui.gxh.ui.fragments.mineFragment.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GeneralMaterialAdapter;
import com.qunxianghui.gxh.base.BaseFragment;
import com.qunxianghui.gxh.bean.EnterpriseMaterial;
import com.qunxianghui.gxh.bean.GeneralMaterial;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.activity.GeneralMaterialActivity;
import com.qunxianghui.gxh.widget.CustomLoadMoreView;

import java.util.List;

import butterknife.BindView;

public class GeneralMateriaItemFragment extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView mRv;

    private GeneralMaterialAdapter mAdapter;
    private int mLastPosition = -1;
    private int mPage;
    private int mCount = 10;
    private int mAdType;
    private int mType;

    private EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert mCompanyAdvert;
    public static List<EnterpriseMaterial.EnterpriseMaterialBean.CompanyAdvert> mList;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_enterprise_materia_item;
    }

    @Override
    public void initViews(View view) {
        mType = getArguments().getInt("type");
        mAdType = getArguments().getInt("ad_type");
        mAdapter = new GeneralMaterialAdapter(R.layout.item_enterprise_material, mType);
        if (mType == 1 || mType == 3) {
            mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        } else {
            mRv.setLayoutManager(new GridLayoutManager(getContext(), 3));
        }
        mAdapter.bindToRecyclerView(mRv);
    }

    @Override
    public void initData() {
        OkGo.<GeneralMaterial>get(Constant.GENERAL_AD)
                .params("ad_type", mAdType)
                .params("page", mPage)
                .params("num", mCount)
                .execute(new JsonCallback<GeneralMaterial>() {
                    @Override
                    public void onSuccess(Response<GeneralMaterial> response) {
                        if (response.body() != null && response.body().code == 200) {
                            mAdapter.addData(response.body().data.generalAdList);
                            if (response.body().data.generalAdList.size() < mCount) {
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
                mCompanyAdvert = mAdapter.getData().get(position);
                View select;
                if (GeneralMaterialActivity.sIsMultiSelect) {
                    mCompanyAdvert.isSelect = !mCompanyAdvert.isSelect;
                    if (mCompanyAdvert.isSelect)
                        mList.add(mCompanyAdvert);
                    else
                        mList.remove(mCompanyAdvert);
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
                        select.setVisibility(mCompanyAdvert.isSelect ? View.VISIBLE : View.GONE);
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
                mList.add(mCompanyAdvert);
                mLastPosition = position;
                mCompanyAdvert.isSelect = !mCompanyAdvert.isSelect;
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
                    select.setVisibility(mCompanyAdvert.isSelect ? View.VISIBLE : View.GONE);
                }
                mList.clear();
                if (mCompanyAdvert.isSelect) {
                    mList.add(mCompanyAdvert);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser && mAdapter != null && !GeneralMaterialActivity.sIsMultiSelect) {
            mList.remove(mCompanyAdvert);
            View viewByPosition = mAdapter.getViewByPosition(mLastPosition, R.id.iv_select_tonglang);
            if (viewByPosition != null) viewByPosition.setVisibility(View.GONE);
        }
    }
}
