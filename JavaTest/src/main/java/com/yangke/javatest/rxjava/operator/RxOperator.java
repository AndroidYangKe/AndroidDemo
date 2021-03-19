package com.yangke.javatest.rxjava.operator;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * author : yangke on 2020-01-05
 * weChat : ACE_5200125
 * email  : 211yangke@gmail.com
 * desc   : 操作符demo
 * 变换操作符和过滤操作符
 * 小结：变换操作符的作用是对被观察者发射的数据按照一定的规则做一些变换操作，然后将变换后的数据发射出去。过滤操作符
 * 的作用是过滤掉被观察者发射的一些数据，不让它们发射出去，也就是忽略并丢弃。至于需要过滤那些数据，则需要按照不同的
 * 规则来进行。
 * version:
 */
public class RxOperator {
    public static void main(String[] args) {
        /**
         * map操作符，对Observable发射的每一项数据应用一个函数，执行变换操作。
         * -----------------------------------------------------------------------------------------
         */
        Observable.just("HELLO")
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s.toLowerCase();
                    }
                })
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s + " world";
                    }
                })
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String o) throws Exception {
                        System.out.println(o);
                    }
                });

        /**
         * flatmap操作符，将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个
         * 单独的Observable。
         * -----------------------------------------------------------------------------------------
         */
//        class Address {
//            String street;
//            String city;
//        }
//        class User {
//            String userName;
//            List<Address> address;
//        }
//
//        User user = new User();
//        user.userName = "yangke";
//        user.address = new ArrayList<>();
//        Address address = new Address();
//        address.street = "四环路";
//        address.city = "北京";
//        user.address.add(address);
//
//        Address address2 = new Address();
//        address2.street = "五环路";
//        address2.city = "北京";
//        user.address.add(address2);
//
//        Observable.just(user)
//                .flatMap(new Function<User, ObservableSource<Address>>() {
//                    @Override
//                    public ObservableSource<Address> apply(User user) throws Exception {
//                        return Observable.fromIterable(user.address);
//                    }
//                })
//                .subscribe(new Consumer<Address>() {
//                    @Override
//                    public void accept(Address address) throws Exception {
//                        System.out.println(address.street);
//                    }
//                });

        /**
         * groupBy操作符将一个Observable拆分为一些Observables集合，它们中的每一个都发射原始Observable的一个
         * 子序列。
         * 那些数据项由哪一个Observable发射是由一个函数决定的，这个函数给每一项指定一个Key，Key相同的数据会被同
         * 一个Observable发射。
         */
//        Observable.range(1, 8).groupBy(new Function<Integer, String>() {
//            @Override
//            public String apply(Integer integer) throws Exception {
//                return (integer % 2 == 0) ? "偶数组" : "奇数组";
//            }
//        }).subscribe(new Consumer<GroupedObservable<String, Integer>>() {
//            @Override
//            public void accept(final GroupedObservable<String, Integer> stringIntegerGroupedObservable) throws Exception {
//                if(stringIntegerGroupedObservable.getKey().equalsIgnoreCase("奇数组")) {
//                    stringIntegerGroupedObservable.subscribe(new Consumer<Integer>() {
//                        @Override
//                        public void accept(Integer integer) throws Exception {
//                            System.out.println(stringIntegerGroupedObservable.getKey() + ": " + integer);
//                        }
//                    });
//                }
//            }
//        });

        /**
         * buffer操作符会定期收集Observable数据并放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值。
         * buffer操作符将一个Observable变换为另一个，原来的Observable正常发射数据，由变换来产生的Observable
         * 发射这些数据的缓存集合。
         */
//        Observable.range(1, 10)
//                .buffer(4)
//                .subscribe(new Consumer<List<Integer>>() {
//                    @Override
//                    public void accept(List<Integer> integers) throws Exception {
//                        System.out.println("onNext: " + integers);
//                    }
//                });

        /**
         * window操作符，定期将来自原始Observable的数据分解为一个Observable窗口，发射这些窗口，而不是每次发射
         * 一项数据。
         * window发射的不是原始Observable的数据包，而是Observables，这些Observables中的每一个都发射原始
         * Observable数据的一个子集，走后发射一个onComplete通知。
         */
