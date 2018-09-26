package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JoinCallActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.rl_joincall_qudao)
    RelativeLayout rlJoincallQudao;
    @BindView(R.id.rl_joincall_product)
    RelativeLayout rlJoincallProduct;
    @BindView(R.id.tv_joincall_product_qudao_call)
    TextView tvJoincallProductQudaoCall;
    @BindView(R.id.tv_joincall_product_product_call)
    TextView tvJoincallProductProductCall;
    @BindView(R.id.iv_joincall_back)
    ImageView ivJoincallBack;
    private String mConsult_phone;
    private String mCooperation_phone;
    private SharedPreferences mSpConpanyname;
    private String mQudao;
    private String mHezuo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_join;
    }

    @Override
    protected void initViews() {
        RequestJoinCallData();

    }


    private void RequestJoinCallData() {
        if (TextUtils.isEmpty(mQudao) || TextUtils.isEmpty(mHezuo)) {
            OkGo.<String>post(Constant.COOPEREATE_CALL_URL).execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        JSONObject data = jsonObject.getJSONObject("data");
                        mConsult_phone = data.getString("consult_phone");
                        mCooperation_phone = data.getString("cooperation_phone");
                        tvJoincallProductQudaoCall.setText(String.valueOf(mConsult_phone));
                        tvJoincallProductProductCall.setText(String.valueOf(mCooperation_phone));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else {
            tvJoincallProductQudaoCall.setText(String.valueOf(mConsult_phone));
            tvJoincallProductProductCall.setText(String.valueOf(mCooperation_phone));
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected void initListeners() {
        rlJoincallQudao.setOnClickListener(this);
        rlJoincallProduct.setOnClickListener(this);
        ivJoincallBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_joincall_qudao:

                initCall(mCooperation_phone);
                break;
            case R.id.rl_joincall_product:

                initCall(mConsult_phone);
                break;
            case R.id.iv_joincall_back:
                finish();
                break;
        }
    }

    private void initCall(final String mobile) {
        if (PermissionsUtil.hasPermission(JoinCallActivity.this, Manifest.permission.CALL_PHONE)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            PermissionsUtil.requestPermission(JoinCallActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    asyncShowToast("权限被禁止  设置权限后再试试.");
                }
            }, Manifest.permission.CALL_PHONE);
        }
    }
}
