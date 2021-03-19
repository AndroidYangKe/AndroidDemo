package com.yangke.javatest.rxjava.subject;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.AsyncSubject;

/**
 * author : yangke on 2020-01-04
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   :
 * version:
 */
public class RxAsyncSubject {
    public static void main(String[] args) {
        /**
         * Observer会接收AsyncSubject的onComplete()之前的最后一个数据
         */
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.onNext("--------------asyncSubject1");
        subject.onNext("--------------asyncSubject2");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("--------------asyncSubject: " + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("--------------asyncSubject onError");
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("--------------asyncSubject: complete");
            }
        });

        subject.onNext("--------------asyncSubject3");
        subject.onNext("--------------asyncSubject4");
        subject.onComplete();//调用此函数才会发送数据，否则观察者将不接收任何数据。
    }
}
