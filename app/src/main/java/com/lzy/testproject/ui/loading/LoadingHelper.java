package com.lzy.testproject.ui.loading;

import android.view.View;

import com.lzy.testproject.R;
import com.lzy.testproject.ui.loading.callback.OnRetryClickListener;
import com.lzy.testproject.ui.loading.change.Mode;
import com.lzy.testproject.ui.loading.change.impl.SwitchLayoutHelperFactory;
import com.lzy.testproject.ui.loading.view.IStatusView;
import com.lzy.testproject.ui.loading.view.OriginView;


/**
 * Created by dgg on 2017/11/7.
 */

public abstract class LoadingHelper {
    public static LoadingHelper with(View originView) {
        return with(originView, Mode.REPLACE);
    }

    public static LoadingHelper with(View originView, Mode mode) {
        LoadingHelper helper = (LoadingHelper) originView.getTag(R.id.status_layout_origin_helper);
        if (helper == null) {
            OriginView defaultOriginView = new OriginView(originView);

            StatusViewCreator defaultStatusViewCreator = LoadingHelperViewCreator.getDefaultStatusViewCreator();

            if (defaultStatusViewCreator == null) {
                throw new IllegalStateException("必须要先使用 LoadingHelperViewCreator.setDefaultStatusViewCreator");
            }

            helper = new LoadingHelperImpl(defaultOriginView,
                    SwitchLayoutHelperFactory.getSwitchLayoutHelper(mode, defaultOriginView), defaultStatusViewCreator);
        }

        return helper;
    }

    /**
     * 显示加载中布局
     */
    public void showLoading() {
        showLoading(null);
    }

    public abstract void showLoading(String message);

    /**
     * 显示空布局
     */
    public void showEmpty() {
        showEmpty(null);
    }

    public void showEmpty(String message) {
        showEmpty(message, null);
    }

    public abstract void showEmpty(String message, String button);

    /**
     * 显示错误布局
     */
    public void showError() {
        showError(null);
    }

    public void showError(String message) {
        showError(message, null);
    }

    public abstract void showError(String message, String button);

    /**
     * 复原布局
     */
    public abstract void restore();

    /**
     * 显示无网布局
     */
    public void showNoNetwork() {
        showNoNetwork(null);
    }

    public void showNoNetwork(String message) {
        showNoNetwork(message, null);
    }

    public abstract void showNoNetwork(String message, String button);

    /**
     * 移除所有View
     */
    public abstract void removeAllView();

    public abstract void setOnRetryClickListener(OnRetryClickListener l);

    /**
     * 加入自定义布局
     *
     * @param view view
     */
    public abstract void putCustomView(IStatusView view);

    public void showCustom() {
        showCustom(null);
    }

    public void showCustom(String message) {
        showCustom(message, null);
    }

    public abstract void showCustom(String message, String button);

    public abstract void show(Status status, String message, String button);
}
