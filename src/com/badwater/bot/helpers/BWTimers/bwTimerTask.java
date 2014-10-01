package com.badwater.bot.helpers.BWTimers;

import java.util.TimerTask;

/**
 * Created by irinix on 9/30/14.
 */
public class bwTimerTask extends TimerTask {
	private long count;

	public bwTimerTask() {
		count = 0;
	}

	@Override
	public void run() {
		count+=200l;

	}

	public long getCount(){
		return count;
	}
}
