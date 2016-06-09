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
public class TakeOrdersForOnlySomeTime {
	static int counter = 0;
	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Bahubali 10", "Star wars", "Batman");
				list.stream()
					.map(e -> "Movie : " + e)
					.forEach(sub::onNext);
			}
		});

		Subscriber<String> mySubscriber = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				try{
					System.out.println("I'm sorry... It takes a second to process an order!!!");
					TimeUnit.SECONDS.sleep(1);
				}catch(Exception e){
					e.printStackTrace();
				}
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

		Observable<String> myCustomObservable = myObservable.take(3, TimeUnit.SECONDS);
		myCustomObservable.subscribe(mySubscriber);
	}
}
