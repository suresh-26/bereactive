package com.rakuten.demo;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class TransformObservableUsingGroupBy {
	public static void main2(String[] args) {

		Observable.range(1, 10)
	    .groupBy(new Func1<Integer, Boolean>() {
	        @Override
	        public Boolean call(Integer integer) {
	            return integer % 2 == 0;
	        }
	    }).subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
	        @Override
	        public void call(GroupedObservable<Boolean, Integer> grouped) {
	            grouped.toList().subscribe(new Action1<List<Integer>>() {
	                @Override
	                public void call(List<Integer> integers) {
	                    System.out.println(integers + " (Even: " + grouped.getKey() + ")");
	                }
	            });
	        }
	    });
	}
	
	public static void main(String[] args) {
		Observable.range(1, 10)
	    .groupBy(e -> e % 2 == 0).subscribe(grouped -> grouped.toList().subscribe( e -> System.out.println( e + " (Even: " + grouped.getKey() + ")")));
	}
}
