package com.badwater.bot.games.TicTacToe;

import com.badwater.bot.games.core.GameCore;

/**
 * Created by irinix on 8/4/14.
 */
public class TicTacToe extends GameCore {
	public TicTacToe() {
		board = new TicTacToeBoard ( 3, 3 );
		board.initializeBoard ();

	}

}