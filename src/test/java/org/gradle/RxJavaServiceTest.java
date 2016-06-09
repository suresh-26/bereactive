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
 * Created on Feb 26, 2016
 * Updated on $Date$
 */
package org.gradle;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

import rx.Observable;
import rx.observers.TestSubscriber;

/**
 * @author suresh.gupta
 *
 */
public class RxJavaServiceTest {
    @Test
    public void testRxTest() {
        RxJavaService rxJavaService = Mockito.spy(new RxJavaService());
        rxJavaService.rxTest();
        verify(rxJavaService, times(1)).printAllDone();
        verify(rxJavaService, times(1)).printStuffForMem(any(String.class));
        // verify(mockObject, atLeast(2)).someMethod("was called at least twice");
    }

    @Test
    public void testCreateobs() {
        RxJavaService rxJavaService = Mockito.spy(new RxJavaService());
        Observable<String> obs = rxJavaService.createMemberObs("createMem");
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        obs.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        verify(rxJavaService, times(1)).printStuffForMem(any(String.class));
    }

    @Test
    public void testCreateCard() {
        RxJavaService rxJavaService = new RxJavaService();
        Observable<String> obs = rxJavaService.createMemberCardObs("createCard");
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        obs.subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
    }
}
