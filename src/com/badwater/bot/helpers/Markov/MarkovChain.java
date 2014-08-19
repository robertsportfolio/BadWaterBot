package com.badwater.bot.helpers.Markov;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by irinix on 8/19/14.
 */
public class MarkovChain implements Serializable {
	private HashMap<String, HashMap<String, Integer>> chains = new HashMap<String, HashMap<String, Integer>> ();

	public MarkovChain() {
	}

	public void genChain(String[] msg) {
		for ( int i = 0; i < msg.length - 2; i++ ) {
			String priKey = msg[i];
			String nextKey = msg[i + 1];
			if ( chains.containsKey ( priKey ) ) {
				if ( chains.get ( priKey ).containsKey ( nextKey ) ) {
					int tmp = chains.get ( priKey ).get ( nextKey ) + 1;
					chains.get ( priKey ).remove ( nextKey );
					chains.get ( priKey ).put ( nextKey, tmp );
				}
			}
			else if ( !chains.containsKey ( priKey ) ) {
				HashMap<String, Integer> tmpMap = new HashMap<String, Integer> ();
				tmpMap.put ( nextKey, 1 );
				chains.put ( priKey, tmpMap );
			}
		}
	}

	public void generateNextLikelyWord(String word) {

	}
}
