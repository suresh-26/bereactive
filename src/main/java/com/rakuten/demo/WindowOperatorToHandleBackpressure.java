package com.rakuten.demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class WindowOperatorToHandleBackpressure {
	public static void main(String[] args) {


		Scheduler pusher = Schedulers.from(Executors.newCachedThreadPool(new ThreadFactory() {
		    @Override public Thread newThread(Runnable r) {
		        Thread result = new Thread(r);
		        result.setName("Pusher");
		        result.setDaemon(false);
		        return result;
		    }
		}));
		
		Observable<Observable<Long>> observableWindows = Observable.interval(600, TimeUnit.MILLISECONDS).window(2, TimeUnit.SECONDS).subscribeOn(pusher);
		
		observableWindows.subscribe((observable) -> observable.subscribe(
					new Subscriber<Long>() {
						{
							System.out.println("New window subscriber is created");
						}
						
						@Override
						public void onNext(Long s) {
							System.out.println("At window : " + s);
						}
	
						@Override
						public void onCompleted() {
							System.out.println("One window is done!!!");
						}
	
						@Override
						public void onError(Throwable e) {
							e.printStackTrace();
						}
					}
				)
				, (e) -> {e.printStackTrace();}
				, () -> {System.out.println("Done!!!");});

	}

}
