package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.GeneralResponseBean;
import com.qunxianghui.gxh.bean.mine.RegistBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.ProtocolActivity;
import com.qunxianghui.gxh.utils.GsonUtil;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.widget.NoLineClickSpan;
import com.qunxianghui.gxh.widget.TitleBuilder;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/3/12 0012.
 */

public class RegistActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_leftIco)
    ImageView titleLeftIco;
    @BindView(R.id.title_text)
    TextView titleText;
    @BindView(R.id.title_rightIco)
    ImageView titleRightIco;
    @BindView(R.id.title_bar)
    RelativeLayout titleBar;
    @BindView(R.id.top_bar)
    LinearLayout topBar;
    @BindView(R.id.et_regist_phone)
    EditText etRegistPhone;
    @BindView(R.id.iv_login_password)
    ImageView ivLoginPassword;


    @BindView(R.id.tv_login_quickly)
    TextView tvLoginQuickly;
    @BindView(R.id.tv_regist_code)
    TextView tvRegistCode;
    @BindView(R.id.et_register_password)
    EditText etRegisterPassword;
    @BindView(R.id.bt_register_quickly)
    Button btRegisterQuickly;
    @BindView(R.id.tv_protocol)
    TextView tvProtocol;
    @BindView(R.id.tv_call)
    TextView tvCall;
    @BindView(R.id.et_registerconfirm_password)
    EditText etRegisterconfirmPassword;
    @BindView(R.id.et_regist_code)
    EditText etRegistCode;
    //    private StudentDao studentDao;
    private String mPhone;
    private UserDao userDao;


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

        initProtocol();
        initCall();
        userDao = new UserDao(mContext);

    }

    private void initCall() {
        tvCall.setText("如需帮助可拨打群享汇服务热线");
        SpannableString spCall = new SpannableString("13295815771");
        String s2 = "13295815771";
        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        NoLineClickSpan clickSpan1 = new NoLineClickSpan("#ff4049") {
            @Override
            public void onClick(View widget) {

                builder.setTitle("拨打给客服？");
                builder.setMessage("13295815771");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "13295815771"));
                        if (ActivityCompat.checkSelfPermission(RegistActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        startActivity(intent);

                    }
                });
                builder.setNeutralButton("取消", null);
                builder.show();
            }
        };
        spCall.setSpan(clickSpan1, spCall.length() - s2.length(), spCall.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvCall.append(spCall);
        tvCall.setMovementMethod(LinkMovementMethod.getInstance());
        //设置文本不高亮，如果需要点击后高亮文本，删掉这句即可
        tvCall.setHighlightColor(Color.parseColor("#ff4049"));

    }

    /**
     * 协议
     */
    private void initProtocol() {
        tvProtocol.setText("未注册群享汇的手机号，点击确认时自动注册，且代表您已同意");
        SpannableString spStr = new SpannableString("《群享汇服务协议》");
        String s2 = "《群享汇服务协议》";
        NoLineClickSpan clickSpan2 = new NoLineClickSpan("#ff4049") {
            @Override
            public void onClick(View widget) {
//                Toast.makeText(LoginActivity.this, "至尊宝豪车共享服务协议", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ProtocolActivity.class);
                intent.putExtra("title", "平台服务协议");
                intent.putExtra("url", Constant.BenDiService);
                startActivity(intent);

            }
        };
        spStr.setSpan(clickSpan2, spStr.length() - s2.length(), spStr.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        tvProtocol.append(spStr);
        tvProtocol.setMovementMethod(LinkMovementMethod.getInstance());
//        //设置文本不高亮，如果需要点击后高亮文本，删掉这句即可
        tvProtocol.setHighlightColor(Color.parseColor("#ff4049"));
    }

    @Override
    protected void initData() {
        tvLoginQuickly.setOnClickListener(this);
        btRegisterQuickly.setOnClickListener(this);
        tvRegistCode.setOnClickListener(this);
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
                } else if (!REGutil.checkCellphone(phone)){
                    asyncShowToast("手机号格式不对");

                }else {
                    final String confirmpass = etRegisterconfirmPassword.getText().toString().trim();
                    if (!pass.equals(confirmpass)) {
                        asyncShowToast("两次输入的密码不一致");
                    } else {
                        if (userDao.dbQueryOneByUsername(phone) == null) {
                            userDao.dbInsert(phone, pass);
//                            asyncShowToast("注册成功" + phone + ",密码:" + pass);
                            RegistUser(phone,pass,registCode);
                        } else {
                            asyncShowToast("该用户已经注册");
                        }
                    }
                }
                break;

            case R.id.tv_regist_code:
                getVertifiCode();
                break;
        }
    }

    private void RegistUser(String phone, String pass,String registCode) {

        OkGo.<String>post(Constant.REGIST_URL).tag(TAG)
                .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile",phone)
                .params("password",pass)
                .params("captcha",registCode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        final RegistBean registBean = GsonUtil.parseJsonWithGson(response.body(), RegistBean.class);
                        if (registBean.getCode()==0){
                            asyncShowToast("注册成功");
                            toActivity(LoginActivity.class);
                        }
                    }
                });
    }

    private void getVertifiCode() {
        //同步更新界面显示，倒计时验证码
        mPhone = etRegistPhone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            Toast.makeText(mContext, "手机号为空", Toast.LENGTH_SHORT).show();
            return;
        }

        OkGo.<String>post(Constant.REFIST_SEND_CODE_URL).tag(TAG)
                 .cacheKey("cachePostKey")
                .cacheMode(CacheMode.DEFAULT)
                .params("mobile",mPhone)
                .params("type",1)
                .execute(new StringCallback() {
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

    public class TimerHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {

            //验证码倒计时
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

