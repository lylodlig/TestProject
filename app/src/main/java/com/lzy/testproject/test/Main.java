package com.lzy.testproject.test;

import android.util.Log;

public class Main {

    public static void main(String[] args) {

    }


}

class Child extends Parent {
    @Override
    public void test() {
        super.test();
    }
    
}

class Parent {
    public static void method() {
        Log.i("lzy", "Parent ");
    }

    public void test() {

    }
}