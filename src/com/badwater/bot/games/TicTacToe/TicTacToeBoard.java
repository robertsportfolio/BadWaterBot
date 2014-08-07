package com.badwater.bot.games.TicTacToe;

//local imports

import com.badwater.bot.games.core.GameBoard;
import com.badwater.bot.helpers.Tuple;

/**
 * Created by irinix on 8/4/14.
 */
public class TicTacToeBoard extends GameBoard {

	public TicTacToeBoard() {
		super ( 3, 3 );
		updateBoard ( 'X', new Tuple<Integer, Integer> ( 1, 1 ) );
		updateBoard ( 'X', new Tuple<Integer, Integer> ( 3, 3 ) );
		updateBoard ( 'X', new Tuple<Integer, Integer> ( 5, 5 ) );

	}

	@Override public void updateBoard(char piece, Tuple<Integer, Integer> position) {
		super.updateBoard ( piece, position );
	}
}
