package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.widget.TitleBuilder;

import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 会员升级界面
 * Created by Administrator on 2018/4/16 0016.
 */

public class MemberUpActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_member_up_quickly_activate)
    TextView tvMemberUpQuicklyActivate;
    @BindView(R.id.et_activity_member_code)
    EditText etActivityMemberCode;

    @Override
    protected int getLayoutId() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return R.layout.activity_member_up;


    }

    @Override
    protected void initViews() {


    }

    @Override
    protected void initDatas() {
        new TitleBuilder(MemberUpActivity.this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("产品介绍");

    }

    @Override
    protected void initListeners() {
        super.initListeners();
        tvMemberUpQuicklyActivate.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        final String activiteCode = etActivityMemberCode.getText().toString().trim();
        switch (v.getId()) {
            case R.id.tv_member_up_quickly_activate:
                ActiviteCompanyData(activiteCode);
                break;
        }
    }

    private void ActiviteCompanyData(String activiteCode) {
        if (TextUtils.isEmpty(activiteCode)) {
            asyncShowToast("激活码不能为空");
        }else {
            OkGo.<String>post(Constant.PERSON_UPGRADE_URL)
                    .params("activecode", activiteCode)
            .execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {

                   asyncShowToast("该账号已激活");

                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                    asyncShowToast("激活失败 具体原因是"+response.body().toString());

                    com.orhanobut.logger.Logger.e("激活失败，原因是："+response.body().toString());
                }
            });
        }

    }


}
