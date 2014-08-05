package com.badwater.bot.games.TicTacToe;

import com.badwater.bot.games.core.GameCore;
import com.badwater.bot.games.core.Player;
import com.badwater.bot.helpers.Tuple;

/**
 * Created by irinix on 8/4/14.
 */
public class TicTacToe extends GameCore {
	public TicTacToe(Tuple<Player, Player> players) {

		board = new TicTacToeBoard ();

	}


}
