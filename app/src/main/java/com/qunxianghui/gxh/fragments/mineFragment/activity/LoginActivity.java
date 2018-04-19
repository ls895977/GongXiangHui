package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.MainActivity;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.db.StudentDao;
import com.qunxianghui.gxh.third.sina.Constants;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qunxianghui.gxh.base.MyApplication.APP_ID;

/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class LoginActivity extends BaseActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final int LOGIN_RESULT = 1;


    private IWXAPI api;


    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_regist)
    TextView tvLoginRegist;
    @BindView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.iv_wx_login)
    ImageView ivWxLogin;
    @BindView(R.id.iv_qq_login)
    ImageView ivQqLogin;
    @BindView(R.id.iv_sina_login)
    ImageView ivSinaLogin;
    private StudentDao studentDao;
    private String phone;
    private String password;
    private SsoHandler mSsoHandler;

    /**
     * 封装了 "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能
     */
    private Oauth2AccessToken mAccessToken;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;


    }

    @Override
    protected void initViews() {
        //微信
        api = WXAPIFactory.createWXAPI(this, APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(APP_ID);
        //微博

        WbSdk.install(getApplicationContext(), new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户登录");

        studentDao = new StudentDao(mContext);
    }

    @Override
    protected void initDatas() {
        mSsoHandler = new SsoHandler(LoginActivity.this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_login_login, R.id.tv_login_regist, R.id.tv_login_forget_password, R.id.iv_wx_login, R.id.iv_qq_login, R.id.iv_sina_login})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bt_login_login:
                phone = etLoginPhone.getText().toString().trim();
                password = etLoginPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    Toast.makeText(mContext, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!REGutil.checkCellphone(phone)) {
                    Toast.makeText(mContext, "手机格式错误了，请检查重试", Toast.LENGTH_SHORT).show();
                } else {

//                    UserService userService = new UserService(mContext);
                    Cursor cursor = studentDao.query(phone, password);

                    if (cursor.moveToNext()) {
                        Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                        toActivityWithResult(MainActivity.class, LOGIN_REQUEST);
                    } else {
                        Toast.makeText(mContext, "登录失败", Toast.LENGTH_SHORT).show();
                    }
                }


                break;
            case R.id.tv_login_regist:
                intent = new Intent(this, RegistActivity.class);
                startActivity(intent);


                break;
            case R.id.tv_login_forget_password:
                intent = new Intent(this, SeekPasswordActivity.class);
                startActivity(intent);
                phone = etLoginPhone.getText().toString();
                password = etLoginPassword.getText().toString();
                studentDao.query(phone, password);

                break;
            case R.id.iv_wx_login:
                loginWx();
                break;
            case R.id.iv_qq_login:
                break;
            case R.id.iv_sina_login:
                mSsoHandler.authorize(new SelfWbAuthListener());

                break;

        }
    }

    private void loginWx() {
        if (MyApplication.api == null) {
            MyApplication.api = WXAPIFactory.createWXAPI(this, MyApplication.APP_ID, true);
        }
        if (!MyApplication.api.isWXAppInstalled()) {
            asyncShowToast("您手机尚未安装微信，请安装后再登录");
            return;
        }

        MyApplication.api.registerApp(MyApplication.APP_ID);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        //官方说明：用于保持请求和回调的状态，授权请求后原样带回给第三方。该参数可用于防止csrf攻击（跨站请求伪造攻击），建议第三方带上该参数，可设置为简单的随机数加session进行校验
        req.state = "wechat_sdk_xb_live_state";
        MyApplication.api.sendReq(req);
    }


    private class SelfWbAuthListener implements WbAuthListener {

        @Override
        public void onSuccess(final Oauth2AccessToken token) {
            LoginActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = token;
                    if (mAccessToken.isSessionValid()) {
                        // 显示 Token
//                     updateTokenView(false);
                        Log.i("获取mAccessToken成功", "获取mAccessToken成功");
                        Log.i("mAccessToken为:", String.valueOf(mAccessToken));
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(mContext, "取消授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(mContext, "授权失败", Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * 显示当前的token信息
//     * @param hasExisted  配置文件中是否已存在 token 信息并且合法
//     */
//    private void updateTokenView(boolean hasExisted) {
//        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(
//                new java.util.Date(mAccessToken.getExpiresTime()));
//        String format = getString(R.string.weibosdk_demo_token_to_string_format_1);
//
//
//        String message = String.format(format, mAccessToken.getToken(), date);
//        if (hasExisted) {
//            message = getString(R.string.weibosdk_demo_token_has_existed) + "\n" + message;
//        }
//    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //sso回调 必须重写
        if (mSsoHandler != null) {
            mSsoHandler.authorizeCallBack(requestCode, resultCode, data);


        }
    }
}

