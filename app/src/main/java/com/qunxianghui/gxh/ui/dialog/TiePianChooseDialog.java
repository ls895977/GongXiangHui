package com.qunxianghui.gxh.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

public class TiePianChooseDialog extends Dialog implements View.OnClickListener {

    private ChooseTimeListener mChooseTimeListener;
    private String[] mTimes = new String[]{"3s", "4s", "5s"};
    private String[] mTypes = new String[]{"跳转链接", "拨打电话", "联系QQ"};

    public void setChooseTimeListener(ChooseTimeListener chooseTimeListener) {
        this.mChooseTimeListener = chooseTimeListener;
    }

    public TiePianChooseDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(R.layout.ad_item_choose_time_type);
        Window window = getWindow();
        if (window != null) window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        findViewById(R.id.tv_3).setOnClickListener(this);
        findViewById(R.id.tv_4).setOnClickListener(this);
        findViewById(R.id.tv_5).setOnClickListener(this);
    }

    public TiePianChooseDialog setIsChooseTime(boolean isChooseTime) {
        if (isChooseTime) {
            ((TextView) findViewById(R.id.tv_3)).setText(mTimes[0]);
            ((TextView) findViewById(R.id.tv_4)).setText(mTimes[1]);
            ((TextView) findViewById(R.id.tv_5)).setText(mTimes[2]);
        } else {
            ((TextView) findViewById(R.id.tv_3)).setText(mTypes[0]);
            ((TextView) findViewById(R.id.tv_4)).setText(mTypes[1]);
            ((TextView) findViewById(R.id.tv_5)).setText(mTypes[2]);
        }
        return this;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        mChooseTimeListener.chooseTime(view);
    }

    public interface ChooseTimeListener {
        void chooseTime(View view);
    }

}
