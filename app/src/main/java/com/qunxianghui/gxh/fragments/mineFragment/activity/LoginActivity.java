package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
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
import com.orhanobut.logger.Logger;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.StudentDao;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.third.sina.Constants;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.qunxianghui.gxh.base.MyApplication.QQAPP_ID;
import static com.qunxianghui.gxh.base.MyApplication.WeiXinAPP_ID;


/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class LoginActivity extends BaseActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final int LOGIN_RESULT = 1;
    @BindView(R.id.tv_login_show_usermessage)
    TextView tvLoginShowUsermessage;


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
    private UserDao userDao;
    private String userText;
    private Tencent mTencent;
    private UserInfo mUserInfo;
    private BaseUiListener mIUiListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;


    }

    @Override
    protected void initViews() {

        //微信
        api = WXAPIFactory.createWXAPI(this, WeiXinAPP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(WeiXinAPP_ID);
        //微博

        WbSdk.install(getApplicationContext(), new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE));

        //QQ
        mTencent = Tencent.createInstance(QQAPP_ID, LoginActivity.this.getApplicationContext());
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户登录");

        //数据库操作类
        userDao = new UserDao(this);
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
        etLoginPassword.setText("123456");
        etLoginPhone.setText("13116779507");
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
                } else if (!REGutil.checkCellphone(phone)) {
                    asyncShowToast("手机号格式不正确");
                } else {
                    doLogin(phone, password);
                    //                    final User user = userDao.dbQueryOneByUsername(phone);
                    //                    if (userDao.dbQueryOneByUsername(phone) == null) {
                    //                        Toast.makeText(mContext, "此用户不存在", Toast.LENGTH_SHORT).show();
                    //                    } else {
                    //                        if (!user.getPassword().equals(password)) {
                    //                            Toast.makeText(mContext, "密码错误", Toast.LENGTH_SHORT).show();
                    //                        } else {
                    //                            Toast.makeText(mContext, "登录成功", Toast.LENGTH_SHORT).show();
                    //                            toActivityWithResult(MainActivity.class, LOGIN_REQUEST);
                    //                    }
                    //                    }
                }

                break;
            case R.id.tv_login_regist:
             toActivity( RegistActivity.class);
                break;
            case R.id.tv_login_forget_password:
                toActivity(SeekPasswordActivity.class);
                break;
            case R.id.iv_wx_login:
                userText = tvLoginShowUsermessage.getText().toString().trim();
                loginWx();
                break;
            case R.id.iv_qq_login:
                loginQQ();
                break;
            case R.id.iv_sina_login:
                mSsoHandler.authorize(new SelfWbAuthListener());

                break;

        }
    }

    private void doLogin(String phone, String password) {


        OkGo.<String>get(Constant.LOGIN_URL).tag(TAG).cacheKey("cachePostKey").
                cacheMode(CacheMode.DEFAULT).
                params("mobile", phone).
                params("password", password).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        if (HttpStatusUtil.getStatus(response.body().toString())) {

                            Logger.d("onSuccess-->:" + response.body().toString());

                            try {
                                JSONObject jsonObject = new JSONObject(response.body().toString());
                                JSONObject  data = jsonObject.getJSONObject("data");
                                JSONObject accessTokenInfo = data.getJSONObject("accessTokenInfo");
                                String access_token = accessTokenInfo.getString("access_token");
                                SPUtils.saveString(mContext, SpConstant.ACCESS_TOKEN, access_token);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            asyncShowToast("登录成功");
                            finish();
                            return;
                        }
                        asyncShowToast(HttpStatusUtil.getStatusMsg(response.body().toString()));
                    }


                });

    }

    private void loginQQ() {
        /**通过这句代码，SDK实现了QQ的登录，这个方法有三个参数，第一个参数是context上下文，第二个参数SCOPO 是一个String类型的字符串，表示一些权限
         官方文档中的说明：应用需要获得哪些API的权限，由“，”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
         第三个参数，是一个事件监听器，IUiListener接口的实例，这里用的是该接口的实现类 */
        mIUiListener = new BaseUiListener();
        //all表示获取所有权限
        mTencent.login(LoginActivity.this, "all", mIUiListener);
    }

    private void loginWx() {
        if (MyApplication.api == null) {
            MyApplication.api = WXAPIFactory.createWXAPI(this, MyApplication.WeiXinAPP_ID, true);
        }
        if (!MyApplication.api.isWXAppInstalled()) {
            asyncShowToast("您手机尚未安装微信，请安装后再登录");
            return;
        }

        MyApplication.api.registerApp(MyApplication.WeiXinAPP_ID);
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

        } else if (requestCode == com.tencent.connect.common.Constants.REQUEST_LOGIN) {
            Tencent.onActivityResultData(requestCode, resultCode, data, mIUiListener);
        }
        super.onActivityResult(requestCode, resultCode, data);

    }

    class BaseUiListener implements IUiListener {

        private UserInfo mUserInfo;

        @Override
        public void onComplete(Object response) {
            asyncShowToast("授权成功");
            Log.i(TAG, "response" + response);
            JSONObject obj = (JSONObject) response;
            try {
                final String openID = obj.getString("openid");
                String accessToken = obj.getString("access_token");
                String expires = obj.getString("expires_in");
                mTencent.setOpenId(openID);
                mTencent.setAccessToken(accessToken, expires);
                QQToken qqToken = mTencent.getQQToken();
                mUserInfo = new UserInfo(getApplicationContext(), qqToken);
                mUserInfo.getUserInfo(new IUiListener() {
                    @Override
                    public void onComplete(Object o) {
                        asyncShowToast("登录成功");

                    }

                    @Override
                    public void onError(UiError uiError) {
                        asyncShowToast("登录失败");
                    }

                    @Override
                    public void onCancel() {
                        asyncShowToast("登录取消");

                    }
                });

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        @Override
        public void onError(UiError uiError) {
            asyncShowToast("授权失败");
        }

        @Override
        public void onCancel() {
            asyncShowToast("授权取消");

        }
    }

}

