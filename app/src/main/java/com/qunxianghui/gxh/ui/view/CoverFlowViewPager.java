package com.qunxianghui.gxh.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.qunxianghui.gxh.R;
import com.qunxianghui.gxh.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class CoverFlowViewPager extends RelativeLayout implements ViewPager.OnPageChangeListener{
    private CoverFlowAdapter mAdapter;
    /**
     * 用于左右滚动
     */
    private ViewPager mViewPager;
    private List<View> mViewList = new ArrayList<>();
    private Context mContext;
    private OnCoverPageSelectListener listener;

    private int sWidthPadding;
    private int sHeightPadding;

    public CoverFlowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.widget_cover_flow, this);
        mViewPager = (ViewPager) findViewById(R.id.vp_conver_flow);
        init();
    }

    private void init() {
        mAdapter = new CoverFlowAdapter(mViewList, getContext());
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(this);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 传递给ViewPager 进行滑动处理
                return mViewPager.dispatchTouchEvent(event);
            }
        });
    }

    public void setViewList(List<View> lists) {
        if (lists == null) {
            return;
        }

        sWidthPadding = ScreenUtils.dp2px(12);
        sHeightPadding = ScreenUtils.dp2px(12);

        mViewList.clear();
        for (View view : lists) {
            FrameLayout layout = new FrameLayout(getContext());
            // 设置padding 值，默认缩小        layout.setPadding(CoverFlowAdapter.sWidthPadding,CoverFlowAdapter.sHeightPadding,CoverFlowAdapter.sWidthPadding,CoverFlowAdapter.sHeightPadding);
            layout.addView(view);
            mViewList.add(layout);
        }
        // 刷新数据
        mAdapter.notifyDataSetChanged();
    }

    public void setOnPageSelectListener(OnCoverPageSelectListener listener) {
        this.listener = listener;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        // 该方法回调ViewPager 的滑动偏移量
        if (mViewList.size() > 0 && position < mViewList.size()) {
            //当前手指触摸滑动的页面,从0页滑动到1页 offset越来越大，padding越来越大
            int outHeightPadding = (int) (positionOffset * sHeightPadding);
            int outWidthPadding = (int) (positionOffset * sWidthPadding);
            // 从0滑动到一时，此时position = 0，其应该是缩小的，符合
            mViewList.get(position).setPadding(outWidthPadding, outHeightPadding, outWidthPadding, outHeightPadding);
            // position+1 为即将显示的页面，越来越大
            if (position < mViewList.size() - 1) {
                int inWidthPadding = (int) ((1 - positionOffset) * sWidthPadding);
                int inHeightPadding = (int) ((1 - positionOffset) * sHeightPadding);
                mViewList.get(position + 1).setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
                if(position != 0){
                    mViewList.get(0).setPadding(inWidthPadding, inHeightPadding, inWidthPadding, inHeightPadding);
                }
            }
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (listener != null) {
            listener.select(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    OnCoverPageSelectListener onPageSelectListener;

    public void setCurrentItem(int i) {
        mViewPager.setCurrentItem(1,true);
    }
    public int getCurrentItemIndex() {
        return mViewPager.getCurrentItem();
    }


    public interface OnCoverPageSelectListener{
        void select(int pos);
    }

    public class CoverFlowAdapter extends PagerAdapter{
        private Context context;
        List<View> mData;
        public CoverFlowAdapter(List<View> viewList, Context context) {
            this.context = context;
            mData = viewList;
        }

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(mData.get(position));
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mData.get(position));
            return mData.get(position);
        }
    }
}
