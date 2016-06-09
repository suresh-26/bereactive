package com.rakuten.demo;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class ManualErrorHandler {
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Star wars", "Bahubali 10", "Batman");
				for (String e : list) {
					try {
						System.out.println("At server: " + e);
						if (e.equalsIgnoreCase("Bahubali 10")) {
							throw new Exception("Something is wrong!!!");
						}
						sub.onNext(e);
						if (sub.isUnsubscribed()) {
							break;
						}
					} catch (Exception e1) {
						sub.onError(e1);
					}
				}
			}
		});

		Subscriber<String> mySubscriber = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				System.out.println("At client : " +s);
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

		myObservable.subscribe(mySubscriber);
	}
}
