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
 * Created on Jan 22, 2016
 * Updated on $Date$
 */
package org.gradle;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.internal.util.RxThreadFactory;
import rx.schedulers.Schedulers;

/**
 * @author suresh.gupta
 *
 */
public class RxJavaService {
    private Scheduler scheduler;

    public static void main(String[] args) {
        new RxJavaService().rxTest();
    }

    public void rxTest() {
        Executor executor = new RxThreadPoolExecutor(3, 6, 30,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(), new RxThreadFactory("RxJavaThreadPool-"));
        scheduler = Schedulers.from(executor);
        Observable.mergeDelayError(
                this.createMemberObs("member").subscribeOn(scheduler),
                this.createMemberProfileObs("profile").subscribeOn(scheduler),
                this.createMemberAttributeServiceObs("attribute").subscribeOn(scheduler),
                this.createMemberCardObs("memberCard"),
                this.createMemberAddressObs("member address"))
                .doOnError(error -> {
                    System.out.println("onSomeErrorr");
                })
                .toBlocking()
                .forEach(next -> {
                    System.out.println(next.getClass().getCanonicalName());
                });

        printAllDone();
    }

    public void printAllDone() {
        System.out.println("all done !");
    }

    public Observable<String> createMemberObs(String type) {
        return Observable.create(subscriber -> {
            try {
                printStuffForMem(type);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("created");
                }
                subscriber.onCompleted();
            } catch (Exception exception) {
                System.out.println("caught exception" + type);
                subscriber.onError(exception);
            }
        });
    }

    private Observable<String> createMemberProfileObs(String type) {
        return Observable.create(subscriber -> {
            try {
                printStuffForProf(type);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("created");
                }
                subscriber.onCompleted();
            } catch (Exception exception) {
                System.out.println("caught exception" + type);
                subscriber.onError(exception);
            }
        });
    }

    private Observable<String> createMemberAttributeServiceObs(String type) {
        return Observable.create(subscriber -> {
            try {
                printStuffForAttr(type);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("created");
                }
                subscriber.onCompleted();
            } catch (Exception exception) {
                System.out.println("caught exception" + type);
                subscriber.onError(exception);
            }
        });
    }

    public Observable<String> createMemberCardObs(String type) {
        return Observable.create(subscriber -> {
            try {
                printStuffForCard(type);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("created");
                }
                subscriber.onCompleted();
            } catch (Exception exception) {
                System.out.println("caught exception" + type);
                subscriber.onError(exception);
            }
        });
    }

    private Observable<String> createMemberAddressObs(String type) {
        return Observable.create(subscriber -> {
            try {
                printStuffForAdd(type);
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext("created");
                }
                subscriber.onCompleted();
            } catch (Exception exception) {
                System.out.println("caught exception" + type);
                subscriber.onError(exception);
            }
        });
    }

    public void printStuffForMem(String type) {
        System.out.println("type: " + type + Thread.currentThread().getName());
    }

    private void printStuffForProf(String type) {
        System.out.println("type: " + type + Thread.currentThread().getName());
    }

    private void printStuffForCard(String type) {
        System.out.println("type: " + type + Thread.currentThread().getName());
    }

    private void printStuffForAdd(String type) {
        System.out.println("type: " + type + Thread.currentThread().getName());
    }

    private void printStuffForAttr(String type) {
        System.out.println("type: " + type + Thread.currentThread().getName());
    }

    private class RxThreadPoolExecutor extends ThreadPoolExecutor {

        public RxThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        @Override
        public void execute(Runnable command) {
            super.execute(new WrappedRunnable(command));
        }

        /**
         * Used to set and clear Thread Local variables in New Thread.
         *
         * @author Venkatesha Chandru
         */
        private class WrappedRunnable implements Runnable {

            private Runnable command;

            public WrappedRunnable(Runnable command) {
                this.command = command;
            }

            @Override
            public void run() {
                try {
                    this.command.run();
                } finally {
                }
            }
        }
    }
}
