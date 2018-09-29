package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

/**
 * Created by user on 2018/6/22.
 */

public class BindMobileActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_bindmobile_phone)
    EditText EtBindMobilePhone;
    @BindView(R.id.et_bindmobile_code)
    EditText etBindMobileCode;
    @BindView(R.id.tv_bindmobile_getcode)
    TextView tvBindMobileGetcode;
    @BindView(R.id.tv_bindmobile_password)
    EditText tvBindMobilePassword;
    @BindView(R.id.bt_bindmobile_bindmobile)
    Button btBindmobileBindmobile;

    private String phoneNumber;
    private String bindPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_mobile;
    }

    @Override
    protected void initViews() {
        super.initViews();
        new TitleBuilder(BindMobileActivity.this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setTitleText("绑定手机号");
    }

    @Override
    protected void initListeners() {
        tvBindMobileGetcode.setOnClickListener(this);
        btBindmobileBindmobile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        bindPassword = tvBindMobilePassword.getText().toString().trim();
        switch (v.getId()) {
            case R.id.bt_bindmobile_bindmobile:
                BindMobilePhone();
                break;
            case R.id.tv_bindmobile_getcode:
                if (Utils.isTwoClick())
                    getVerifyCode();
                break;
        }
    }

    private void BindMobilePhone() {
        String mobileCode = etBindMobileCode.getText().toString().trim();
        phoneNumber = EtBindMobilePhone.getText().toString().trim();
        if (TextUtils.isEmpty(mobileCode) || TextUtils.isEmpty(phoneNumber) || TextUtils.isEmpty(bindPassword)) {
            asyncShowToast("检查一下手机号 验证码 密码那个没有填");
        } else {
            OkGo.<CommonResponse<LoginBean>>post(Constant.LOGIN_BINE_MOBILE_URL)
                    .params("mobile", phoneNumber)
                    .params("captcha", mobileCode)
                    .params("password", bindPassword)
                    .params("connect_id", getIntent().getIntExtra("connect_id", 0))
                    .execute(new DialogCallback<CommonResponse<LoginBean>>(this) {
                        @Override
                        public void onSuccess(Response<CommonResponse<LoginBean>> response) {
                            Log.e("TAG_绑定","onSuccess");
                            String access_token = response.body().data.getAccessTokenInfo().getAccess_token();
                            SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                            SPUtils.saveBoolean(SpConstant.IS_COMPANY, response.body().data.getCompany_id() != 0);
                            OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                            Log.e("TAG_绑定","onSuccess"+response.body().code);
                            if (response.body().code == 0) {
                                asyncShowToast(response.body().message);
                                toActivity(MainActivity.class);
                                finish();
                            } else {
                                asyncShowToast(response.body().message);
                            }
                        }

                        @Override
                        public void onError(Response<CommonResponse<LoginBean>> response) {
                            super.onError(response);
                            try {
                                CommonResponse<LoginBean> body = response.body();
                                if (body !=null){
                                    asyncShowToast(body.message);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
        }
    }

    private void getVerifyCode() {
        //同步更新界面显示，倒计时验证码
        phoneNumber = EtBindMobilePhone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            asyncShowToast("手机号为空");
            return;
        }

        OkGo.<GeneralResponseBean>post(Constant.REFIST_SEND_CODE_URL).tag(TAG)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", phoneNumber)
                .params("type", 3)
                .execute(new JsonCallback<GeneralResponseBean>() {
                    @Override
                    public void onSuccess(Response<GeneralResponseBean> response) {
                        GeneralResponseBean responseBean = response.body();
                        if (responseBean.getCode() == 0) {
                            timerHandler.sendEmptyMessage(MSG_SEND_SUCCESS);

                        } else {
                            timerHandler.sendEmptyMessage(MSG_SEND_CODE_ERROR);
                        }
                    }

                    @Override
                    public void onError(Response<GeneralResponseBean> response) {
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
                    asyncShowToast("验证码发送成功");
                    timerHandler.sendEmptyMessage(MSG_TIMER);
                    break;
                case MSG_SEND_CODE_ERROR:
                    asyncShowToast("验证码发送失败，请重试");
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
            tvBindMobileGetcode.setText(String.format("倒计时%s秒", time));
            time--;
            tvBindMobileGetcode.setEnabled(false);
            timerHandler.sendEmptyMessageDelayed(MSG_TIMER, 1000);
        } else {
            tvBindMobileGetcode.setText("获取验证码");
            time = 60;
            tvBindMobileGetcode.setEnabled(true);
        }
    }

}
