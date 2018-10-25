package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

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
    @BindView(R.id.et_fetch_pass_code)
    EditText etFetchPassCode;
    private String phoneNumber;
    private String vertifiCode;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_seek_password;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("密码找回");
    }

    @Override
    protected void initData() {
        btSeekPasswordNext.setOnClickListener(this);
        etSeekPasswordPhoneNumber.setOnClickListener(this);
        tvSeekPasswordCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_seekPassword_phoneNumber:
                break;
            case R.id.tv_seekPassword_code:
                if (Utils.isTwoClick()) {
                    getVertifiCode();
                }

                break;
            case R.id.bt_seek_password_next:
                RequestNextStep();
                break;
        }
    }

    /**
     * 点击下一步
     */
    private void RequestNextStep() {
        vertifiCode = etFetchPassCode.getText().toString().trim();
        OkGo.<CommonBean>post(Constant.SEEK_PASSWORD_URL)
                .params("mobile", phoneNumber)
                .params("captcha", vertifiCode)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        final int code = response.body().code;
                        if (code == 101) {
                            asyncShowToast("验证码不正确");
                        } else if (code == 0) {
                            Intent intent = new Intent(mContext, ResetPasswordActivity.class);
                            intent.putExtra("mobile", phoneNumber);
                            intent.putExtra("captcha", vertifiCode);
                            startActivity(intent);
                        }
                    }
                });
    }

    private void getVertifiCode() {
        //同步更新界面显示，倒计时验证码
        phoneNumber = etSeekPasswordPhoneNumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<GeneralResponseBean>post(Constant.REFIST_SEND_CODE_URL).tag(TAG)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", phoneNumber)
                .params("type", 2)
                .execute(new JsonCallback<GeneralResponseBean>() {
                    @Override
                    public void onSuccess(Response<GeneralResponseBean> response) {
                        GeneralResponseBean responseBean = response.body();
                        if (responseBean.getCode() == 0) {
                            handler.sendEmptyMessage(MSG_SEND_SUCCESS);
                        } else {
                            asyncShowToast(responseBean.getMessage());
                        }
                    }

                    @Override
                    public void onError(Response<GeneralResponseBean> response) {
                        asyncShowToast(response.body().getMessage());
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
