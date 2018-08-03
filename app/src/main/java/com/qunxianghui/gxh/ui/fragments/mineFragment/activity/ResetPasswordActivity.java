package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.widget.TitleBuilder;

import org.json.JSONObject;

import butterknife.BindView;

public class ResetPasswordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_resetpassword_newpassword)
    EditText etResetpasswordNewpassword;
    @BindView(R.id.et_resetpassword_conform_new_passworm)
    EditText etResetpasswordConformNewPassworm;
    @BindView(R.id.bt_resetpassword_conform_password)
    Button btResetpasswordConformPassword;
    private String mobile;
    private String vertifiCode;
    private String password;
    private String confirmPassword;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reset_password;
    }

    @Override
    protected void initData() {
        new TitleBuilder(ResetPasswordActivity.this).setLeftIco(R.mipmap.common_black_back).setLeftIcoListening(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setTitleText("密码找回");
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        vertifiCode = intent.getStringExtra("captcha");
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        btResetpasswordConformPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_resetpassword_conform_password:
                SeekPasswordCommit();
                break;
        }
    }

    private void SeekPasswordCommit() {
        password = etResetpasswordNewpassword.getText().toString().trim();
        confirmPassword = etResetpasswordConformNewPassworm.getText().toString().trim();
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            asyncShowToast("密码和确认密码不能为空");
        } else if (!password.equals(confirmPassword) ) {
            asyncShowToast("两次输入的密码不一致");
        } else {
            CommitSeekPassword(password);
        }
    }
    private void CommitSeekPassword(String password) {
        OkGo.<String>post(Constant.SEEK_PASSWORD_URL)
                .params("password", password)
                .params("mobile", mobile)
                .params("captcha", vertifiCode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response.body());
                            final int code = jsonObject.getInt("code");
                            if (code==0){
                                com.orhanobut.logger.Logger.e("----------修改成功" + response.toString());
                                toActivity(LoginActivity.class);
                            }else {
                                asyncShowToast("修改失败");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
