package com.qunxianghui.gxh.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

import com.qunxianghui.gxh.R;

@SuppressLint("AppCompatCustomView")
public class ClearEditText extends EditText {

    private Drawable mClearImg;

    public ClearEditText(Context context) {
        super(context);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mClearImg = ContextCompat.getDrawable(getContext(), R.mipmap.icon_clear);

//        clearImg.setTint(context.resources.getColor(R.color.colorAccent))
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s)) {
                    //给textView上下左右四个方向添加drawable
                    setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(null, null, mClearImg, null);
                }
            }
        });
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            boolean isClean = event.getX() > getWidth() - getPaddingRight() && event.getX() < getWidth() - getPaddingRight();
//            if (isClean) {
//                setText("");
//            }
//        }
//        return super.onTouchEvent(event);
//    }
}
