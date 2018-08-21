package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.baseAdapter.BaseRecycleViewAdapter;
import com.qunxianghui.gxh.adapter.mineAdapter.AdvanceAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.AddAdvanceBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComPanyAdvanceActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.xrecycler_activity_adv)
    XRecyclerView mXrecyclerActivityAdv;
    @BindView(R.id.bt_add_advance)
    Button mBtAddAdvance;
    @BindView(R.id.iv_company_advance_back)
    ImageView ivCompanyAdvanceBack;

    private int count;
    private boolean mIsRefresh = false;
    private List<AddAdvanceBean.DataBean> mDataList = new ArrayList<>();
    private boolean mIsFirst = true;
    private AdvanceAdapter mAdvanceAdapter;
    private AddAdvanceBean mAddAdvanceBean;

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
                .params("datatype", 1)
                .params("limit", 5)
                .params("skip", count)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        parseCompanyAdvanceData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        Logger.e("上传失败了" + response.message());
                    }
                });

    }

    private void parseCompanyAdvanceData(String body) {
        mAddAdvanceBean = GsonUtils.jsonFromJson(body, AddAdvanceBean.class);
        if (mIsRefresh) {
            mIsRefresh = false;
            mDataList.clear();
        }
        mDataList.addAll(mAddAdvanceBean.getData());
        count = mDataList.size();
        int code = mAddAdvanceBean.getCode();
        if (code == 200) {
            if (mIsFirst) {
                mIsFirst = false;
                mAdvanceAdapter = new AdvanceAdapter(mContext, mDataList);
                mXrecyclerActivityAdv.setAdapter(mAdvanceAdapter);
                mAdvanceAdapter.setOnItemClickListener(new BaseRecycleViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        Intent intent = new Intent(mContext, AddAdvanceActivity.class);
                        intent.putExtra("viewTag", 1);
                        intent.putExtra("aboutus_id", mDataList.get(position - 1).getAboutus_id());
                        intent.putExtra("title", mDataList.get(position - 1).getTitle());
                        intent.putExtra("describe", mDataList.get(position - 1).getDescribe());
                        intent.putStringArrayListExtra("image_array", (ArrayList<String>) mDataList.get(position - 1).getImage_array());
                        startActivity(intent);

                    }
                });
            }
            mXrecyclerActivityAdv.refreshComplete();
            mAdvanceAdapter.notifyDataSetChanged();
            mAdvanceAdapter.notifyItemRangeChanged(count, mAddAdvanceBean.getData().size());

        }

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        mBtAddAdvance.setOnClickListener(this);
        ivCompanyAdvanceBack.setOnClickListener(this);
        mXrecyclerActivityAdv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mIsRefresh = true;
                count = 0;
                RequestCompanyAdvanceData();

            }

            @Override
            public void onLoadMore() {
                RequestCompanyAdvanceData();
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
                Intent intent = new Intent(mContext, AddAdvanceActivity.class);
                intent.putExtra("viewTag", 2);
                startActivity(intent);
                break;
            case R.id.iv_company_advance_back:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
