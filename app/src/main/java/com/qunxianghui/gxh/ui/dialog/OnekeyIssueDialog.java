package com.qunxianghui.gxh.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.ui.activity.PublishActivity;
import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.BaoLiaoActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CheckBoxActivity;
import com.qunxianghui.gxh.ui.fragments.mineFragment.activity.CompanySetActivity;
import com.qunxianghui.gxh.utils.FastBlurUtility;

public class OnekeyIssueDialog extends Dialog {

    private Activity mActicity;

    public OnekeyIssueDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        mActicity = ((Activity) context);
        View view = LayoutInflater.from(context).inflate(R.layout.pop_onekey_issue, null);
        View issueVideo = view.findViewById(R.id.rl_iv_onekey_issue_video);
        View issueLocalcircle = view.findViewById(R.id.rl_onekey_issue_localcircle);
        View issueBaoliao = view.findViewById(R.id.rl_onekey_issue_baoliao);
        View issueLocalService = view.findViewById(R.id.rl_onekey_issue_localservice);
        View issueChoiceness = view.findViewById(R.id.rl_onekey_issue_choiceness);
        ImageView issueClose = view.findViewById(R.id.iv_onekey_issue_close);
        RelativeLayout popLl = view.findViewById(R.id.pop_ll);
        LinearLayout popBlock = view.findViewById(R.id.pop_ll_block);
        popLl.setBackground(new BitmapDrawable(FastBlurUtility.getBlurBackgroundDrawer(((Activity) context))));
        setContentView(view);
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rl_iv_onekey_issue_video:
                        FitchVideo();
                        break;
                    case R.id.rl_onekey_issue_localcircle:
                        startActivity(PublishActivity.class);
                        break;
                    case R.id.rl_onekey_issue_baoliao:
                        startActivity(BaoLiaoActivity.class);
                        break;
                    case R.id.rl_onekey_issue_localservice:
                        startActivity(CompanySetActivity.class);
                        break;
                    case R.id.rl_onekey_issue_choiceness:
////                        toActivity(GuidActivity.class);
//                        toActivity(GuidSlideActivity.class);

                        startActivity(CheckBoxActivity.class);
                        return;
                }
                dismiss();
            }
        };
        issueVideo.setOnClickListener(listener);
        issueLocalcircle.setOnClickListener(listener);
        issueBaoliao.setOnClickListener(listener);
        issueVideo.setOnClickListener(listener);
        issueLocalService.setOnClickListener(listener);
        issueChoiceness.setOnClickListener(listener);
        issueClose.setOnClickListener(listener);
        popBlock.setOnClickListener(listener);
        //获取当前activity所在的窗体
        final Window dialogWindow = getWindow();
        //设置dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        final WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        final WindowManager windowManager = ((Activity) context).getWindowManager();
        final Display display = windowManager.getDefaultDisplay();
//        lp.alpha = 0.9f;
        lp.width = display.getWidth();  //设置宽度
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;//设置dialog高度
        lp.y = 3;  //设置dialog距离底部的距离
        dialogWindow.setFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
        //将属性设置给窗体
        dialogWindow.setAttributes(lp);
    }

    /*获取系统的视频和录像*/
    private void FitchVideo() {
        PictureSelector.create(mActicity)
                .openGallery(PictureMimeType.ofVideo())
                .selectionMode(PictureConfig.SINGLE)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    private void startActivity(Class clazz) {
        mActicity.startActivity(new Intent(mActicity, clazz));
    }
}
