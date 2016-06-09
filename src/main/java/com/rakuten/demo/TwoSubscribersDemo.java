package com.rakuten.demo;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class TwoSubscribersDemo {
	static int counter = 0;

	public static void main(String[] args) {
		Observable<String> myObservable = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Bahubali 10", "Star wars", "Batman", "Golden Axe");
				list.stream().map(e -> "Movie : " + e).forEach(sub::onNext);
			}
		});

		Subscriber<String> mySubscriber1 = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				System.out.println("At mySubscriber1 : " + s);
			}

			@Override
			public void onCompleted() {
				System.out.println("MySubscriber1 is done");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Error at mySubscriber1 :");
				e.printStackTrace();
			}
		};

		Subscriber<String> mySubscriber2 = new Subscriber<String>() {
			@Override
			public void onNext(String s) {
				System.out.println("At mySubscriber2 : " + s);
			}

			@Override
			public void onCompleted() {
				System.out.println("MySubscriber2 is Done");
			}

			@Override
			public void onError(Throwable e) {
				System.out.println("Error at mySubscriber2 : ");
				e.printStackTrace();
			}
		};

		myObservable.skip(1).take(2).filter(e -> e.toUpperCase().contains("STAR")).subscribe(mySubscriber1);
		myObservable.skip(2).take(3).filter(e -> e.toUpperCase().contains("GOLD")).subscribe(mySubscriber2);
	}
}
