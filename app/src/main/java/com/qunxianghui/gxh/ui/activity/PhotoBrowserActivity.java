package com.qunxianghui.gxh.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.locationAdapter.ImageAdapter;
import com.qunxianghui.gxh.widget.PhotoViewPager;

import java.util.ArrayList;
import java.util.List;

public class PhotoBrowserActivity extends AppCompatActivity implements View.OnClickListener{
    private PhotoViewPager mViewPager;
    private int currentPosition ;
    private ImageAdapter adapter;
    //private TextView mTvImageCount;
    //private TextView mTvSaveImage;
    private List<String> Urls = new ArrayList<>();

    @Override
    public void onWindowFocusChanged(boolean hasFocus)
    {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_photo_browser);
        initViews();
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        getSupportActionBar().hide();
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onCreate(savedInstanceState, persistentState);
//        setContentView(R.layout.activity_photo_browser);
//        initViews();
//
//    }

    protected void initViews() {
        mViewPager = (PhotoViewPager) findViewById(R.id.view_pager_photo);
        mViewPager.setBackgroundColor(Color.parseColor("#000000"));
        //mTvSaveImage.setOnClickListener(this);
        Urls = this.getIntent().getStringArrayListExtra("url");
        currentPosition = this.getIntent().getIntExtra("position",0);
        //currentPosition = currentPosition + 1;

        adapter = new ImageAdapter(Urls, this);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(currentPosition, false);
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentPosition = position;
                //mTvImageCount.setText(currentPosition + 1 + "/" + Urls.size());
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

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_save_image_photo:
//                //save image
//                break;
//        }
    }
}
