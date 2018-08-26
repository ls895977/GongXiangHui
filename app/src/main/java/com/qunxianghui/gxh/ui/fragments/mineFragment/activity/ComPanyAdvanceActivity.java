package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvanceAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ComPanyAdvanceActivity extends BaseActivity {

    @BindView(R.id.xrecycler_activity_adv)
    XRecyclerView mXrecyclerActivityAdv;

    private int mPage;
    private boolean mIsRefresh = false;
    private List<AddAdvanceBean.DataBean> mDataList = new ArrayList<>();
    private AdvanceAdapter mAdvanceAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_coreadvance;
    }

    @Override
    protected void initViews() {
        mXrecyclerActivityAdv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
        mAdvanceAdapter = new AdvanceAdapter(mContext, mDataList);
        mXrecyclerActivityAdv.setAdapter(mAdvanceAdapter);
        mAdvanceAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(mContext, AddAdvanceActivity.class);
                intent.putExtra("info", mDataList.get(position - 1));
                startActivityForResult(intent, 0x0011);
            }
        });
    }

    @Override
    protected void initData() {
        OkGo.<AddAdvanceBean>post(Constant.CHECK_COMPANY_CENTER_ADVANCE)
                .params("datatype", 1)
                .params("limit", 10)
                .params("skip", mPage)
                .execute(new JsonCallback<AddAdvanceBean>() {
                    @Override
                    public void onSuccess(Response<AddAdvanceBean> response) {
                        parseCompanyAdvanceData(response.body());
                    }

                    @Override
                    public void onError(Response<AddAdvanceBean> response) {
                        super.onError(response);
                        mXrecyclerActivityAdv.setLoadingMoreEnabled(false);
                        Logger.e("上传失败了" + response.message());
                    }
                });
    }

    private void parseCompanyAdvanceData(AddAdvanceBean mAddAdvanceBean) {
        if (mIsRefresh) {
            mIsRefresh = false;
            mDataList.clear();
            mXrecyclerActivityAdv.setLoadingMoreEnabled(true);
        }
        mPage++;
        mDataList.addAll(mAddAdvanceBean.getData());
        int code = mAddAdvanceBean.getCode();
        if (code == 200) {
            mXrecyclerActivityAdv.setLoadingMoreEnabled(mAddAdvanceBean.getData().size() >= 10);
            mXrecyclerActivityAdv.refreshComplete();
            mAdvanceAdapter.notifyDataSetChanged();
        } else {
            mXrecyclerActivityAdv.setLoadingMoreEnabled(false);
        }
    }

    @Override
    protected void initListeners() {
        mXrecyclerActivityAdv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                mPage = 0;
                initData();
            }

            @Override
            public void onLoadMore() {
                initData();
            }
        });
    }

    @OnClick({R.id.iv_company_advance_back, R.id.bt_add_advance})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_company_advance_back:
                finish();
                break;
            case R.id.bt_add_advance:
                toActivityWithResult(AddAdvanceActivity.class, 0x0011);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0x0022) {
            mIsRefresh = true;
            mPage = 0;
            initData();
        }
    }
}
