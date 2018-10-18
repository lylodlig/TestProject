package com.lzy.testproject.ui.loading.change.impl;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lzy.testproject.ui.loading.change.SwitchLayoutHelper;
import com.lzy.testproject.ui.loading.view.IStatusView;


/**
 *
 * Created by leeiides on 2017/6/23.
 */

class CoverSwitchLayoutHelper implements SwitchLayoutHelper {

    private View mOriginView;

    private ViewGroup mParentView;

    private int indexOfView;

    private IStatusView mCurrentView;

    private ViewGroup.LayoutParams originParams;

    private FrameLayout mFrameLayout;


    CoverSwitchLayoutHelper(@NonNull IStatusView view) {
        mOriginView = view.getView();

        originParams = mOriginView.getLayoutParams();

        if (mOriginView.getParent() == null) {
            mParentView = (ViewGroup) mOriginView.getRootView().findViewById(android.R.id.content);
        } else {
            mParentView = (ViewGroup) mOriginView.getParent();
        }

        indexOfView = mParentView.indexOfChild(mOriginView);

        mCurrentView = view;

        mFrameLayout = new FrameLayout(mOriginView.getContext());
        mParentView.removeView(mCurrentView.getView());
        mFrameLayout.addView(mCurrentView.getView(), originParams);

        mParentView.addView(mFrameLayout, indexOfView, originParams);
    }

    @Override
    public synchronized void switchLayout(@NonNull IStatusView targetView) {
        if (mCurrentView == targetView) return;

        if (mFrameLayout.getChildCount() == 2) {
            mFrameLayout.removeViewAt(1);
        }

        if (targetView != mOriginView) mFrameLayout.addView(targetView.getView(), 1, originParams);

        mCurrentView = targetView;

    }

    @Override
    public void removeAllViews() {
        mFrameLayout.removeView(mOriginView);
        mParentView.removeView(mFrameLayout);
        mParentView.addView(mOriginView, indexOfView, originParams);

        mFrameLayout = null;
        mParentView = null;
        mCurrentView = null;
        mOriginView = null;
        originParams = null;
    }

    @NonNull
    @Override
    public IStatusView getCurrentStatusView() {
        return mCurrentView;
    }
}
