package com.lzy.testproject.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.lzy.testproject.other.reflect.RefInvoke;

public class EvilInsrumentation extends Instrumentation {

    Instrumentation mBase;

    public EvilInsrumentation(Instrumentation mBase) {
        this.mBase = mBase;
    }

    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, String target,
            Intent intent, int requestCode, Bundle options) {
        Log.i("lzy", "execStartActivity: Hook");
        Class[] p = {Context.class, IBinder.class, IBinder.class, String.class, Intent.class, int.class, Bundle.class};
        Object[] v = {who, contextThread, token, target, intent, requestCode, options};
        return (ActivityResult) RefInvoke.invokeInstanceMethod(mBase, "execStartActivity", p, v);
    }
}
