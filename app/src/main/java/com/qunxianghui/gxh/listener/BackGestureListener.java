package com.qunxianghui.gxh.listener;

import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;

import com.qunxianghui.gxh.ui.fragments.homeFragment.activity.GestureDetectorActivity;


/**
 * 返回手势监听接口
 */
public class BackGestureListener implements OnGestureListener {

	GestureDetectorActivity activity;
	
	public BackGestureListener(GestureDetectorActivity activity) {
		this.activity = activity; 
	}

	@Override
	public boolean onDown(MotionEvent e) {
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {

	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
		if ((e2.getX() - e1.getX()) >100 && Math.abs(e1.getY() - e2.getY()) < 60 ) {
			activity.onBackPressed();
			return true;
		}
		return false;
	}

	@Override
	public void onShowPress(MotionEvent e) {
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}

}
