package com.lzy.testproject.framework.rx.rxbus;


import androidx.annotation.NonNull;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by LiZhiyu on 2018/10/24.
 * <p>
 * 1、Subject同时充当了Observer和Observable的角色，Subject是非线程安全的，要避免该问题，需要将 Subject转换为一个 SerializedSubject
 * ，上述RxBus类中把线程非安全的PublishSubject包装成线程安全的Subject。
 * <p>
 * PublishSubject只有在订阅后才接受处理消息，之前发送的消息不会到达接收者
 * <p>
 * ofType操作符只发射指定类型的数据，使用filter过滤
 * <p>
 */
public class RxBus {
    private final Subject<Object> mBus;

    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    private RxBus() {
        mBus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static RxBus INSTANCE = new RxBus();
    }

    public void post(@NonNull Object obj) {
        mBus.onNext(obj);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable(Class<T> eventType) {
        return mBus.ofType(eventType);
    }

}
