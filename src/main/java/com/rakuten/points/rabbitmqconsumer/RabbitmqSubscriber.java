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
 * Created on Apr 29, 2016
 * Updated on $Date$
 */
package com.rakuten.points.rabbitmqconsumer;

import rx.Subscriber;

/**
 * @author suresh.gupta
 *
 */
public class RabbitmqSubscriber {
    public Subscriber<Long> getRabbitMqSubscriber() {
        return new Subscriber<Long>() {
            @Override
            public void onNext(Long s) {
                System.out.println("subscriber Rabbitmq : " + s);
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

    }
}
