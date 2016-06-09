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
 * Created on Apr 21, 2016
 * Updated on $Date$
 */
package rxExamples;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.observables.ConnectableObservable;

/**
 * @author suresh.gupta
 *
 */
public class IntervalExample {

    public static void main(String[] args) throws Exception {
        ConnectableObservable<Long> hotObservable = Observable.interval(1, TimeUnit.SECONDS).publish();
        // Observable<Long> hotObservable = Observable.interval(1, TimeUnit.SECONDS);
        hotObservable.connect();
        Thread.sleep(2000);
        hotObservable.subscribe(val -> System.out.println("Subscriber 1 >> " + val));

        Thread.sleep(5000);

        hotObservable.subscribe(val -> System.out.println("Subscriber 2 >> " + val));

        Thread.sleep(5000);
    }
}
