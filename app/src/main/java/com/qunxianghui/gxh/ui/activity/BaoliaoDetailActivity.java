package com.qunxianghui.gxh.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.BaoliaoBean;
import com.qunxianghui.gxh.observer.EventManager;

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
    @BindView(R.id.tv_baoliao_content)
    TextView tvBaoliaoContent;
    @BindView(R.id.iv_image0)
    ImageView ivImage0;
    @BindView(R.id.iv_image1)
    ImageView ivImage1;
    @BindView(R.id.iv_image2)
    ImageView ivImage2;

    private BaoliaoBean.DataBean dataBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_baoliao_detail;
    }

    @Override
    protected void initViews() {
        EventManager.getInstance().addObserver(this);

        dataBean = (BaoliaoBean.DataBean) getIntent().getSerializableExtra("baoliao");
        if(!TextUtils.isEmpty(dataBean.getCtime())){
            tvBaoliaoTime.setText(dataBean.getCtime());
        }if(!TextUtils.isEmpty(dataBean.getTitle())){
            tvBaoliaoTitle.setText(dataBean.getTitle());
        }if(!TextUtils.isEmpty(dataBean.getContent())){
            tvBaoliaoContent.setText(dataBean.getContent());
        }

        if(dataBean.getImages()!= null && !dataBean.getImages().isEmpty()){
            RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.default_img)
                    .error(R.mipmap.default_img)
                    .centerCrop();
            for (int i = 0; i < dataBean.getImages().size(); i++) {
                if(i == 0){
                    Glide.with(this).load(dataBean.getImages().get(i)).apply(requestOptions).into(ivImage0);
                }if(i == 1){
                    Glide.with(this).load(dataBean.getImages().get(i)).apply(requestOptions).into(ivImage1);
                }if(i == 2){
                    Glide.with(this).load(dataBean.getImages().get(i)).apply(requestOptions).into(ivImage2);
                }
            }
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
}
