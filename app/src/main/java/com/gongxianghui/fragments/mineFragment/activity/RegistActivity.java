package com.gongxianghui.fragments.mineFragment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gongxianghui.R;
import com.gongxianghui.base.BaseActivity;
import com.gongxianghui.bean.mine.UserBean;
import com.gongxianghui.db.StudentDao;
import com.gongxianghui.utils.REGutil;
import com.gongxianghui.utils.UserService;
import com.gongxianghui.widget.TitleBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.et_login_phone)
    EditText etLoginPhone;
    @BindView(R.id.iv_login_password)
    ImageView ivLoginPassword;
    @BindView(R.id.et_login_password)
    EditText etLoginPassword;
    @BindView(R.id.tv_login_forget_password)
    TextView tvLoginForgetPassword;
    @BindView(R.id.bt_login_login)
    Button btLoginLogin;
    @BindView(R.id.tv_login_quickly)
    TextView tvLoginQuickly;
    private StudentDao studentDao;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initViews() {
        new TitleBuilder(this).setLeftIco(R.mipmap.icon_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("用户注册");
        studentDao = new StudentDao(mContext);
    }

    @Override
    protected void initDatas() {
        tvLoginQuickly.setOnClickListener(this);
        btLoginLogin.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login_quickly:
                finish();

                break;

            case R.id.bt_login_login:
                String phone = etLoginPhone.getText().toString().trim();
                String pass = etLoginPassword.getText().toString().trim();
                studentDao.insert(phone, pass);
                Intent intent=new Intent(this,LoginActivity.class);
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(pass)) {
                    Toast.makeText(mContext, "手机号和密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!REGutil.checkCellphone(phone)) {
                    Toast.makeText(mContext, "手机格式错误了，请检查重试", Toast.LENGTH_SHORT).show();
                } else {
                    Log.i("TAG", phone + "_" + pass + "_");
//                    UserService uService = new UserService(mContext);

//                    UserBean userBean = new UserBean(phone, pass);
//                    userBean.setUsername(phone);
//                    userBean.setPassword(pass);
//                    studentDao.insert(userBean);
                    Toast.makeText(mContext, "注册成功", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }


                break;

        }
    }
}

