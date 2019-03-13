package com.lzy.testproject.test;

public class Singleton {
    private Singleton() {
    }

    public Singleton getInstance() {
        return SingletonHolder.singleton;
    }

    private static class SingletonHolder {
        private static final Singleton singleton = new Singleton();
    }
}
