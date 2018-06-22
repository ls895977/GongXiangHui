package com.qunxianghui.gxh.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.activity.MainActivity;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.base.MyApplication;
import com.qunxianghui.gxh.bean.LzyResponse;
import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.StudentDao;
import com.qunxianghui.gxh.db.UserDao;

import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;



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



    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;


    }

    @Override
    protected void initViews() {






        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toActivity(MainActivity.class);
                finish();
            }
        }).setTitleText("用户登录");

        //数据库操作类
        userDao = new UserDao(this);
    }

    @Override
    protected void initDatas() {


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

                break;
            case R.id.iv_qq_login:

                break;
            case R.id.iv_sina_login:
                mSsoHandler.authorize(new SelfWbAuthListener());
                break;
        }
    }

    private void doLogin(String phone, String password) {
        OkGo.<LzyResponse<LoginBean>>get(Constant.LOGIN_URL).tag(TAG).cacheKey("cachePostKey").
                cacheMode(CacheMode.DEFAULT).
                params("mobile", phone).
                params("password", password).
                execute(new DialogCallback<LzyResponse<LoginBean>>(this) {
                    @Override
                    public void onSuccess(Response<LzyResponse<LoginBean>> response) {
                        if (response.body().code.equals("0")) {
                            String access_token=response.body().data.getAccessTokenInfo().getAccess_token();
                            SPUtils.saveString(mContext, SpConstant.ACCESS_TOKEN, access_token);
                            SPUtils.saveBoolean(mContext, SpConstant.IS_COMPANY, response.body().data.getCompany_id() != 0);
                            MyApplication.getApp().setAccessToken(access_token);
                            Log.e(TAG, "onSuccess: "+access_token);
                            asyncShowToast("登录成功");
                            toActivity(MainActivity.class);
                            finish();
                        }else {
                            asyncShowToast(response.body().message);
                        }


                    }
                });
//                execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//
//                        if (HttpStatusUtil.getStatus(response.body().toString())) {
//
//                            Logger.d("onSuccess-->:" + response.body().toString());
//
//                            try {
//                                JSONObject jsonObject = new JSONObject(response.body().toString());
//                                JSONObject  data = jsonObject.getJSONObject("data");
//                                JSONObject accessTokenInfo = data.getJSONObject("accessTokenInfo");
//                                String access_token = accessTokenInfo.getString("access_token");
//                                SPUtils.saveString(mContext, SpConstant.ACCESS_TOKEN, access_token);
//                                MyApplication.getApp().setAccessToken(access_token);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//
//                            asyncShowToast("登录成功");
//                            toActivity(MainActivity.class);
//                            finish();
//                            return;
//                        }
//                        asyncShowToast(HttpStatusUtil.getStatusMsg(response.body().toString()));
//                    }
//
//
//                });

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
        super.onActivityResult(requestCode, resultCode, data);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            toActivity(MainActivity.class);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

