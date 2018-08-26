package com.qunxianghui.gxh.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class KeyboardLayout extends RelativeLayout {


    public KeyboardLayout(Context context) {
        super(context);
    }

    public KeyboardLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KeyboardLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private OnResizeListener onResizeListener;

    public interface OnResizeListener {
        void OnResize(int w, int h, int oldw, int oldh);
    }


    public void setOnResizeListener(OnResizeListener _listener) {
        this.onResizeListener = _listener;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (onResizeListener != null) {
            onResizeListener.OnResize(w, h, oldw, oldh);
        }
    }
}