package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;

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
    private String mTvJoincallProductQudaoCall;
    private String mTvJoincallProductProductCall;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_join;
    }

    @Override
    protected void initViews() {
        mTvJoincallProductQudaoCall = tvJoincallProductQudaoCall.getText().toString().trim();
        mTvJoincallProductProductCall = tvJoincallProductProductCall.getText().toString().trim();
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
                initCall(mTvJoincallProductQudaoCall);
                break;
            case R.id.rl_joincall_product:
                initCall(mTvJoincallProductProductCall);
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
