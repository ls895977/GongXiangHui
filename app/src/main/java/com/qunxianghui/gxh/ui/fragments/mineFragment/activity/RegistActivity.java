package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonBean;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.utils.Utils;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.et_regist_phone)
    EditText etRegistPhone;
    @BindView(R.id.tv_login_quickly)
    TextView tvLoginQuickly;
    @BindView(R.id.tv_regist_code)
    TextView tvRegistCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.bt_register_quickly)
    Button btRegisterQuickly;
    @BindView(R.id.et_regist_code)
    EditText etRegistCode;
    @BindView(R.id.tv_regist_link)
    TextView tvRegistLink;
    @BindView(R.id.ch_regist)
    CheckBox chRegist;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户注册");
    }

    @Override
    protected void initData() {
        tvLoginQuickly.setOnClickListener(this);
        btRegisterQuickly.setOnClickListener(this);
        tvRegistCode.setOnClickListener(this);
        tvRegistLink.setOnClickListener(this);
        chRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_quickly:
                finish();
                break;
            case R.id.bt_register_quickly:
                String phone = etRegistPhone.getText().toString().trim();
                String pass = etRegisterPassword.getText().toString().trim();
                final String registCode = etRegistCode.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass)) {
                    asyncShowToast("手机号和密码不能为空");
                } else if (!REGutil.checkCellphone(phone)) {
                    asyncShowToast("手机号格式不对");
                } else if (!chRegist.isChecked()) {
                    asyncShowToast("请勾选用户协议");
                } else {

                    RegisterUser(phone, pass, registCode);
                }
                break;

            case R.id.tv_regist_code:
                if (Utils.isTwoClick()) {
                    getVertifiCode();
                }

                break;
            case R.id.tv_regist_link:
                Intent intent = new Intent(mContext, ProtocolActivity.class);
                intent.putExtra("title", "平台服务协议");
                intent.putExtra("url", Constant.BenDiService);
                intent.putExtra("tag", 2);
                startActivity(intent);
                break;
            case R.id.ch_regist:
                break;
        }
    }

    private void RegisterUser(String phone, String pass, String registCode) {
        OkGo.<CommonBean>post(Constant.REGIST_URL)
                .params("mobile", phone)
                .params("password", pass)
                .params("captcha", registCode)
                .execute(new JsonCallback<CommonBean>() {
                    @Override
                    public void onSuccess(Response<CommonBean> response) {
                        asyncShowToast(response.body().message);
                        if (response.body().code == 0) {
                            asyncShowToast("注册成功");
                            toActivity(LoginActivity.class);
                        } else {
                            asyncShowToast(response.body().message);

                        }
                    }

                    @Override
                    public void onError(Response<CommonBean> response) {
                        super.onError(response);
                        asyncShowToast(response.body().message);
                    }
                });
    }

    private void getVertifiCode() {
        //同步更新界面显示，倒计时验证码
        String mPhone = etRegistPhone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }
        OkGo.<String>post(Constant.REFIST_SEND_CODE_URL)
                .tag(TAG)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile", mPhone)
                .params("type", 1)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final GeneralResponseBean responseBean = GsonUtil.parseJsonWithGson(response.body(), GeneralResponseBean.class);
                        asyncShowToast(responseBean.getMessage());
                        if (responseBean.getCode() == 0) {
                            handler.sendEmptyMessage(MSG_SEND_SUCCESS);
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

    public class TimerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            //验证码倒计时
            switch (msg.what) {
                case MSG_SEND_SUCCESS:
                    handler.sendEmptyMessage(MSG_TIMER);
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
            tvRegistCode.setText(String.format("倒计时%s秒", time));
            time -= 1;
            chageSendStatus(false);
            handler.sendEmptyMessageDelayed(MSG_TIMER, 1000);
        } else {
            tvRegistCode.setText("获取验证码");
            time = 60;
            chageSendStatus(true);
        }

    }

    private void chageSendStatus(boolean canSend) {
        if (canSend) {
            tvRegistCode.setEnabled(true);
            tvRegistCode.setClickable(true);
        } else {
            tvRegistCode.setEnabled(false);
            tvRegistCode.setClickable(false);
        }
    }
}

