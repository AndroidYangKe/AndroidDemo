package com.yangke.javatest.rxjava.subject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * author : yangke on 2020-01-04
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   :
 * version:
 */
public class RxPublishSubject {
    public static void main(String[] args) {
        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("publishSubject1");
        subject.onNext("publishSubject2");

        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("publishSubject: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("publishSubject: accept");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("publishSubject: run");
            }
        });

        subject.onNext("publishSubject3");
        subject.onNext("publishSubject4");
        subject.onComplete();
    }
}
