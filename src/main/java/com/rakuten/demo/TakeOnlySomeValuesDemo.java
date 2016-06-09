package com.rakuten.demo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class TakeOnlySomeValuesDemo {
	static int counter = 0;
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Bahubali 10", "Star wars", "Batman", "Golden Axe");
				list.stream()
					.map(e -> "Movie : " + e)
					.forEach(sub::onNext);
				sub.onCompleted();
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

//		Observable<String> myCustomObservable = myObservable.take(2);
//		myCustomObservable.subscribe(mySubscriber);
		 myObservable.take(2).subscribe(mySubscriber);
	}
}
