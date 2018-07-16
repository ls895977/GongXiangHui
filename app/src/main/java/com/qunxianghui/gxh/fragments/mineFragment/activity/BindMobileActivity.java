package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.MainActivity;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.SPUtils;

import butterknife.BindView;

/**
 * Created by user on 2018/6/22.
 */

public class BindMobileActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_bindmobile_phone)
    EditText EtBindmobilePhone;
    @BindView(R.id.iv_bindmobile_code)
    ImageView ivBindmobileCode;
    @BindView(R.id.et_bindmobile_code)
    EditText etBindmobileCode;
    @BindView(R.id.tv_bindmobile_getcode)
    TextView tvBindmobileGetcode;
    @BindView(R.id.tv_bindmobile_password)
    EditText tvBindmobilePassword;
    @BindView(R.id.bt_bindmobile_bindmobile)
    Button btBindmobileBindmobile;
    private String phoneNumber;
    private String mobileCode;
    private String bindPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_mobile;
    }

    @Override
    protected void initViews() { }

    @Override
    protected void initDatas() { }

    @Override
    protected void initListeners() {
        super.initListeners();
        tvBindmobileGetcode.setOnClickListener(this);
        btBindmobileBindmobile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        bindPassword = tvBindmobilePassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.bt_bindmobile_bindmobile:
                BindMobilePhone();
                break;

            case R.id.tv_bindmobile_getcode:
                getVertifiCode();
                break;
        }
    }

    private void BindMobilePhone() {
        mobileCode = etBindmobileCode.getText().toString().trim();
        phoneNumber = EtBindmobilePhone.getText().toString().trim();
        if (TextUtils.isEmpty(mobileCode) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(bindPassword)) {
            asyncShowToast("检查一下手机号 验证码 密码那个没有填");
        } else {
            OkGo.<LzyResponse<LoginBean>>post(Constant.LOGIN_BINE_MOBILE_URL)
                    .params("mobile", phoneNumber)
                    .params("captcha", mobileCode)
                    .params("password", bindPassword)
                    .params("connect_id", getIntent().getIntExtra("connect_id", 0))
                    .execute(new DialogCallback<LzyResponse<LoginBean>>(this) {
                        @Override
                        public void onSuccess(Response<LzyResponse<LoginBean>> response) {
                                if (response.body().code==0) {
                                    String access_token = response.body().data.getAccessTokenInfo().getAccess_token();
                                    SPUtils.saveString(mContext, SpConstant.ACCESS_TOKEN, access_token);
                                    SPUtils.saveBoolean(mContext, SpConstant.IS_COMPANY, response.body().data.getCompany_id() != 0);
                                    MyApplication.getApp().setAccessToken(access_token);
                                    Log.e(TAG, "onSuccess: " + access_token);
                                    asyncShowToast("登录成功");
                                    toActivity(MainActivity.class);
                                    finish();
                                } else {
                                    asyncShowToast("绑定失败" + response.body().toString());
                                }
                            }

                    });
        }
    }

    private void getVertifiCode() {
        //同步更新界面显示，倒计时验证码
        phoneNumber = EtBindmobilePhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(Constant.REFIST_SEND_CODE_URL).tag(TAG)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", phoneNumber)
                .params("type", 3)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final GeneralResponseBean responseBean = GsonUtil.parseJsonWithGson(response.body(), GeneralResponseBean.class);
                        if (responseBean.getCode() == 0) {
                            timerHandler.sendEmptyMessage(MSG_SEND_SUCCESS);

                        } else {
                            timerHandler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        timerHandler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
                    }
                });

    }

    private TimerHandler timerHandler = new TimerHandler();
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
                    timerHandler.sendEmptyMessage(MSG_TIMER);
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
            tvBindmobileGetcode.setText(String.format("倒计时%s秒", time));
            time -= 1;
            chageSendStatus(false);
            timerHandler.sendEmptyMessageDelayed(MSG_TIMER, 1000);
        } else {
            tvBindmobileGetcode.setText("获取验证码");
            time = 60;
            chageSendStatus(true);
        }
    }

    private void chageSendStatus(boolean canSend) {

        if (canSend) {
            tvBindmobileGetcode.setEnabled(true);
            tvBindmobileGetcode.setClickable(true);
        } else {
            tvBindmobileGetcode.setClickable(false);
            tvBindmobileGetcode.setEnabled(false);
        }

    }

}
