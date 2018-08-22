package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.MemberActiviteBean;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.utils.GsonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberUpActiveActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_member_active_code)
    EditText etMemberActiveCode;
    @BindView(R.id.tv_member_activite_quickly)
    TextView tvMemberActiviteQuickly;
    @BindView(R.id.iv_memberup_activite_back)
    ImageView ivMemberupActiviteBack;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_active;
    }

    @Override
    protected void initData() {
        super.initData();


    }

    @Override
    protected void initListeners() {
        ivMemberupActiviteBack.setOnClickListener(this);
        tvMemberActiviteQuickly.setOnClickListener(this);
    }

    //激活码激活
    private void ActiviteData(String activeCode) {
        OkGo.<String>post(Constant.PERSON_UPGRADE_URL)
                .params("activecode", activeCode)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

//                        try {
//                            JSONObject jsonObject = new JSONObject(response.body());
//                            int code = jsonObject.getInt("code");
//                            String msg = jsonObject.getString("msg");
//                            if (code == 0) {
//                                asyncShowToast(msg);
//
//                                finish();
//                            } else if (code == 101) {
//                                asyncShowToast("序列号不存在");
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }


                        ParseActivieData(response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

    private void ParseActivieData(String body) {
        MemberActiviteBean memberActiviteBean = GsonUtils.jsonFromJson(body, MemberActiviteBean.class);
        
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

            case R.id.iv_memberup_activite_back:
                finish();
                break;
            case R.id.tv_member_activite_quickly:
                String activeCode = etMemberActiveCode.getText().toString().trim();
                if (TextUtils.isEmpty(activeCode)) {

                    asyncShowToast("激活码为空");
                } else {
                    ActiviteData(activeCode);
                }
                break;
        }
    }
}
