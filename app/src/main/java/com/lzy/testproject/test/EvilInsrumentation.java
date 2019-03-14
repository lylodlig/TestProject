package com.lzy.testproject.test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.UserHandle;
import android.util.Log;

import com.lzy.testproject.other.reflect.RefInvoke;

public class EvilInsrumentation extends Instrumentation {

    Instrumentation mBase;

    public EvilInsrumentation(Instrumentation mBase) {
        this.mBase = mBase;
    }


    public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, String resultWho,
            Intent intent, int requestCode, Bundle options, UserHandle user){
        Log.i("lzy", "execStartActivity: ---------------------------------------------Hook");
        Class[] p = {Context.class, IBinder.class, IBinder.class, String.class, Intent.class, int.class, Bundle.class, UserHandle.class};
        Object[] v = {who, contextThread, token, resultWho, intent, requestCode, options, user};
        return (Instrumentation.ActivityResult) RefInvoke.invokeInstanceMethod(mBase, "execStartActivity", p, v);
    }
}
