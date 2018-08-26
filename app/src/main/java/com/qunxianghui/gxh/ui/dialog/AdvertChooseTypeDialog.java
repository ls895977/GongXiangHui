package com.qunxianghui.gxh.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.qunxianghui.gxh.R;

import java.util.List;

public class AdvertChooseTypeDialog extends Dialog implements View.OnClickListener {

    private List<View> mViewList;
    private ViewPager mVp;
    private boolean mIsBigPageType;

    public Callback mCallback;

    public void SetCallback(Callback callback) {
        this.mCallback = callback;
    }

    public AdvertChooseTypeDialog(@NonNull Context context, List<View> viewList, ViewPager vp) {
        super(context, R.style.ActionSheetDialogStyle);
        this.mViewList = viewList;
        this.mVp = vp;
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setContentView(R.layout.ad_item_choose_type);
        Window window = getWindow();
        if (window != null) window.setGravity(Gravity.BOTTOM);
        android.view.WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        findViewById(R.id.tv_link).setOnClickListener(this);
        findViewById(R.id.tv_call).setOnClickListener(this);
        findViewById(R.id.tv_activity).setOnClickListener(this);
        findViewById(R.id.tv_poster).setOnClickListener(this);
        findViewById(R.id.tv_code).setOnClickListener(this);
    }

    public void setBigPageType() {
        if (!mIsBigPageType) {
            mIsBigPageType = true;
            ((TextView) findViewById(R.id.tv_activity)).setText("联系QQ");
            findViewById(R.id.tv_poster).setVisibility(View.GONE);
            findViewById(R.id.tv_code).setVisibility(View.GONE);
        }
    }

    public void setTongLangPageType() {
        if (mIsBigPageType) {
            mIsBigPageType = false;
            ((TextView) findViewById(R.id.tv_activity)).setText("跳转活动");
            findViewById(R.id.tv_poster).setVisibility(View.VISIBLE);
            findViewById(R.id.tv_code).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        dismiss();
        View contentView = mViewList.get(mVp.getCurrentItem());
        goneView(contentView);
        TextView mTvType = contentView.findViewById(R.id.tv_choose_type);
        int type = 0;
        switch (view.getId()) {
            case R.id.tv_link:
                type = 1;
                mTvType.setText("跳转链接");
                contentView.findViewById(R.id.rl_link).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_call:
                mTvType.setText("拨打电话");
                type = 2;
                if (contentView.findViewById(R.id.et_phone) != null) {
                    contentView.findViewById(R.id.et_phone).setVisibility(View.VISIBLE);
                } else {
                    contentView.findViewById(R.id.et_other).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_activity:
                type = 3;
                mTvType.setText(((TextView) findViewById(R.id.tv_activity)).getText().toString().trim());
                if (contentView.findViewById(R.id.tv_choose_activity_link) != null) {
                    contentView.findViewById(R.id.tv_choose_activity_link).setVisibility(View.VISIBLE);
                } else {
                    contentView.findViewById(R.id.et_other).setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_poster:
                type = 4;
                mTvType.setText("展示海报");
                contentView.findViewById(R.id.rl_add_img).setVisibility(View.VISIBLE);
                break;
            case R.id.tv_code:
                type = 5;
                mTvType.setText("展示二维码");
                contentView.findViewById(R.id.rl_add_img).setVisibility(View.VISIBLE);
                break;
        }
        if (mCallback != null) {
            mCallback.callback(type);
        }
    }

    private void goneView(View view) {
        view.findViewById(R.id.rl_link).setVisibility(View.GONE);
        if (view.findViewById(R.id.tv_choose_activity_link) != null) {
            view.findViewById(R.id.tv_choose_activity_link).setVisibility(View.GONE);
            view.findViewById(R.id.rl_add_img).setVisibility(View.GONE);
            view.findViewById(R.id.et_phone).setVisibility(View.GONE);
        } else {
            view.findViewById(R.id.et_other).setVisibility(View.GONE);
            view.findViewById(R.id.rl_link).setVisibility(View.GONE);
        }
    }

    public interface Callback {
        public void callback(int type);
    }

}
