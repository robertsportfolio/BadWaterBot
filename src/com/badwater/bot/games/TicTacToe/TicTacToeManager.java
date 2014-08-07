package com.badwater.bot.games.TicTacToe;

import com.badwater.bot.core.GameManagerListener;
import com.badwater.bot.core.Listener;
import com.badwater.bot.games.core.Player;
import com.badwater.bot.helpers.Tuple;
import com.badwater.bot.helpers.helperFuncs;

/**
 * Created by irinix on 8/5/14.
 */
public class TicTacToeManager extends TicTacToePlayer {
	private Tuple<Player, Player> players;
	private Player player1;
	private Player player2;

	public TicTacToeManager(String name,
	                        String login,
	                        String password,
	                        String server,
	                        String channel,
	                        Listener listener,
	                        GameManagerListener GMListener,
	                        boolean autoNickChange,
	                        boolean capEnabled) {

	}


	protected void pickPlayer1(Tuple<Player, Player> players) {
		if ( helperFuncs.nextIntInRange ( 2, 1 ) == 1 ) {
			player1 = players.getFirst ();
			player2 = players.getSecond ();
		}
		else {
			player1 = players.getSecond ();
			player2 = players.getFirst ();
		}
	}
}
