package com.lzy.testproject.framework.rx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.lzy.testproject.R;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observables.GroupedObservable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;

public class RxActivity extends AppCompatActivity {

    private String TAG = "lzy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

//        testCreate();
//        testJust();
//        testRange();
//        intervalRange();
//        countDown();
//        doOnEach();
//        timeInterval();
//        flatMap();
        groupBy();
    }

    private void testCreate() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
                e.onNext(4);
                e.onComplete();//上游可以继续发生，下游不继续接收
                e.onNext(5);
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(Integer value) {
                Log.i(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });
    }

    //Consume只关心next事件
    private void testJust() {
        Observable.just("Love", "For", "You!")
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "accept: " + s);
                    }
                });
    }

    //start:起始值；count：数量
    //Consume只关心next事件
    private void testRange() {
        Observable.range(5, 3)
                //输出 5,6,7
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "accept: " + integer);
                    }
                });
    }

    /**
     * start,count:同range
     * initialDelay 发送第一个值的延迟时间
     * period 每两个发射物的间隔时间
     * unit,scheduler 额你懂的
     */
    private void intervalRange() {
        Observable.intervalRange(5, 3, 1, 2, TimeUnit.SECONDS, Schedulers.io())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        Log.i(TAG, "accept: " + aLong);
                    }
                });
    }

    private void countDown() {
        final long time = 10;
        Observable.interval(1, TimeUnit.SECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return time - aLong;
                    }
                }).take(time + 1)//发生time+1前条数据
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        Log.i(TAG, "accept: 开始之前");
                    }
                }).subscribeWith(new DisposableObserver<Long>() {
            @Override
            public void onNext(Long value) {
                Log.i(TAG, "onNext: " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: ");
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });
    }


    private void doOnEach() {
        Observable.just(1, 2, 3, 4, 5)
                .doOnEach(new Consumer<Notification<Integer>>() {
                    @Override
                    public void accept(Notification<Integer> integerNotification) throws Exception {
                        Log.i(TAG, "doOnEach accept: " + integerNotification.isOnComplete() + "    " + integerNotification.getValue());
                    }
                })
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.i(TAG, "subscribe accept: " + integer);
                    }
                });
    }

    private void timeInterval() {
        Observable.just("time", "hello", "love")
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(new Consumer<Timed<String>>() {
                    @Override
                    public void accept(Timed<String> stringTimed) throws Exception {
                        Log.i(TAG, "accept: " + stringTimed);
                    }
                });
    }

    private void flatMap() {
        Observable.just(1, 2, 3, 4, 5)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer + "_love", integer + "_you");
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i(TAG, "accept: " + s);
                    }
                });
    }

    private void groupBy() {
        Observable.range(0, 10)
                .groupBy(new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer integer) throws Exception {
                        return integer % 3;//返回分组名
                    }
                })
                .subscribe(new Consumer<GroupedObservable<Integer, Integer>>() {
                    @Override
                    public void accept(GroupedObservable<Integer, Integer> integerIntegerGroupedObservable) throws Exception {
                        final Integer key = integerIntegerGroupedObservable.getKey();
                        integerIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) {
                                Log.i(TAG, "accept: " + key + "   " + integer);
                            }
                        });
                    }
                });
    }
}
