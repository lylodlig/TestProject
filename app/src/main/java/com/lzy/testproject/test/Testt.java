package com.lzy.testproject.test;

import android.util.Log;
import android.view.ViewGroup;

import com.lzy.testproject.other.reflect.RefInvoke;

/**
 * Created by LiZhiyu on 2019/3/14.
 */
public class Testt extends Test {

    Test test;

    public Testt(Test test) {
        this.test = test;
    }

    @Override
    public void tess() {
        Log.i("lzy", "tess: Hook");
        RefInvoke.invokeInstanceMethod(test, "tess");
    }

}
