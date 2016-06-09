/*
 * Copyright (c) Rakuten, Inc. All Rights Reserved.
 * 
 * This program is the information assets which are handled
 * as "Strictly Confidential".
 * Permission of Use is only admitted in Rakuten Inc.
 * Development Department.
 * If you don't have permission , MUST not be published,
 * broadcast, rewritten for broadcast or publication
 * or redistributed directly or indirectly in any medium.
 * 
 * $Id$
 * Created on Apr 22, 2016
 * Updated on $Date$
 */
package rxExamples;

import java.util.stream.IntStream;

import rx.Observable;

/**
 * @author suresh.gupta
 *
 */
public class Example4 {

    public static Observable<Integer> naturalNumbers(int n) {
        return Observable.create(subscriber -> {
            try {
                IntStream.rangeClosed(1, n).forEach(number -> subscriber.onNext(number));
                int i = 6 / 0;
                subscriber.onCompleted();
            } catch (Exception e) {
                subscriber.onError(e);
            }
        });
    }

    public static void main(String[] args) {
        Observable<Integer> obs1 = naturalNumbers(10);
        // obs1.subscribe(System.out::println);
        obs1.doOnCompleted(() -> System.out.print("done"));
        obs1.doOnError(e -> System.out.println("error: " + e.getMessage()));
    }

}