//        Observable.range(1, 10)
//                .window(4)
//                .subscribe(new Consumer<Observable<Integer>>() {
//                    @Override
//                    public void accept(Observable<Integer> integerObservable) throws Exception {
//                        System.out.println("onNext:");
//                        integerObservable.subscribe(new Consumer<Integer>() {
//                            @Override
//                            public void accept(Integer integer) throws Exception {
//                                System.out.println("accept:" + integer);
//                            }
//                        });
//                    }
//                });

        /**
         * first操作符，只发射第一项（或满足某个条件的第一项）数据。
         * 如果只对Observable发射的第一项数据，或者满足某个条件的第一项数据感兴趣，那么就可以使用first操作符。
         */
//        Observable
////                .<Integer>empty()
//                .just(1, 2, 3)
//                .first(2)//如果发送的数据为空defaultItem才会起作用
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept： "+integer);
//                    }
//                });

        /**
         * last操作符，只发射最后一项（或者满足某个条件的最后一项）数据。
         */
//        Observable
//                .<Integer>empty()
////                .just(1, 2, 3)
//                .last(2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept: "+integer);
//                    }
//                });

        /**
         * take操作符，只发射前面的n项数据。使用take操作符可以只修改Observable的行为，返回前面的n项数据，发射
         * 完成通知，忽略剩余的数据。takeLast取后n项数据。
         */
//        Observable.just(1, 2, 3, 4)
//                .take(2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept:" + integer);
//                    }
//                });

        /**
         * skip操作符，抑制Observable发射的前n项数据。使用skip操作符，可以忽略Observable发射的前n项数据，只保
         * 留之后的数据。skipLast反之。
         */
//        Observable.just(1, 2, 3, 4, 5, 6)
//                .skip(3)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept: "+integer);
//                    }
//                });

        /**
         * elementAt只发射第n项数据。elementAt操作符获取原始Observable发射的数据序列指定索引位置的数据项，然
         * 后当做自己的唯一数据发射。它传递一个基于0的索引值，发射原始Observable数据序列对应索引位置的值，如果传
         * 递给elementAt的值为5，那么它会发射第6项数据。如果传递的是一个负数，则将会配偶出一个
         * IndexOutOfBoundsException异常。如果index值超出了元素边界则会输出默认值，如果有
         * （elementAt(2， defaultNum)）。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .elementAt(2)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println("accept: " + integer);
//                    }
//                });


        /**
         * ignoreElements操作符抑制原始Observable发射的所有数据，值允许它的终止通知（onError或onComplete）
         * 通过。如果我们不关心一个Observable发射的数据，但是希望在它完成时或者遇到错误终止时收到通知，那么久可
         * 以对Observable使用ignoreElements操作符，它将确保永远不会调用观察者的onNext方法。
         */
//        Observable.just(1, 2, 3, 4)
//                .ignoreElements()
//                .subscribe(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("run:");
//                    }
//                });

        /**
         * distinct操作符，过滤掉重复的数据项。过滤规则：只允许还没有发射过的数据项通过。
         * distinctUntilChanged操作符，改操作符与distinct的区别是，它只判定一个数据和它的直接前驱是否不同。
         */
//        Observable.just(1, 1, 3, 1, 5, 6, 7, 8)
//                .distinct()
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });
//        Observable.just(1, 2, 1, 2, 3, 4, 5, 5, 6)
//                .distinctUntilChanged()//distinctUntilChanged操作符，改操作符与distinct的区别是，它只判定一个数据和它的直接前驱是否不同。
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * filter只发射通过谓词测试的数据项。filter操作符使用你指定的一个谓词函数测试数据项，只有通过测试的数据
         * 才会被发射。
         */
