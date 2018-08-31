package com.qunxianghui.gxh.ui.activity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.ImageAdapter;
import com.qunxianghui.gxh.utils.SavePicByUrlUtils;
import com.qunxianghui.gxh.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

public class PhotoBrowserActivity extends AppCompatActivity implements ImageAdapter.OnItemClick {
    private PhotoViewPager mViewPager;
    private int currentPosition;
    private ImageAdapter adapter;
    private List<String> Urls = new ArrayList<>();
    private Dialog mDialog;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo_browser);
        initViews();
        initDatas();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    protected void initViews() {
        mViewPager = findViewById(R.id.view_pager_photo);
        mViewPager.setBackgroundColor(Color.parseColor("#000000"));
        Urls = this.getIntent().getStringArrayListExtra("url");
        currentPosition = this.getIntent().getIntExtra("position", 0);
        adapter = new ImageAdapter(Urls, this);
        mViewPager.setAdapter(adapter);
        adapter.setOnItemClick(this);
        mViewPager.setCurrentItem(currentPosition, false);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
            }
        });
    }

    protected void initDatas() {
    }

    @Override
    public void finish() {
        super.finish();
        this.overridePendingTransition(0, R.anim.activity_pop_out);
    }


    /*图片长按的处理*/
    @Override
    public void PicLongClick(int position, String url) {
        showBottomDialog(position, url);
    }

    /*弹出底部弹出框*/
    private void showBottomDialog(final int position, final String url) {
        if (mDialog == null) {
            mDialog = new Dialog(PhotoBrowserActivity.this, R.style.ActionSheetDialogStyle);
            //填充对话框的布局
            View alertView = LayoutInflater.from(this).inflate(R.layout.bottom_piccancel_dialog, null);
            //初始化控件
            alertView.findViewById(R.id.tv_savepicto_colume).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SavePicByUrlUtils.getBitmap(PhotoBrowserActivity.this, url);
                        }
                    }).start();
                    mDialog.dismiss();
                }
            });
            alertView.findViewById(R.id.tv_bottom_alertdialog_cancle).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                }
            });
            //将布局设置给dialog
            mDialog.setContentView(alertView);
            //获取当前activity所在的窗体
            Window dialogWindow = mDialog.getWindow();
            //设置dialog从窗体底部弹出
            dialogWindow.setGravity(Gravity.BOTTOM);
            //获得窗体的属性
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            WindowManager windowManager = getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            lp.width = (int) display.getWidth();  //设置宽度
            lp.y = 5;  //设置dialog距离底部的距离
            //将属性设置给窗体
            dialogWindow.setAttributes(lp);
        }
        mDialog.show();
    }
}
