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

/**
 * @author suresh.gupta
 *
 */

public class Example1 {
    public static void main(String[] args) {
        // TweetPojo tweetPojo = new TweetPojo();
        // tweetPojo.setId(1234);
        // tweetPojo.setMessage("learning RxJava");
        // // Observable<TweetPojo> tweets = Observable.just(tweetPojo);
        // Observable<TweetPojo> tweets = Observable.just(tweetPojo);
        // tweets.subscribe(Example1::printTweet);
        // Observable<String> tweets2 = Observable.just("learning RxJava", "Writing blog about RxJava",
        // "RxJava rocks!!");
        // Observable<Object> tweets3 = tweets2.map(tweet2 -> tweet2.length());
        // tweets3.subscribe(System.out::println);

        event();
    }

    public static void imperative() {
        String today = "friday";
        System.out.println(today);
        // if (today.equals("friday")) {
        // System.out.println("hello world");
        // }
    }

    public static void event() {
        String today = "friday";
        rx.Observable<String> todayObs = new CreateObservable().getStringObservable(today);
        todayObs.subscribe(System.out::println);
    }

    public static void printTweet(TweetPojo tweetPojo) {
        System.out.println("id: " + tweetPojo.getId());
        System.out.println("message: " + tweetPojo.getMessage());
    }
}
