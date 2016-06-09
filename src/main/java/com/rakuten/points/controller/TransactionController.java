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
package com.rakuten.points.controller;

import rx.Observable;

/**
 * @author suresh.gupta
 *
 */
public class TransactionController {

    public Observable<String> getTransactionObservable(String transactionId) {
        return Observable.just(transactionId);
    }
}
