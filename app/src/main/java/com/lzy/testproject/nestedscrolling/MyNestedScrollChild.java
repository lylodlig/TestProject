package com.lzy.testproject.nestedscrolling;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by lzy on 2018/6/19.
 */
public class MyNestedScrollChild extends LinearLayout implements NestedScrollingChild {
	private NestedScrollingChildHelper mNestedScrollingChildHelper;

	public MyNestedScrollChild(Context context) {
		super(context);
	}

	public MyNestedScrollChild(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
	}

	//初始化helper对象
	private NestedScrollingChildHelper getScrollingChildHelper() {
		if (mNestedScrollingChildHelper == null) {
			mNestedScrollingChildHelper = new NestedScrollingChildHelper(this);
			mNestedScrollingChildHelper.setNestedScrollingEnabled(true);
		}
		return mNestedScrollingChildHelper;
	}

	//实现一下接口
	@Override
	public void setNestedScrollingEnabled(boolean enabled) {
		getScrollingChildHelper().setNestedScrollingEnabled(enabled);
	}

	@Override
	public boolean isNestedScrollingEnabled() {
		return getScrollingChildHelper().isNestedScrollingEnabled();
	}

	@Override
	public boolean startNestedScroll(int axes) {
		return getScrollingChildHelper().startNestedScroll(axes);
	}

	@Override
	public void stopNestedScroll() {
		getScrollingChildHelper().stopNestedScroll();
	}

	@Override
	public boolean hasNestedScrollingParent() {
		return getScrollingChildHelper().hasNestedScrollingParent();
	}

	@Override
	public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
		return getScrollingChildHelper().dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
	}

	@Override
	public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
		return getScrollingChildHelper().dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
	}

	@Override
	public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
		return getScrollingChildHelper().dispatchNestedFling(velocityX, velocityY, consumed);
	}

	@Override
	public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
		return getScrollingChildHelper().dispatchNestedPreFling(velocityX, velocityY);
	}
}
