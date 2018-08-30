package com.qunxianghui.gxh.ui.fragments.homeFragment.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.homeAdapter.BianMinCallGridAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommoentCallActivity extends BaseActivity implements BianMinCallGridAdapter.OnItemClickListener {
    @BindView(R.id.iv_homebianmin_call_back)
    ImageView ivHomebianminCallBack;
    private int[] images = {R.mipmap.icon_commen_call_110, R.mipmap.icon_commen_call_120, R.mipmap.icon_commen_call_119
            , R.mipmap.icon_commen_call_112, R.mipmap.icon_commen_call_122,
            R.mipmap.icon_commen_call_1212, R.mipmap.icon_commen_call_12345, R.mipmap.icon_commen_call_xiaofei
            , R.mipmap.icon_commen_call_zhufang, R.mipmap.icon_commen_call_12333,
            R.mipmap.icon_commen_call_gongdian, R.mipmap.icon_commen_call_114, R.mipmap.icon_commen_call_tianqi
    };
    private int[] imagesCall = {R.mipmap.icon_comment_call, R.mipmap.icon_comment_call, R.mipmap.icon_comment_call
            , R.mipmap.icon_comment_call, R.mipmap.icon_comment_call,
            R.mipmap.icon_comment_call, R.mipmap.icon_comment_call, R.mipmap.icon_comment_call
            , R.mipmap.icon_comment_call, R.mipmap.icon_comment_call,
            R.mipmap.icon_comment_call, R.mipmap.icon_comment_call, R.mipmap.icon_comment_call

    };
    private String[] iconName = {"警匪", "急救中心", "火警", "紧急或报警电话", "交通事故报警", "高速公路报警救援", "市民专线",
            "消费者投诉", "全民住房公积金热线", "人力资源社会保障热线", "供电服务", "114电话导航", "天气预报"};

    private String[] NameCall = {"110", "120", "119", "112", "122", "1212", "12345",
            "12315", "12329", "12333", "95598", "114", "12121"};
    @BindView(R.id.recycler_comment_call)
    RecyclerView recyclerCommentCall;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comment_call;
    }

    @Override
    protected void initViews() {
        recyclerCommentCall.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void initData() {
        BianMinCallGridAdapter homegridNavigator = new BianMinCallGridAdapter(mContext, images, imagesCall, iconName, NameCall);
        homegridNavigator.setOnItemClickListener(this);
        recyclerCommentCall.setAdapter(homegridNavigator);
    }

    @Override
    protected void initListeners() {
        ivHomebianminCallBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    @Override
    public void onpicItemClick(int position) {
        switch (position) {
            case 0:
                requestCall(NameCall[0]);
                break;
            case 1:
                requestCall(NameCall[1]);
                break;
            case 2:
                requestCall(NameCall[2]);
                break;
            case 3:
                requestCall(NameCall[3]);
                break;
            case 4:
                requestCall(NameCall[4]);
                break;
            case 5:
                requestCall(NameCall[5]);
                break;
            case 6:
                requestCall(NameCall[6]);
                break;
            case 7:
                requestCall(NameCall[7]);
                break;
            case 8:
                requestCall(NameCall[8]);
                break;
            case 9:
                requestCall(NameCall[9]);
                break;
            case 10:
                requestCall(NameCall[10]);
                break;
            case 11:
                requestCall(NameCall[11]);
                break;
            case 12:
                requestCall(NameCall[12]);
                break;
            case 13:
                requestCall(NameCall[13]);
                break;
        }
    }

    /*打电话*/
    private void requestCall(final String s) {
        if (PermissionsUtil.hasPermission(CommoentCallActivity.this, new String[]{Manifest.permission.CALL_PHONE})) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + s));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else {
            PermissionsUtil.requestPermission(CommoentCallActivity.this, new PermissionListener() {
                @Override
                public void permissionGranted(@NonNull String[] permission) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + s));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }

                @Override
                public void permissionDenied(@NonNull String[] permission) {
                    asyncShowToast("权限被禁止  设置权限后再试试.");
                }
            }, new String[]{Manifest.permission.CALL_PHONE});
        }
    }

}
