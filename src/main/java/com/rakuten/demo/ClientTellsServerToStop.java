package com.rakuten.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class ClientTellsServerToStop {
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Bahubali", "Star wars", "Batman");
				for (String e : list) {
					System.out.println("At server : " + e);
					if (sub.isUnsubscribed()) {
						break;
					}
					sub.onNext(e);
				}
			}
		});

		Subscriber<String> mySubscriber = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				System.out.println("At client : " + s);
				if (s.toUpperCase().contains("BAHU")) {
					System.out.println("I got my movie!!!!");
					unsubscribe();
				}
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
