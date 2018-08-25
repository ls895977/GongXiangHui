package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.OnClick;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.CommonResponse;
import com.qunxianghui.gxh.bean.mine.LoginBean;
import com.qunxianghui.gxh.callback.DialogCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.db.StudentDao;
import com.qunxianghui.gxh.db.UserDao;
import com.qunxianghui.gxh.ui.activity.MainActivity;
import com.qunxianghui.gxh.utils.HttpStatusUtil;
import com.qunxianghui.gxh.utils.REGutil;
import com.qunxianghui.gxh.utils.SPUtils;
import com.qunxianghui.gxh.widget.TitleBuilder;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


/**
 * Created by Administrator on 2018/3/10 0010.
 */

public class LoginActivity extends BaseActivity {

    public static final int LOGIN_REQUEST = 1;
    public static final int LOGIN_RESULT = 1;

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
    private UMShareAPI mShareAPI;
    private UMAuthListener mUmAuthListener;
    private UMShareListener umShareListener;
    private StudentDao studentDao;
    private String phone;
    private String password;
    private UserDao userDao;
    private IWXAPI mWxApi;
    private String openId;
    /*1为QQ,2为微信*/
    private String thirdType;
    private String companyName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户登录");
        //数据库操作类
        userDao = new UserDao(this);
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
                for (Map.Entry<String, String> entry : data.entrySet()) {
                    Log.d(TAG, "onComplete: " + entry.getKey() + ":" + entry.getValue());
                }
                switch (platform) {
                    case QQ:
                        /**
                         * qq登录完成后的回掉
                         */
                        OkGo.<String>post(Constant.QQ_RESPONSE_URL)
                                .params("status", true)
                                .params("accessToken", data.get("accessToken"))
                                .params("openId", data.get("uid"))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body());
                                            JSONObject data = jsonObject.getJSONObject("data");
                                            int code = jsonObject.getInt("code");
                                            if (code == 0) {
                                                String access_token = data.getJSONObject("accessTokenInfo").getString("access_token");
                                                SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                                                SPUtils.saveBoolean(SpConstant.IS_COMPANY, data.getInt("company_id") != 0);
                                                OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                                                Log.e(TAG, "onSuccess: " + access_token);
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
                        break;
                    case WEIXIN:
                        /**
                         * weixin登录完成后的回掉
                         */
                        OkGo.<String>post(Constant.WEIXIN_RESPONSE_URL)
                                .params("status", true)
                                .params("accessToken", data.get("accessToken"))
                                .params("openId", data.get("uid"))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body());
                                            JSONObject data = jsonObject.getJSONObject("data");
                                            int code = jsonObject.getInt("code");
                                            if (code == 0) {
                                                String access_token = data.getJSONObject("accessTokenInfo").getString("access_token");
                                                SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                                                SPUtils.saveBoolean(SpConstant.IS_COMPANY, data.getInt("company_id") != 0);
                                                OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                                                Log.e(TAG, "onSuccess: " + access_token);
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

                        break;
                    case SINA:
                        /**
                         * sina登录完成后的回掉
                         */
                        OkGo.<String>post(Constant.SINA_RESPONSE_URL)
                                .params("status", true)
                                .params("accessToken", data.get("accessToken"))
                                .params("openId", data.get("uid"))
                                .execute(new StringCallback() {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response.body());
                                            JSONObject data = jsonObject.getJSONObject("data");
                                            int code = jsonObject.getInt("code");
                                            if (code == 0) {
                                                String access_token = data.getJSONObject("accessTokenInfo").getString("access_token");
                                                SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                                                SPUtils.saveBoolean(SpConstant.IS_COMPANY, data.getInt("company_id") != 0);
                                                OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
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
                        break;
                }
                Toast.makeText(mContext, "成功了", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//                Log.w("test", "性别: " + data.get("gender"));
            }

            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(mContext, "取消了", Toast.LENGTH_LONG).show();
            }
        };

        //此回调用于分享
        umShareListener = new UMShareListener() {
            @Override
            public void onStart(SHARE_MEDIA platform) {
                //分享开始的回调
            }

            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(LoginActivity.this, platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(LoginActivity.this, platform + " 分享取消了", Toast.LENGTH_SHORT).show();
            }
        };

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
                            String access_token = response.body().data.getAccessTokenInfo().getAccess_token();
                            SPUtils.saveString(SpConstant.ACCESS_TOKEN, access_token);
                            SPUtils.saveBoolean(SpConstant.IS_COMPANY, response.body().data.getCompany_id() != 0);
                            OkGo.getInstance().getCommonHeaders().put("X-accesstoken", access_token);
                            fillUserData();
                            holdReneraCompanyData();
                        }
                    }

                    @Override
                    public void onError(Response<CommonResponse<LoginBean>> response) {
                        super.onError(response);
                        asyncShowToast("用户名或密码错误！");
                    }
                });
    }

    private void holdReneraCompanyData() {
        OkGo.<String>post(Constant.GENERALIZE_COMPANY_STATICS_URL).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                if (response.body() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        JSONObject data = jsonObject.getJSONObject("data");
                        int staff_cnt = data.getInt("staff_cnt");
                        String avatar = data.getString("avatar");
                        SharedPreferences spCompanymessage = getSharedPreferences("companymessage", Context.MODE_PRIVATE);
                        SharedPreferences.Editor spCompanymessageEditor = spCompanymessage.edit();
                        spCompanymessageEditor.putInt("staff_cnt", staff_cnt);
                        spCompanymessageEditor.apply();
                    } catch (JSONException ignored) {

                    }
                }
                asyncShowToast("登录成功");
                toActivity(MainActivity.class);
                finish();
            }
        });
    }

    /*获取个人资料*/
    private void fillUserData() {
        OkGo.<String>post(Constant.CATCH_USERDATA_URL).
                execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        if (HttpStatusUtil.getStatus(response.body())) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.body());
                                JSONObject data = jsonObject.getJSONObject("data");
                                int code = jsonObject.getInt("code");
                                if (code == 0) {
                                    companyName = data.getJSONObject("company_info").getString("company_name");
                                    String expire_time = data.getString("expire_time");
                                    String avatar = data.getString("avatar");
                                    /**
                                     * 保存自己的公司名称
                                     */
                                    SharedPreferences spConpanyname = getSharedPreferences("companymessage", 0);
                                    SharedPreferences.Editor editor = spConpanyname.edit();
                                    editor.putString("selfcompanyname", companyName);
                                    editor.putString("expire_time", expire_time);
                                    editor.putString("avatar", avatar);
                                    editor.apply();
                                }

                            } catch (Exception ignored) {

                            }
                        }
                    }
                });
        OkGo.<String>post(Constant.SHARE_COMPANY_CARD_URL)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.body());
                            int code = jsonObject.getInt("code");
                            if (code == 200) {
                                JSONObject mCompanyCardData = jsonObject.getJSONObject("data");
//                                String avatar = mCompanyCardData.getString("avatar");
//                                String title = mCompanyCardData.getString("title");
//                                String content = mCompanyCardData.getString("content");
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


