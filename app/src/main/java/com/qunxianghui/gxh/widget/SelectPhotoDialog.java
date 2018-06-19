package com.qunxianghui.gxh.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.qunxianghui.gxh.R;

/**
 * Created by hasee on 2018/6/16.
 */

public class SelectPhotoDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private SelectPhotoListener selectPhotoListener;

    public SelectPhotoDialog(@NonNull Context context,SelectPhotoListener selectPhotoListener) {
        super(context, R.style.DIALOG_THEME);
        this.context = context;
        this.selectPhotoListener = selectPhotoListener;
        init(context);
    }

    public SelectPhotoDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected SelectPhotoDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    private void init(Context context) {
        int top = getWindow().getDecorView().getTop();
        int bottom = getWindow().getDecorView().getBottom();
        getWindow().getDecorView().setPadding(0, top, 0, bottom);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);
        getWindow().setGravity(Gravity.BOTTOM);

        setCancelable(true);

        View view = getLayoutInflater().inflate(R.layout.dialog_select_photo, null);

        view.findViewById(R.id.tv_take_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_slect_photo).setOnClickListener(this);
        view.findViewById(R.id.tv_cancel_select).setOnClickListener(this);

        setContentView(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:
                selectPhotoListener.onTakePhoto();
                break;
            case R.id.tv_slect_photo:
                selectPhotoListener.onSelect();
                break;
            case R.id.tv_cancel_select:
                selectPhotoListener.onDismiss();
                break;
        }
    }

    public interface SelectPhotoListener{
        void onTakePhoto();
        void onSelect();
        void onDismiss();
    }
}
