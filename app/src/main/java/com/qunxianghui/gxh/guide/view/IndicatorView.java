package com.qunxianghui.gxh.guide.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.qunxianghui.gxh.R;

public class IndicatorView extends FrameLayout {
    private static final String TAG = IndicatorView.class.getName();
    private int mInitialMarginLeft;
    private int mIntervalMargins;

    private ImageView ivCurrentIndicator;

    public IndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        View view = LayoutInflater.from(context).inflate(R.layout.guide_indicator_view, this);
        ivCurrentIndicator = (ImageView) view.findViewById(R.id.guide_indicator_iv_current);

        mInitialMarginLeft = ((MarginLayoutParams) ivCurrentIndicator.getLayoutParams()).leftMargin;
        mIntervalMargins = dpToPx(30);
    }

    public void setCurrentPosition(int position) {
        setCurrentPosition(position, 0);
    }

    public void setCurrentPosition(int position, float positionOffset) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) ivCurrentIndicator.getLayoutParams();
        marginLayoutParams.leftMargin = (int) (mInitialMarginLeft + mIntervalMargins * (position + positionOffset));
        ivCurrentIndicator.requestLayout();

        if (position > 1) {
            setAlpha(0);
            return;
        }
        if (position == 1) {
            setAlpha(1 - positionOffset);
            return;
        }
        setAlpha(1);
    }

    public void bindViewPager(ViewPager viewPager) {
        viewPager.addOnPageChangeListener(new UpdateIndicatorPositionListener());
    }

    private int dpToPx(float dps) {
        return Math.round(getContext().getResources().getDisplayMetrics().density * dps);
    }

    private class UpdateIndicatorPositionListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.d(TAG, "onPageScrolled() position: " + position + " positionOffset: " + positionOffset + " positionOffsetPixels: " + positionOffsetPixels);
            setCurrentPosition(position, positionOffset);
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    }
}
