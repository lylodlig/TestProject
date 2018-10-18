package com.lzy.testproject.ui.loading.change;

import android.support.annotation.NonNull;

import com.lzy.testproject.ui.loading.view.IStatusView;


/**
 * Created by leeiides on 2017/6/23.
 */

public interface SwitchLayoutHelper {
    void switchLayout(@NonNull IStatusView targetView);

    void removeAllViews();

    @NonNull
    IStatusView getCurrentStatusView();
}
