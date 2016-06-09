package com.rakuten.demo;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class HelloWorldRxJava {
    public static void main(String[] args) {
        Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> sub) {
                try {
                    sub.onNext("Hello, world!");
                    sub.onCompleted();
                } catch (Exception e) {
                    sub.onError(e);
                }
            }
        });

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("subscriber 1 : " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Done");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        Subscriber<String> mySubscriber2 = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                System.out.println("subscriber 2 : " + s);
            }

            @Override
            public void onCompleted() {
                System.out.println("Done 2");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }
        };

        myObservable.subscribe(mySubscriber);
        myObservable.subscribe(mySubscriber2);
    }
}
