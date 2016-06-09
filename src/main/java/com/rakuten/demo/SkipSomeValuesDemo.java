package com.rakuten.demo;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class SkipSomeValuesDemo {
	static int counter = 0;
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3");// "Bahubali 10", "Star wars", "Batman", "Golden Axe");
				list.stream()
					.map(e -> "Great Movie : " + e)
					.forEach(sub::onNext);
				List<String> list2 = Arrays.asList("A", "B", "C", "D", "E");
				list2.stream()
					.map(e -> "Movie : " + e)
					.forEach(sub::onNext);
			}
		});

		Subscriber<String> mySubscriber = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				System.out.println("At client : " + s);
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

		Observable<String> myCustomObservable = myObservable.skip(3);
		myCustomObservable.subscribe(mySubscriber);
	}
}
