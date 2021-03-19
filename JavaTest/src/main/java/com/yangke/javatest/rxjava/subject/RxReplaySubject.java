package com.yangke.javatest.rxjava.subject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.ReplaySubject;

/**
 * author : yangke on 2020-01-04
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   :
 * version:
 */
public class RxReplaySubject {
    public static void main(String[] args) {

        /**
         * ReplaySubject会发射所有来自原始Observable的数据给观察者，无论它们是何时订阅的。
         */
        ReplaySubject<String> subject = ReplaySubject.create();
//        ReplaySubject<String> subject = ReplaySubject.createWithSize(1);
        subject.onNext("replaySubject1");
        subject.onNext("replaySubject2");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("replaySubject: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("replaySubject: accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("replaySubject: run");
            }
        });

        subject.onNext("replaySubject3");
        subject.onNext("replaySubject4");
//        subject.onNext("replaySubject5");

    }
}
