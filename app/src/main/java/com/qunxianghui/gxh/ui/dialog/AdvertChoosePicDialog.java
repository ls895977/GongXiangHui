package com.qunxianghui.gxh.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qunxianghui.gxh.R;

public class AdvertChoosePicDialog extends Dialog implements View.OnClickListener {

    private ImgPickListener mImgPickListener;

    public void setImgPickListener(ImgPickListener imgPickListener) {
        this.mImgPickListener = imgPickListener;
    }

    public AdvertChoosePicDialog(@NonNull Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(R.layout.ad_pic_view_pick);
        Window window = getWindow();
        if (window != null) window.setGravity(Gravity.BOTTOM);
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        findViewById(R.id.btnPhoto).setOnClickListener(this);
        findViewById(R.id.btnPick).setOnClickListener(this);
        findViewById(R.id.btnPicFromLocal).setOnClickListener(this);
        findViewById(R.id.btnCommon).setOnClickListener(this);
        findViewById(R.id.btnCancel).setOnClickListener(this);
    }

    public AdvertChoosePicDialog showLocalView(){
        findViewById(R.id.btnPicFromLocal).setVisibility(View.VISIBLE);
        return this;
    }

    public AdvertChoosePicDialog hideLocalView(){
        findViewById(R.id.btnPicFromLocal).setVisibility(View.GONE);
        return this;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        mImgPickListener.pickListener(view);
    }

    public interface ImgPickListener {
        void pickListener(View view);
    }

}
