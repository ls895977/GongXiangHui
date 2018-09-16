package com.qunxianghui.gxh.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.config.LoginMsgHelper;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.LoginActivity;

public class LoginDialog extends Dialog {

    private static LoginDialog mInstance;

    public static LoginDialog getInstance(@NonNull final Context context) {
        if (mInstance == null) {
            synchronized (LoginDialog.class) {
                if (mInstance == null) {
                    mInstance = new LoginDialog(context);
                }
            }
        }
        return mInstance;
    }

    public void setContent(String content) {
        LoginMsgHelper.exitLogin();
        if (!TextUtils.isEmpty(content))
            ((TextView) findViewById(R.id.tv_title)).setText(content);
        if (!mInstance.isShowing()) {
            mInstance.show();
        }
    }

    private LoginDialog(@NonNull final Context context) {
        super(context, R.style.LOGIN_DIALOG_THEME);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        setContentView(R.layout.dialog_login);
        Window window = getWindow();
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);

        findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        findViewById(R.id.tv_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                context.startActivity(new Intent(context, LoginActivity.class));
            }
        });
    }
}
