package com.badwater.bot.helpers.Markov;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by irinix on 8/19/14.
 */
public class MarkovChain implements Serializable {
	private HashMap<String, HashMap<String, Integer>> chains = new HashMap<String, HashMap<String, Integer>>();

	private boolean processing;

	public MarkovChain() {

	}

	public boolean isProcessing() {
		return processing;
	}

	public void genChain(String[] msg) {
		processing = true;
		//iterate over the msg array setting both primary and next keys.
		for (int i = 0; i < msg.length - 2; i++) {
			String priKey = msg[i];
			String nextKey = msg[i + 1];
			//if chains contains the primary key, check to see if it contains nextKey
			if (chains.containsKey(priKey)) {
				if (chains.get(priKey).containsKey(nextKey)) {
					//if it does, grab the integer Value of that key and add 1.  Then remove that key from
					//chains[priKey] and re add it with the new word count.
					int tmp = chains.get(priKey).get(nextKey) + 1;
					chains.get(priKey).put(nextKey, tmp);
				}
				else {
					chains.get(priKey).put(nextKey, 1);
				}
			}
			//if chains does NOT contain the primary key
			else {
				//create a temporary hashmap to store our values in.
				HashMap<String, Integer> tmpMap = new HashMap<String, Integer>();

				tmpMap.put(nextKey, 1);
				//put our tmpMap into chains[key]
				chains.put(priKey, tmpMap);
			}
		}
		processing = false;
	}

	public void printChains() {
		//print out each primary key
		for (String key : chains.keySet()) {
			System.out.println(key);
			//followed by a newLine + tab then secondary key followed by count
			for (String key1 : chains.get(key).keySet()) {
				System.out.println("\t" + key1 + "\t" + chains.get(key).get(key1));

			}
		}
	}

	public HashMap getChain() {
		return chains;
	}

	public void setChains(HashMap<String, HashMap<String, Integer>> loadedChain) {

		chains = loadedChain;
	}

}
