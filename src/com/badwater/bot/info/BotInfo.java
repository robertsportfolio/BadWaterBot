package com.badwater.bot.info;

import java.util.ArrayList;

/**
 * Created by irinix on 9/29/14.
 */
public class BotInfo {
	private String caps;
	String name;
	ArrayList<String> capabilities;

	public BotInfo createBotInfo(String pName, String[] pCapabilities) {
		name = pName;
		for (String s : pCapabilities) {
			capabilities.add(s);
		}
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCaps(String[] caps) {
		for (String s : caps) {
			System.out.println(s);
			capabilities.add(s);
		}
	}

	public ArrayList<String> getCaps() {
		return capabilities;
	}

	public String getName() {
		return name;
	}
}
