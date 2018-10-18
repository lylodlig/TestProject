package com.lzy.testproject.ui.loading;

import android.content.Context;
import android.support.annotation.Nullable;

import com.lzy.testproject.ui.loading.callback.OnRetryClickListener;
import com.lzy.testproject.ui.loading.view.IStatusView;


/**
 * Created by dgg on 2017/11/7.
 */

public interface StatusViewCreator {
	IStatusView onCreateStatusView(Context context, Status status, @Nullable OnRetryClickListener l);
}
