package com.yangke.javatest.rxjava.subject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;

/**
 * author : yangke on 2020-01-04
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   :
 * version:
 */
public class RxBehaviorSubject {
    public static void main(String[] args) {
        /**
         * Observer会先接收到BehaviorSubject被订阅之前的最后一个数据，再接收订阅之后发射过来的数据。如果
         * BehaviorSubject被订阅之前没有发送任何数据，则会发送一个默认数据。
         */
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("behaviorSubject1");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("behaviorSubject: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("behaviorSubject: onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("behaviorSubject: run");
            }
        });

        subject.onNext("behaviorSubject2");
        subject.onNext("behaviorSubject3");
    }
}
