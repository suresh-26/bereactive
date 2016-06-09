package org.gradle;

import org.apache.commons.collections.list.GrowthList;

import rx.Observable;

public class RxJavaTest {
    private final String name;

    public RxJavaTest(String name) {
        this.name = name;
        new GrowthList();
    }

    public String getName() {
        return name;
    }

    public static void rxHelloWorld() {
        Observable.just("Hello, world! -Dan")
                .subscribe(s -> System.out.println(s));
    }

    public static void main(String[] args) {

        RxJavaService rxJavaService = new RxJavaService();
        rxJavaService.rxTest();

    }

}