//        Observable.just(2, 30, 22, 5, 60, 1)
//                .filter(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 10;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * debounce仅在过了一段指定的时间还没发射数据时才发射一个数据，会过滤掉发射速率过快的数据项。
         */
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                if (e.isDisposed()) return;
//                for (int i = 1; i <= 10; i++) {
//                    e.onNext(i);
//                    Thread.sleep(i * 100);
//                }
//            }
//        }).debounce(500, TimeUnit.MILLISECONDS)
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

//        ==================================条件操作符和布尔操作符=====================================
        /**
         * all操作符，判定Observable发射的所有数据是否都满足某个条件。传递一个谓词函数给all操作符，这个函数接受
         * 原始Observable发射的数据，根据计算返回一个布尔值。all返回一个只发射单个布尔值的Observable，如果原始
         * Observable正常终止并且每一项数据都满足条件，就返回true；如果原始Observable的任意一项数据不满足条件，
         * 就返回false。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .all(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer > 3;
//                    }
//                })
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        System.out.println(aBoolean);
//                    }
//                });

        /**
         * contains操作符，判定一个Observable是否发射了一个特定的值。给contains传一个指定的值，如果原始
         * Observable发射了那个值，那么返回的Observable将发射true，否则发射false。与它相关的操作符是isEmpty，
         * 用于判定原始的Observable是否未发射任何数据。
         */
//        Observable.just(1, 2, 3)
//                .contains(2)
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        System.out.println(aBoolean);
//                    }
//                });
//        Observable.<Integer>empty()
//                .isEmpty()
//                .subscribe(new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        System.out.println("isEmpty: "+aBoolean);
//                    }
//                });

        /**
         * amb操作符，给定两个或多个Observable，它只发射首先发射数据或通知的那个Observable的所有数据。当传递多
         * 个Observable给amb时，它只发射一个Observable的数据和通知：首先发送通知给amb的那个Observable，不管发
         * 射的是一项数据，还是一个onError或是onCompleted通知。amb将忽略和丢弃所有Observables的发射物。
         */
//        Observable.ambArray(
//                Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS),//稍作改动加个延迟
//                Observable.just(4, 5, 6))
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * defaultEmpty发射来自原始Observable的值，如果原始Observable没有发射任何值，就发射一个默认值。
         */
//        Observable.empty()
//                .defaultIfEmpty(8)
//                .subscribe(new Consumer<Object>() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        System.out.println(o);
//                    }
//                });

        /**
         * sequenceEqual判定两个Observable是否发射相同的数据序列。传递两个Observable给sequenceEqual操作符时
         * 它会比较两个Observable的发射物，如果两个序列相同（相同的数据，相同的顺讯，相同的终止状态），则发射
         * true，否则发射false。
         */
//        Observable.sequenceEqual(
//                Observable.just(1, 2, 3, 4),
//                Observable.just(1, 2, 3)
//        ).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                System.out.println(aBoolean);
//            }
//        });
//        Observable.sequenceEqual(
//                Observable.just(1, 2, 3),
//                Observable.just(1, 2, 3)
//                , new BiPredicate<Integer, Integer>() {
//                    @Override
//                    public boolean test(Integer integer, Integer integer2) throws Exception {
//                        return integer == integer2;
//                    }
//                }
//        ).subscribe(new Consumer<Boolean>() {
//            @Override
//            public void accept(Boolean aBoolean) throws Exception {
//                System.out.println(aBoolean);
//            }
//        });

        /**
         * skipUntil丢弃原始Observable发射的数据，直到第二个Observable发射了一项数据。skipUntil订阅原始的
         * Observable，但是忽略它的发射物，直到第二个Observable发射一项数据那一刻，它才开始发射原始Observable
         * 。skipUntil默认不在任何特定的调度器上执行。
         */
