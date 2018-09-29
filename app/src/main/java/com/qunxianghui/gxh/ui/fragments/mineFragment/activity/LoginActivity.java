package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.callback.JsonConvert;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.listener.NewTextWatcher;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.utils.UserUtil;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class LoginActivity extends BaseActivity {
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
    @BindView(R.id.iv_login_bick)
    ImageView ivLoginBick;

    private UMShareAPI mShareAPI;
    private UMAuthListener mUmAuthListener;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initData() {
        mShareAPI = UMShareAPI.get(this);
        //此回调用于三方登录回调
        mUmAuthListener = new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
                String url = null;
                switch (platform) {
                    case QQ:
                        url = Constant.QQ_RESPONSE_URL;
                        break;
                    case WEIXIN:
                        url = Constant.WEIXIN_RESPONSE_URL;
                        break;
                    case SINA:
                        url = Constant.SINA_RESPONSE_URL;
                        break;
                }
                PostRequest<String> params = OkGo.<String>post(url)
                        .params("status", true)
                        .params("accessToken", data.get("accessToken"));
                if (Constant.QQ_RESPONSE_URL.equals(url) || Constant.WEIXIN_RESPONSE_URL.equals(url)) {
                    params.params("openId", data.get("uid"));
                } else {
                    params.params("userId", data.get("uid"));
                }
                params.execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            JSONObject data = jsonObject.getJSONObject("data");
                            int code = jsonObject.getInt("code");
                            if (code == 0) {
                                JsonConvert.sIsShow = true;
                                String access_token = data.getJSONObject("accessTokenInfo").getString("access_token");
                                SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                                SPUtils.saveBoolean(SpConstant.IS_COMPANY, data.getInt("company_id") != 0);
                                OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                                if (TextUtils.isEmpty(data.getString("nick"))) {
                                    UserUtil.getInstance().mNick = "" + data.getString("mobile");
                                } else {
                                    UserUtil.getInstance().mNick = data.getString("nick");
                                }
                                asyncShowToast("登录成功");
                                toActivity(MainActivity.class);
                                finish();
                            } else if (code == 200) {
                                startActivity(new Intent(LoginActivity.this, BindMobileActivity.class).putExtra("connect_id", data.getInt("connect_id")));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(mContext, "登录取消", Toast.LENGTH_LONG).show();
            }
        };
    }

    @Override
    protected void initListeners() {
        etLoginPhone.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (etLoginPhone.getText().toString().trim().length() > 5 && etLoginPassword.getText().toString().trim().length() > 5) {
                    btLoginLogin.setEnabled(true);
                } else {
                    btLoginLogin.setEnabled(false);
                }
            }
        });
        etLoginPassword.addTextChangedListener(new NewTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (etLoginPhone.getText().toString().trim().length() == 11 && etLoginPassword.getText().toString().trim().length() > 5) {
                    btLoginLogin.setEnabled(true);
                } else {
                    btLoginLogin.setEnabled(false);
                }
            }
        });
    }

    @OnClick({R.id.bt_login_login, R.id.tv_login_regist, R.id.tv_login_forget_password, R.id.iv_wx_login, R.id.iv_qq_login, R.id.iv_sina_login, R.id.iv_login_bick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_login_login:
                String phone = etLoginPhone.getText().toString().trim();
                String password = etLoginPassword.getText().toString().trim();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    asyncShowToast("手机号和密码不能为空");
                } else if (!REGutil.checkCellphone(phone)) {
                    asyncShowToast("手机号格式不正确");
                } else {
                    doLogin(phone, password);
                }
                break;
            case R.id.tv_login_regist:
                toActivity(RegistActivity.class);
                break;
            case R.id.tv_login_forget_password:
                toActivity(SeekPasswordActivity.class);
                break;
            case R.id.iv_wx_login:
                if (!mShareAPI.isInstall(this, SHARE_MEDIA.WEIXIN)) {
                    Toast.makeText(this, "请先安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    mShareAPI.getPlatformInfo(this, SHARE_MEDIA.WEIXIN, mUmAuthListener);
                }
                break;
            case R.id.iv_qq_login:
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, mUmAuthListener);
                break;
            case R.id.iv_sina_login:
                mShareAPI.getPlatformInfo(this, SHARE_MEDIA.SINA, mUmAuthListener);
                break;
            case R.id.iv_login_bick:
                finish();
                break;
        }
    }

    private void doLogin(String phone, String password) {
        OkGo.<CommonResponse<LoginBean>>post(Constant.LOGIN_URL).
                params("mobile", phone).
                params("password", password).
                execute(new DialogCallback<CommonResponse<LoginBean>>(this) {
                    @Override
                    public void onSuccess(Response<CommonResponse<LoginBean>> response) {
                        if (response.body().code == 0) {
                            JsonConvert.sIsShow = true;
                            LoginBean userData = response.body().data;
                            String access_token = userData.getAccessTokenInfo().getAccess_token();
                            SPUtils.saveBoolean(SpConstant.IS_COMPANY, userData.getCompany_id() != 0);
                            SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                            if (TextUtils.isEmpty(userData.getNick())) {
                                UserUtil.getInstance().mNick = "" + userData.getMobile();
                            } else {
                                UserUtil.getInstance().mNick = userData.getNick();
                            }

                            if (userData.getCompany_info() != null) {
                                SharedPreferences.Editor editor = getSharedPreferences("companymessage", 0).edit();
                                editor.putString("selfcompanyname", userData.getCompany_info().getCompany_name());
                                editor.putString("expire_time", userData.getAccessTokenInfo().getExpires_time());
                                editor.putString("avatar", userData.getAvatar());
                                editor.apply();
                            }
                            OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                            holdReneraCompanyData();

                        } else {
                            asyncShowToast(response.body().message);
                        }
                    }
                });
    }

    private void holdReneraCompanyData() {
        getH5Url();
        asyncShowToast("登录成功");
        toActivity(MainActivity.class);
        finish();
    }

    private void getH5Url() {
        OkGo.<String>post(Constant.SHARE_COMPANY_CARD_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                JSONObject mCompanyCardData = jsonObject.getJSONObject("data");
                                String url = mCompanyCardData.getString("url");
                                SPUtils.getSp("companymessage").edit().putString("aboutus_showh5", url).apply();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}


