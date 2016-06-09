package com.rakuten.demo;

import rx.Observable;

public class TransformObservableUsingGroupByString {
	public static void main(String[] args) {
		Observable.just("A", "B", "C", "CC", "BB", "DDD", "DD")
		.groupBy(e -> ""+e.charAt(0)).subscribe(grouped -> grouped.toList().subscribe( e -> System.out.println( e + " (Starting Letter: " + grouped.getKey() + ")")));
	}
}
