package com.qunxianghui.gxh.fragments.locationFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;

import java.util.logging.Handler;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InFormActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_inform_back)
    ImageView ivInformBack;
    @BindView(R.id.iv_inform_close)
    ImageView ivInformClose;
    @BindView(R.id.ll_infprm_remember)
    LinearLayout llInfprmRemember;
    @BindView(R.id.et_inform_content)
    EditText etInformContent;
    @BindView(R.id.tv_inform_inform)
    TextView tvInformInform;
    private String content;
    private int model_id;
    private android.os.Handler handler = new android.os.Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inform;
    }

    @Override
    protected void initViews() {
        content = etInformContent.getText().toString().trim();


    }

    @Override
    protected void initDatas() {
        final Intent intent = getIntent();
        model_id = intent.getIntExtra("model_id", 1);

    }

    @Override
    protected void initListeners() {
        ivInformBack.setOnClickListener(this);
        ivInformClose.setOnClickListener(this);
        tvInformInform.setOnClickListener(this);
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
            case R.id.iv_inform_back:
                finish();
                break;
            case R.id.tv_inform_inform:
                InformData();
                break;
            case R.id.iv_inform_close:
                llInfprmRemember.setVisibility(View.GONE);

                break;
        }

    }

    private void InformData() {
        OkGo.<String>post(Constant.ADD_REPORT_URL)
                .params("content", content)
                .params("model", "Posts")
                .params("model_id", model_id)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(final Response<String> response) {
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                asyncShowToast("举报成功");
                                com.orhanobut.logger.Logger.d("举报信息+++++" + response.body().toString());

                            }
                        }, 500);

                    }
                });

    }
}
