package com.qunxianghui.gxh.ui.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.adapter.GuidViewPagerAdapter;
import com.qunxianghui.gxh.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 2018/7/22.
 */

public class GuidActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @BindView(R.id.viewpager_guid)
    ViewPager viewpagerGuid;
    @BindView(R.id.layout_guidpoint)
    LinearLayout layoutGuidpoint;
    @BindView(R.id.framlayout_guidpoint)
    FrameLayout framlayoutGuidpoint;
    @BindView(R.id.bt_guid_start)
    Button btGuidStart;

    private List<View> list_view = new ArrayList<>();
    private List<ImageView> list_pointView = new ArrayList<ImageView>();
    private GuidViewPagerAdapter guidViewPagerAdapter;
    private ImageView colorPoint;
    private LinearLayout.LayoutParams lpColorPoint;
    private int pointSpacing;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_guid;
    }

    @Override
    protected void initViews() {
        initGuidView();
    }

    private void initGuidView() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        list_view.add(inflater.inflate(R.layout.guid_one, null));
        list_view.add(inflater.inflate(R.layout.guid_two, null));
        list_view.add(inflater.inflate(R.layout.guid_three, null));
        guidViewPagerAdapter = new GuidViewPagerAdapter(list_view);
        viewpagerGuid.setAdapter(guidViewPagerAdapter);

        for (int i = 0; i < list_view.size(); i++) {
            ImageView point = new ImageView(this);
            //设置暗点
            point.setBackgroundResource(R.mipmap.home_video_location);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(10, 0, 10, 0);
            point.setLayoutParams(lp);
            list_pointView.add(point);
            layoutGuidpoint.addView(point);

            //添加选中的引导点
            colorPoint = new ImageView(this);
            //设置两点
            colorPoint.setBackgroundResource(R.mipmap.icon_close);
            lpColorPoint = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            colorPoint.setLayoutParams(lpColorPoint);
            framlayoutGuidpoint.addView(colorPoint);
            framlayoutGuidpoint.post(new Runnable() {
                @Override
                public void run() {
                    //待布局绘制完毕  设置选中白点 的初始化位置
                    FrameLayout.LayoutParams framLayoutParams = (FrameLayout.LayoutParams) colorPoint.getLayoutParams();
                    framLayoutParams.leftMargin = list_pointView.get(0).getLeft();
                    colorPoint.setLayoutParams(framLayoutParams);

                }
            });
            layoutGuidpoint.post(new Runnable() {
                @Override
                public void run() {
                    // 获取引导的之间的间隔
                    pointSpacing = layoutGuidpoint.getChildAt(1).getLeft() - layoutGuidpoint.getChildAt(0).getLeft();
                }
            });
        }
    }

    @Override
    protected void initListeners() {
        super.initListeners();
        viewpagerGuid.setOnPageChangeListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        FrameLayout.LayoutParams framLayoutParams = (FrameLayout.LayoutParams) colorPoint.getLayoutParams();
        //根据滑动动态设置左边距
        framLayoutParams.leftMargin= (int) (list_pointView.get(position).getLeft()+pointSpacing*positionOffset);
        colorPoint.setLayoutParams(framLayoutParams);
    }

    @Override
    public void onPageSelected(int position) {
        //根据Pager位置设置btn的显示
        if (position==list_view.size()-1){
            btGuidStart.setVisibility(View.VISIBLE);
        }else {
            btGuidStart.setVisibility(View.GONE);
        }

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