//        Observable.intervalRange(1, 9, 0, 1, TimeUnit.MILLISECONDS)
//                .skipUntil(Observable.timer(4, TimeUnit.MILLISECONDS))
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        System.out.println(aLong);
//                    }
//                }); try { Thread.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }

        /**
         * skipWhile丢弃Observable发射的数据，直到一个指定的条件不成立。skipWhile订阅原始的Observable，但是
         * 忽略它的发射物，直到指定的某个条件变为false，他才开始发射原始的Observable。skipWhile默认不在任何特
         * 定的调度器上执行。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .skipWhile(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer <= 2;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * takeUntil当第二个Observable发射了一项数据或者终止时，丢弃原始的Observable发射的任何数据。
         * takeUntil订阅并开始发射原始的Observable，它还监听你提供的第二个Observable。如果第二个Observable发
         * 射了一项数据或者发射了一个终止通知，则takeUntil返回的Observable会停止发射原始Observable并终止。
         */
//        Observable.just(1, 2, 3, 4, 5, 6, 7)
//                .takeUntil(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer == 5;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        /**
         * takeWhile发射原始Observable发射的数据，直到一个指定的条件不成立。takeWhile发射原始的Observable，
         * 直到某个指定的条件不成立，它会立即停止发射原始Observable，并终止自己的Observable。RxJava中的
         * takeWhile操作符返回一个原始Observable行为的Observable，直到某项数据我们指定的函数返回false，这个新
         * 的Observable就会发射onComplete终止通知。
         */
//        Observable.just(1, 2, 3, 4, 5)
//                .takeWhile(new Predicate<Integer>() {
//                    @Override
//                    public boolean test(Integer integer) throws Exception {
//                        return integer <= 2;
//                    }
//                })
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        System.out.println(integer);
//                    }
//                });

        // todo 合并操作符与连接操作符省略
        //        ==============================RxJava的背压=================================
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
//                    for(int i=0;;i++) {
//                        e.onNext(i);
//                    }
//            }
//        }).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d("yangke-", "${integer:}"+integer);
//                    }
//                });

//        ==============================合并操作符与连接操作符=================================
        /**
         * merge合并多个Observable的发射物，使得它们就像是单个的Observable一样。
         */
//        Observable o1 = Observable.just(1, 3, 5, 7, 9);
//        Observable o2 = Observable.just(2, 4, 6);
//        Observable.merge(o1, o2)
//                .subscribe(new Consumer() {
//                    @Override
//                    public void accept(Object o) throws Exception {
//                        System.out.println(o);
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        throwable.printStackTrace();
//                    }
//                }, new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        System.out.println("action!");
//                    }
//                });

        /**
         * zip通过一个函数将多个Observable的发射物结合到一起，基于这个函数的结果为每个结合体发射单个数据项。
         *
         * 1  3  5  9  10
         * 2  4  6
         *
         * result 3 result 7 result11 多余的数据项抛弃
         */
//        Observable<Integer> o1 = Observable.just(1, 3, 5, 9, 10);
//        Observable<Integer> o2 = Observable.just(2, 4, 6);
//        Observable.zip(o1, o2, new BiFunction<Integer, Integer, Object>() {
//            @Override
//            public Object apply(Integer integer, Integer integer2) throws Exception {
//                return integer + integer2;
//            }
//        }).subscribe(new Consumer<Object>() {
//            @Override
//            public void accept(Object o) throws Exception {
//                System.out.println(o);
//            }
//        });

        /**
         * startWith在数据序列的开头插入也给指定的项。startWith操作符还支持Iterable，同时还有一个
         * startWithArray的操作符。startWith还可以传递一个Observable对象，他会将那个Observable的发射物插在
         * 原始Observable发射的数据序列之前，然后把这个当做自己的发射物集合。
         */
//        Observable.just("Hello Java", "Hello Kotlin", "Hello Scala")
//                .startWith("Hello Android")
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        System.out.println(s);
//                    }
//                });

        /**
         *
         */


        /**
         *
         */


        /**
         *
         */


        /**
         *
         */

    }
}
