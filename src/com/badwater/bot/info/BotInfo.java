package com.badwater.bot.info;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by irinix on 9/29/14.
 */
public class BotInfo {
	private String caps;
	String name;
	ArrayList<String> capabilities;

	public BotInfo createBotInfo(String pName, String[] pCapabilities) {
		name = pName;
		if(pCapabilities.equals(null)){
			capabilities = new ArrayList<String>();

			capabilities.add("NONE");
		}
		else {
			capabilities = new ArrayList<String>(Arrays.asList(pCapabilities));
		}
		return this;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCaps(ArrayList<String> caps) {
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

	public void addCaps(String s) {
		capabilities.add(s);
	}
}
