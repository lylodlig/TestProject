package com.lzy.testproject.test;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import com.blankj.utilcode.util.DeviceUtils;
import com.leeiidesu.permission.PermissionHelper;
import com.leeiidesu.permission.callback.OnPermissionResultListener;
import com.lzy.testproject.R;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestActivity extends AppCompatActivity {

    private TelephonyManager mTelephonyManager;
    private SubscriptionManager mSubscriptionManager;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);
        Log.i("lzy", "onCreate: " + DeviceUtils.getManufacturer());

        printTelephonyManagerMethodNamesForThisDevice();
        getSubscriberId();

        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (mTelephonyManager == null) {
            throw new Error("telephony manager is null");
        }
//        mConnMngr = (ConnectivityManager).getSystemService(Context.CONNECTIVITY_SERVICE);
        mSubscriptionManager = SubscriptionManager.from(this);

    }


    public void bt(View view) {
//        getPhoneNumber();
        String msisdn = getMsisdn(0);
        String msisdn1 = getMsisdn(1);
        getLine1NumberForSubscriber(0);
        getLine1NumberForSubscriber(1);
    }

    private String getPhoneNumber() {
        PermissionHelper.with(this)
                .permissions(Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_PHONE_NUMBERS)
                .listener(new OnPermissionResultListener() {
                    @SuppressLint("MissingPermission")
                    @Override
                    public void onGranted() {
                        TelephonyManager mTelephonyMgr;
                        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//                        String line1Number1 = mTelephonyMgr.getLine1Number(1);
//                        String line1Number = mTelephonyMgr.getLine1Number();
//                        Log.i("lzy", "onGranted: "+line1Number1);
                    }

                    @Override
                    public void onFailed(ArrayList<String> deniedPermissions) {

                    }
                }).request();
        return "";
    }

    // 遍历 TelephonyManager 里的方法
    public void printTelephonyManagerMethodNamesForThisDevice() {
        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method[] methods = telephonyClass.getMethods();
            for (int i = 0; i < methods.length; i++) {
                Log.i("lzy", "\n" + methods[i] + " declared by " + methods[i].getDeclaringClass());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取双卡双待 SIM 卡序列号
    public void getSubscriberId() {
        TelephonyManager telephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Class<?> telephonyClass;
        Object result = null;
        Object result0 = null;
        Object result1 = null;
        try {
            telephonyClass = Class.forName(telephony.getClass().getName());
            Method m1 = telephonyClass.getMethod("getSubscriberId");
            Method m4 = telephonyClass.getMethod("getSubId");
            Method m2 = telephonyClass.getMethod("getSubscriberId", new Class[]{int.class});
            Method m3 = telephonyClass.getMethod("getLine1Number", int.class);
            m3.setAccessible(true);
            m4.setAccessible(true);
            result = m1.invoke(telephony);
            result0 = m2.invoke(telephony, 0);
            result1 = m2.invoke(telephony, 1);
            Object s = m3.invoke(telephony, m4.invoke(telephony));
            Object s1 = m3.invoke(telephony, 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String getMsisdn(int slotId) {//slotId 0为卡1 ，1为卡2
        return getLine1NumberForSubscriber(getSubIdForSlotId(slotId));
    }


    private int getSubIdForSlotId(int slotId) {
        int[] subIds = getSubId(slotId);
        if (subIds == null || subIds.length < 1 || subIds[0] < 0) {
            return -1;
        }
        Log.i("lzy", "getSubIdForSlotId: " + subIds[0]);
        return subIds[0];
    }

    private int[] getSubId(int slotId) {
        Method declaredMethod;
        int[] subArr = null;
        try {
            declaredMethod = Class.forName("android.telephony.SubscriptionManager").getDeclaredMethod("getSubId", new Class[]{Integer.TYPE});
            declaredMethod.setAccessible(true);
            subArr = (int[]) declaredMethod.invoke(mSubscriptionManager, slotId);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            declaredMethod = null;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            declaredMethod = null;
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
            declaredMethod = null;
        } catch (ClassCastException e4) {
            e4.printStackTrace();
            declaredMethod = null;
        } catch (IllegalAccessException e5) {
            e5.printStackTrace();
            declaredMethod = null;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            declaredMethod = null;
        }
        if (declaredMethod == null) {
            subArr = null;
        }
        Log.i("lzy", "getSubId: " + subArr[0]);
        return subArr;
    }

    private String getLine1NumberForSubscriber(int subId) {
        Method method;
        String status = null;
        try {
            method = mTelephonyManager.getClass().getMethod("getLine1Number", int.class);
            method.setAccessible(true);
            status = String.valueOf(method.invoke(mTelephonyManager, subId));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        Log.i("lzy", "dff " + status);
        return status;
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lzy", "onStop: ");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lzy", "onStart: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lzy", "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lzy", "onPause: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lzy", "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lzy", "onResume: ");
    }
}