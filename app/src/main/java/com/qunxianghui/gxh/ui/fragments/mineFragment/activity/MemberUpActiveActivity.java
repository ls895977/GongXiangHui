package com.qunxianghui.gxh.ui.fragments.mineFragment.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.base.BaseActivity;
import com.qunxianghui.gxh.bean.mine.MemberActiviteBean;
import com.qunxianghui.gxh.callback.JsonCallback;
import com.qunxianghui.gxh.config.Constant;
import com.qunxianghui.gxh.config.SpConstant;
import com.qunxianghui.gxh.observer.EventManager;
import com.qunxianghui.gxh.utils.SPUtils;

import butterknife.BindView;

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
    protected void initListeners() {
        ivMemberupActiviteBack.setOnClickListener(this);
        tvMemberActiviteQuickly.setOnClickListener(this);
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
                    activityData(activeCode);
                }
                break;
        }
    }

    //激活码激活
    private void activityData(String activeCode) {
        OkGo.<MemberActiviteBean>post(Constant.PERSON_UPGRADE_URL)
                .params("activecode", activeCode)
                .execute(new JsonCallback<MemberActiviteBean>() {
                    @Override
                    public void onSuccess(Response<MemberActiviteBean> response) {
                        parseActivieData(response.body());
                    }

                    @Override
                    public void onError(Response<MemberActiviteBean> response) {
                        super.onError(response);
                    }
                });
    }

    private void parseActivieData(MemberActiviteBean memberActiviteBean) {
        int code = memberActiviteBean.getCode();
        String msg = memberActiviteBean.getMsg();
        MemberActiviteBean.DataBean memberData = memberActiviteBean.getData();
        asyncShowToast(msg);
        if (code == 0) {
            String avatar = memberData.getAvatar();
            int company_id = memberData.getCompany_id();
            String code_endtime = memberData.getCode_endtime();
            SPUtils.saveBoolean(SpConstant.IS_COMPANY, true);
            SharedPreferences companyData = getSharedPreferences("companymessage", Context.MODE_PRIVATE);
            SharedPreferences.Editor spCompanymessageEditor = companyData.edit();
            spCompanymessageEditor.putString("member_expire_time", code_endtime);
            spCompanymessageEditor.putString("avatar", avatar);
            spCompanymessageEditor.apply();
            EventManager.getInstance().publishMessage("company");
            setResult(0x0022);
            finish();
        }
    }

}
