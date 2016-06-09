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
package com.rakuten.points.resultobservable;

import java.util.List;

import rx.Observable;

import com.rakuten.points.inventory.ResultInventory;

/**
 * @author suresh.gupta
 *
 */
public class ResultProvider {
    public Observable<Long> getResultObservable(Observable<String> transactionObservable,
            ResultInventory resultInventory) {
        return transactionObservable.flatMap(string -> Observable.create(
                (sub) -> {
                    List<Long> list = resultInventory.getIdsListFromMap(string);
                    list.stream().forEach(sub::onNext);
                }));

    }
}
