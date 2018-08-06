package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvanceAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComPanyAdvanceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xrecycler_activity_adv)
    XRecyclerView mXrecyclerActivityAdv;
    @BindView(R.id.bt_add_advance)
    Button mBtAddAdvance;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_coreadvance;
    }

    @Override
    protected void initViews() {
        mXrecyclerActivityAdv.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        RequestCompanyAdvanceData();
    }

    private void RequestCompanyAdvanceData() {

        OkGo.<String>post(Constant.CHECK_COMPANY_CENTER_ADVANCE)
                .params("datatype",1)
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
        List<AddAdvanceBean.DataBean> dataList = addAdvanceBean.getData();
        if (code == 200) {
            AdvanceAdapter advanceAdapter = new AdvanceAdapter(mContext, dataList);
            mXrecyclerActivityAdv.setAdapter(advanceAdapter);
        }

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mBtAddAdvance.setOnClickListener(this);
        mXrecyclerActivityAdv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mXrecyclerActivityAdv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                mXrecyclerActivityAdv.refreshComplete();
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
            case R.id.bt_add_advance:
                toActivity(AddAdvanceActivity.class);
                break;
        }
    }
}
