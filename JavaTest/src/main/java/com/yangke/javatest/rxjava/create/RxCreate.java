package com.yangke.javatest.rxjava.create;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * author : yangke on 2020-01-04
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   : 创建操作符
 * version:
 */
public class RxCreate {
    public static void main(String[] args) {

        /**
         * just创建一个或多个对象转换成发射这个或这些对象的一个Observable。
         * =========================================================================================
         */
//        Observable.just("hello just1")
//        Observable.just("hello just1", "hello just2", "hello just3", "hello just6", "hello just5")
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });

        /**
         * from操作符可以将Future、Iterable和数组转换成Observable。对于Iterable和数组，产生的Observable会
         * 发射Iterable或数组的每一项数据。
         * =========================================================================================
         */
//        Observable.fromArray("hello", "from")
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });
//        List<Integer> items = new ArrayList<>();
//        for(int i=0; i<10; i++) {
//            items.add(i);
//        }
//        Observable.fromIterable(items)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * repeat创建一个发射特定数据重复多次的Observable。
         * =========================================================================================
         */
//        Observable.just("hello repeat")
//                .repeat(3)
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });

        /**
         * repeatWhen不是缓存和重放原始数据Observable的数据序列，而是有条件地重新订阅和发射原来的Observable。
         * =========================================================================================
         */
//        Observable.range(0, 9).repeatWhen(new Function<Observable<Object>, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(Observable<Object> objectObservable) throws Exception {
//                return Observable.timer(3, TimeUnit.SECONDS);
//            }
//        }).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                System.out.println("accept-->"+integer);
//            }
//        });try { Thread.sleep(12000); } catch (InterruptedException e) { e.printStackTrace(); }

        /**
         * interval创建一个按照时间间隔发射整数序列的Observable。
         * =========================================================================================
         */
//        Observable.interval(2, TimeUnit.SECONDS)
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println(aLong);
//                    }
//                });try { Thread.sleep(12000); } catch (InterruptedException e) { e.printStackTrace(); }

        /**
         * timer创建一个Observable，它在一个给定的延迟后发射一个特殊的值。
         * =========================================================================================
         */
        Observable.timer(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println(aLong);
                    }
                });
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
