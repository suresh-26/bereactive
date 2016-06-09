package com.rakuten.demo;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

public class TransformObservableUsingGroupBy5 {
	public static void main(String[] args) {

		Observable.range(1, 15)
	    .groupBy(new Func1<Integer, Integer>() {
	        @Override
	        public Integer call(Integer integer) {
	            return integer % 5;
	        }
	    }).subscribe(new Action1<GroupedObservable<Integer, Integer>>() {
	        @Override
	        public void call(GroupedObservable<Integer, Integer> grouped) {
	            grouped.toList().subscribe(new Action1<List<Integer>>() {
	                @Override
	                public void call(List<Integer> integers) {
	                    System.out.println(integers + " (Remainder: " + grouped.getKey() + ")");
	                }
	            });
	        }
	    });
	}
	
	public static void main1(String[] args) {
		Observable.range(1, 15)
	    .groupBy(e -> e % 5).subscribe(grouped -> grouped.toList().subscribe( e -> System.out.println( e + " (Remainder: " + grouped.getKey() + ")")));
	}
}
