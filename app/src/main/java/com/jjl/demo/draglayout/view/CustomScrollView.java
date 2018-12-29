package com.jjl.demo.draglayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

public class CustomScrollView extends ScrollView {
	private GestureDetector mGestureDetector;
	private boolean isScrolledToTop;
	private boolean isScrolledToBottom;
	private float topY;
	private boolean mAllowDragOnBottom;
	private boolean mAllowDragOnTop;

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mGestureDetector = new GestureDetector(new YScrollDetector());
		mGestureDetector.setIsLongpressEnabled(false);
		setFadingEdgeLength(0);
	}

	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return super.onInterceptTouchEvent(ev)
				&& mGestureDetector.onTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
			switch (ev.getAction()) {
				case MotionEvent.ACTION_DOWN:
					topY = ev.getY();
					break;
				case MotionEvent.ACTION_MOVE:
					if(mAllowDragOnTop){
						if(isScrolledToTop  && ev.getY() - topY > 0){
							getParent().requestDisallowInterceptTouchEvent(false);
						}else{
							getParent().requestDisallowInterceptTouchEvent(true);
						}
					}
					if(mAllowDragOnBottom){
						if(isScrolledToBottom  && ev.getY() - topY < 0){
							getParent().requestDisallowInterceptTouchEvent(false);
						}else{
							getParent().requestDisallowInterceptTouchEvent(true);
						}
					}
					break;
			}
		return super.onTouchEvent(ev);
	}

	@Override
	protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
		super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
		//api >= 9
		if (scrollY == 0) {
			isScrolledToTop = clampedY;
			isScrolledToBottom = false;
		} else {
			isScrolledToTop = false;
			isScrolledToBottom = clampedY;
		}
	}

	public void setAllowDragOnBottom(boolean allowDragOnBottom) {
		mAllowDragOnBottom = allowDragOnBottom;
	}

	public void setAllowDragOnTop(boolean allowDragOnTop) {
		mAllowDragOnTop = allowDragOnTop;
	}

	// Return false if we're scrolling in the x direction
	class YScrollDetector extends SimpleOnGestureListener {
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
                                float distanceX, float distanceY) {
			if (Math.abs(distanceY) > Math.abs(distanceX)) {
				return true;
			}
			return false;
		}
	}

}
