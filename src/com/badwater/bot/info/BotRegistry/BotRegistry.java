package com.badwater.bot.info.BotRegistry;

import com.badwater.bot.info.BotInfo;

import java.util.HashMap;

/**
 * Created by Robert on 9/30/2014.
 */
public class BotRegistry {
	//store the info in a hashmap for easy access
	private HashMap<String, BotInfo> knownBots = new HashMap<>();
	private BotInfo                  localInfo = new BotInfo();

	public BotRegistry() {
		//default Constructor.
		//Basically just ignore this.

	}


	public void addBot(String name, BotInfo info) {
		if (!knownBots.containsKey(name)) {
			knownBots.put(name, info);
		}
	}

	public void updateBot(String name, String[] pCapablities) {
		if (knownBots.containsKey(name)) {
			for(String s : pCapablities){
				if(knownBots.get(name).getCaps().contains(s)){
					//skip
					continue;
				}
				else{
					knownBots.get(name).addCaps(s);
				}
			}
		}

	}

	public BotInfo getBotInfo(String pName){
		return knownBots.get(pName);
	}

	public boolean isBotKnown(String pName){
		return knownBots.containsKey(pName);
	}

	public boolean botHasCap(String pName, String pCap){
		BotInfo info = knownBots.get(pName);
		if(info.getCaps().contains(pCap)){
			return true;
		}
		else{
			return false;
		}
	}

	public void setLocalInfo(BotInfo localInfo) {
		this.localInfo = localInfo;
	}
}
