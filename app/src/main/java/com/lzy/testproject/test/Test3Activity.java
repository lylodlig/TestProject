package com.lzy.testproject.test;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.lzy.testproject.R;
import com.lzy.testproject.other.reflect.RefInvoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test3Activity extends TestActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test3);

        Test test = (Test) RefInvoke.getFiledObject(TestActivity.class, this, "test");
//        Test test1 = (Test) Proxy.newProxyInstance(test.getClass().getClassLoader(), test.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                Log.i("lzy", "invoke: ");
//                return null;
//            }
//        });
//        RefInvoke.setFiledObject(TestActivity.class,this,"test",test1);
        Testt testt = new Testt(test);
        RefInvoke.setFiledObject(TestActivity.class,this,"test",testt);

    }

    public void bt(View view) {
        test();
//        startActivity(new Intent(this, TestActivity.class));
    }
}
