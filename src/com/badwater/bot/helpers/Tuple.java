package com.badwater.bot.helpers;

/**
 * Created by irinix on 8/3/14.
 */
public class Tuple {
	private Object object1;
	private Object object2;

	public Tuple(Object o, Object o1) {
		this.object1 = object1;
		this.object2 = object2;
	}

	public Object getFirst() {
		return object1;
	}

	public Object getSecond() {
		return object2;
	}
}
