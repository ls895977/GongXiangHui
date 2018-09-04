package com.qunxianghui.gxh.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.BaoliaoDetailAdapter;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.BaoLiaoContentBean;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.observer.EventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/26 0026.
 */
public class BaoliaoDetailActivity extends BaseActivity implements View.OnClickListener, Observer {


    @BindView(R.id.iv_myissue_back)
    ImageView ivMyissueBack;
    @BindView(R.id.tv_myissue_cancel)
    TextView tvMyissueCancel;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_baoliao_title)
    TextView tvBaoliaoTitle;
    @BindView(R.id.tv_baoliao_time)
    TextView tvBaoliaoTime;
    @BindView(R.id.recyclerView)
    XRecyclerView recyclerView;
    private BaoliaoBean.DataBean dataBean;
    private List<BaoLiaoContentBean> mList = new ArrayList();
    private BaoliaoDetailAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baoliao_detail;
    }

    @Override
    protected void initViews() {
        EventManager.getInstance().addObserver(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        dataBean = (BaoliaoBean.DataBean) getIntent().getSerializableExtra("baoliao");

        mList = new Gson().fromJson(dataBean.getContent(), new TypeToken<List<BaoLiaoContentBean>>(){}.getType());
        mAdapter = new BaoliaoDetailAdapter(this,mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);


        if (!TextUtils.isEmpty(dataBean.getCtime())) {
            tvBaoliaoTime.setText(dataBean.getCtime());
        }
        if (!TextUtils.isEmpty(dataBean.getTitle())) {
            tvBaoliaoTitle.setText(dataBean.getTitle());
        }
    }

    @SuppressLint("ResourceAsColor")
    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initListeners() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_myissue_back:
                finish();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
