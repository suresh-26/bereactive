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
 * Created on Apr 28, 2016
 * Updated on $Date$
 */
package com.rakuten.points.test;

import rx.Observable;

import com.rakuten.points.controller.TransactionController;
import com.rakuten.points.inventory.ResultInventory;
import com.rakuten.points.rabbitmqconsumer.RabbitmqSubscriber;
import com.rakuten.points.resultobservable.ResultProvider;

/**
 * @author suresh.gupta
 *
 */
public class PointsSchedulerTest {
    private static String TRANSACTION_ID = "transactionId";

    public static void main(String[] args) {
        ResultInventory resultInventory = new ResultInventory(TRANSACTION_ID);
        // new PointsSchedulerTest().testObservables();
    }

    /**
     * 
     */
    private void testObservables() {
        ResultInventory resultInventory = new ResultInventory(TRANSACTION_ID);
        Observable<String> transactionObservable = new TransactionController().getTransactionObservable(resultInventory
                .getTransactionId());
        Observable<Long> resultsPObservable = new ResultProvider().getResultObservable(transactionObservable,
                resultInventory);
        RabbitmqSubscriber rabbitmqSubscriber = new RabbitmqSubscriber();
        resultsPObservable.subscribe(data -> {
            System.out.println("data: " + data + " in thread : " + Thread.currentThread().getName());

        });

    }
}
