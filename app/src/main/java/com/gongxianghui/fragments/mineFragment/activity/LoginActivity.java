package com.gongxianghui.fragments.mineFragment.activity;

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

import com.gongxianghui.R;
import com.gongxianghui.activity.MainActivity;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.db.StudentDao;
import com.gongxianghui.utils.REGutil;
import com.gongxianghui.utils.UserService;
import com.gongxianghui.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    private StudentDao studentDao;
    private String phone;
    private String password;

    @Override
    protected int getLayoutId() {

        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
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
                break;
            case R.id.iv_qq_login:
                break;
            case R.id.iv_sina_login:
                break;

        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case LOGIN_REQUEST:
//                if (resultCode == LOGIN_RESULT) {
//
//                }
//                break;
//
//        }
//    }
}

