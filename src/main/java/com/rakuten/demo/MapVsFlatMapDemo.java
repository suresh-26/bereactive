package com.rakuten.demo;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class MapVsFlatMapDemo {

	public static void main(String[] args) {
		Observable<String> myObservable1 = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom", "Bahubali", "Star wars", "Batman");
				list.stream().map(s -> "Movie: " + s).forEach(sub::onNext);
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

		myObservable1.flatMap(s -> {
			String movie1 = s + " 1";
			String movie2 = s + " 2";
			String movie3 = s + " 3";
			return Observable.just(movie1, movie2, movie3);
		}).subscribe(mySubscriber);
	}
}
