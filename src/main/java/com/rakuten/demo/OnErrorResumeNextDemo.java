package com.rakuten.demo;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Hello world!
 *
 */
public class OnErrorResumeNextDemo {
	static int counter = 0;

	public static void main(String[] args) {
		Observable<String> myObservable1 = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Dhoom 3", "Bahubali 10", "Star wars", "Batman");
				list.stream().forEach(
					(s) -> {
						try{
							if(s.toUpperCase().contains("BAHU")){
								throw new Exception("Something is wrong!!!");
							}
							sub.onNext(s);
						}catch(Exception ex){
							sub.onError(ex);
						}
					} 
				);
			}
		});

		Observable<String> myObservable2 = Observable.create(new Observable.OnSubscribe<String>() {
			@Override
			public void call(Subscriber<? super String> sub) {
				List<String> list = Arrays.asList("Spiderman", "King Kong", "Rangi", "Chan");
				list.stream().forEach(
					(s) -> {
						try{
							if(s.toUpperCase().contains("RANGI")){
								throw new Exception("Something is wrong!!!");
							}
							sub.onNext(s);
						}catch(Exception ex){
							sub.onError(ex);
						}
					} 
				);
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

		myObservable1//.onErrorResumeNext(myObservable2)
		.subscribe(mySubscriber);
	}
}











