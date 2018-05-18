package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/3/15 0015.
 */

public class SeekPasswordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.bt_seek_password_next)
    Button btSeekPasswordNext;
    @BindView(R.id.et_seekPassword_phoneNumber)
    EditText etSeekPasswordPhoneNumber;
    @BindView(R.id.tv_seekPassword_code)
    TextView tvSeekPasswordCode;
    private String phoneNumber;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_seek_password;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("密码找回");
    }

    @Override
    protected void initDatas() {
        btSeekPasswordNext.setOnClickListener(this);
        etSeekPasswordPhoneNumber.setOnClickListener(this);
        tvSeekPasswordCode.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.et_seekPassword_phoneNumber:

                break;
            case R.id.tv_seekPassword_code:
                getVertifiCode();
                break;
            case R.id.bt_seek_password_next:
                asyncShowToast("默认请求接口成功");
                finish();
                break;

        }
    }

    private void getVertifiCode() {
        //同步更新新界面显示，倒计时验证码
        phoneNumber = etSeekPasswordPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>get(Constant.API_GET_CODE)
                .params("mobile", phoneNumber).tag(TAG).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                final GeneralResponseBean responseBean = GsonUtil.parseJsonWithGson(response.body(), GeneralResponseBean.class);
                if (responseBean.getCode() == 0) {
                    handler.sendEmptyMessage(MSG_SEND_SUCCESS);

                } else {
                    handler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
                }
            }
            @Override
            public void onError(Response<String> response) {
                handler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
            }
        });

    }

    private TimerHandler handler = new TimerHandler();
    public static final int MSG_SEND_SUCCESS = 0;
    private static final int MSG_SEND_CODE_ERROR = 1;
    private static final int MSG_TIMER = 2;

    private int time = 60;

    private class TimerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SEND_SUCCESS:
                    Toast.makeText(mContext, "验证码发送成功", Toast.LENGTH_SHORT).show();
                    handler.sendEmptyMessage(MSG_TIMER);
                    break;
                case MSG_SEND_CODE_ERROR:
                    Toast.makeText(mContext, "验证码发送失败，请重试", Toast.LENGTH_SHORT).show();
                    break;
                case MSG_TIMER:
                    startTimer();
                    break;
            }
            super.handleMessage(msg);
        }
    }
    private void startTimer() {

        if (time > 0) {
            tvSeekPasswordCode.setText(String.format("倒计时%s秒", time));
            time -= 1;
            chageSendStatus(false);
            handler.sendEmptyMessageDelayed(MSG_TIMER, 1000);
        } else {
            tvSeekPasswordCode.setText("获取验证码");
            time = 60;
            chageSendStatus(true);
        }
    }

    private void chageSendStatus(boolean canSend) {

        if (canSend) {
            tvSeekPasswordCode.setEnabled(true);
            tvSeekPasswordCode.setClickable(true);
        } else {
            tvSeekPasswordCode.setClickable(false);
            tvSeekPasswordCode.setEnabled(false);
        }

    }

}
