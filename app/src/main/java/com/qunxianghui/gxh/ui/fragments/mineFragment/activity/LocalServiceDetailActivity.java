package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.MineIssueLocalServiceBean;
import com.qunxianghui.gxh.bean.mine.MyIssueGoodSelectBean;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocalServiceDetailActivity extends BaseActivity {
    @BindView(R.id.tv_localservice_detail_title)
    TextView tvLocalserviceDetailTitle;
    @BindView(R.id.iv_baoliao_detail_top)
    ImageView ivBaoliaoDetailTop;
    @BindView(R.id.tv_localservice_detail_industry)
    TextView tvLocalserviceDetailIndustry;
    @BindView(R.id.tv_localservice_detail_description)
    TextView tvLocalserviceDetailDescription;
    @BindView(R.id.tv_localservice_detail_linkname)
    TextView tvLocalserviceDetailLinkname;
    @BindView(R.id.tv_localservice_detail_qq)
    TextView tvLocalserviceDetailQq;
    @BindView(R.id.tv_localservice_detail_adress)
    TextView tvLocalserviceDetailAdress;
    @BindView(R.id.iv_localservice_detail_call)
    ImageView ivLocalserviceDetailCall;
    @BindView(R.id.ll_localservice_back)
    ImageView llLocalserviceBack;
    private MineIssueLocalServiceBean.DataBean mDataBean;
    private MyIssueGoodSelectBean.DataBean mGoodDataBean;
    private String mMobile;
    private int mPosition;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_localservice_detail;
    }

    @Override
    protected void initViews() {
        mPosition = getIntent().getIntExtra("position", 0);
        if (mPosition == 1) {
            mDataBean = (MineIssueLocalServiceBean.DataBean) getIntent().getSerializableExtra("localservice");
            mMobile = mDataBean.getMobile();
        } else if (mPosition == 2) {
            mGoodDataBean = (MyIssueGoodSelectBean.DataBean) getIntent().getSerializableExtra("goodselect");
            mMobile = mGoodDataBean.getMobile();
        }

    }

    @Override
    protected void initData() {


        if (mPosition == 1) {
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(mDataBean.getImages()).apply(options).into(ivBaoliaoDetailTop);
            tvLocalserviceDetailTitle.setText(mDataBean.getCompany_name());
            tvLocalserviceDetailIndustry.setText(mDataBean.getCate_name());
            tvLocalserviceDetailLinkname.setText(mDataBean.getLinkname());
            tvLocalserviceDetailQq.setText(mDataBean.getQq());
            tvLocalserviceDetailAdress.setText(mDataBean.getAddress());
            tvLocalserviceDetailDescription.setText(mDataBean.getDescription());
        } else {
            RequestOptions options = new RequestOptions();
            options.placeholder(R.mipmap.default_img);
            options.error(R.mipmap.default_img);
            Glide.with(mContext).load(mGoodDataBean.getImages()).apply(options).into(ivBaoliaoDetailTop);
            tvLocalserviceDetailTitle.setText("￥" + mGoodDataBean.getPrice());
            tvLocalserviceDetailTitle.setTextColor(Color.RED);
            tvLocalserviceDetailTitle.setTextSize(20);
            tvLocalserviceDetailIndustry.setText(mGoodDataBean.getCate_name());
            tvLocalserviceDetailLinkname.setText(mGoodDataBean.getLinkname());
            tvLocalserviceDetailQq.setText(mGoodDataBean.getMobile());
            tvLocalserviceDetailAdress.setText(mGoodDataBean.getAddress());
            tvLocalserviceDetailDescription.setVisibility(View.GONE);
        }

    }

    @Override
    protected void initListeners() {
        ivLocalserviceDetailCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initCall(mMobile);
            }
        });
        llLocalserviceBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private void initCall(final String mobile) {
        if (PermissionsUtil.hasPermission(LocalServiceDetailActivity.this, Manifest.permission.CALL_PHONE)) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + mobile));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            PermissionsUtil.requestPermission(LocalServiceDetailActivity.this, new PermissionListener() {
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
