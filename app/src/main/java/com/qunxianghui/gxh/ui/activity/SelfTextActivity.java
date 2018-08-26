package com.qunxianghui.gxh.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.MyLiveList;
import com.qunxianghui.gxh.adapter.SelfCheckAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelfTextActivity extends BaseActivity implements View.OnClickListener, SelfCheckAdapter.OnItemClickListener {
    @BindView(R.id.btn_editor)
    TextView btnEditor;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_select_num)
    TextView tvSelectNum;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.select_all)
    TextView selectAll;
    @BindView(R.id.ll_mycollection_bottom_dialog)
    LinearLayout llMycollectionBottomDialog;

    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    private SelfCheckAdapter mSelfCheckAdapter;
    private List<MyLiveList> mList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_self_test;
    }

    @Override
    protected void initViews() {
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 30; i++) {
            MyLiveList myLiveList = new MyLiveList();
            myLiveList.setTitle("这是第" + i + "个条目");
            myLiveList.setSource("来源" + i);
            mList.add(myLiveList);
            mSelfCheckAdapter.notifyAdapter(mList, false);


        }

        mSelfCheckAdapter = new SelfCheckAdapter(mContext);
        recyclerview.setAdapter(mSelfCheckAdapter);
    }

    @Override
    protected void initListeners() {
        btnDelete.setOnClickListener(this);
        selectAll.setOnClickListener(this);
        btnEditor.setOnClickListener(this);
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
            case R.id.btn_delete:
                deleteVideo();
                break;
            case R.id.select_all:
                selectAllMain();
                break;
            case R.id.btn_editor:
                updataEditMode();
                break;
            default:
                break;
        }
    }

    /*点击编辑*/
    private void updataEditMode() {

    }

    /*嗲及全选*/
    private void selectAllMain() {
    }

    /*点击删除*/
    private void deleteVideo() {
    }


    /*条目的点击*/
    @Override
    public void onItemClickListener(int pos, List<MyLiveList> myLiveList) {

    }
}
