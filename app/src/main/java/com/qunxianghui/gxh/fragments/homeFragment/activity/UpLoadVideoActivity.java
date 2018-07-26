package com.qunxianghui.gxh.fragments.homeFragment.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpLoadVideoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.iv_video_thumb)
    ImageView ivVideoThumb;
    @BindView(R.id.ll_uploadvideo_back)
    LinearLayout llUploadvideoBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_video;
    }

    @Override
    protected void initViews() {
        Intent intent = getIntent();
        ArrayList<Parcelable> videodata = intent.getParcelableArrayListExtra("videodata");
    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        llUploadvideoBack.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_uploadvideo_back:
                finish();
                break;
        }
    }
}
