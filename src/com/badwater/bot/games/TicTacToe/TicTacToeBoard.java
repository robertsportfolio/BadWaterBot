package com.badwater.bot.games.TicTacToe;

//local imports

import com.badwater.bot.games.core.GameBoard;

/**
 * Created by irinix on 8/4/14.
 */
public class TicTacToeBoard extends GameBoard {

	public TicTacToeBoard(int sizeH, int sizeW) {
		super ( sizeH, sizeW );

	}

	/*
	@Override public void initializeBoard() {
		/*                               0    1    2    3   4     5     6    7    8    9   10
		protected char[] seperators = { '⎾', '⏋', '⎿', '⏌', '⎸', '|', '⎹', '⎺', '-', '_', '+' };
		*//*
		board = new char[][]{
			   { '⎾', '⎺', '⎺', '⎺', '⎺', '⎺','⎺', '⏋' },
			   { '⎸', ' ', '|', ' ', '|', ' ', '⎹' },
			   { '⎸', '-', '+', '-', '+', '-', '⎹' },
			   { '⎸', ' ', '|', ' ', '|', ' ', '⎹' },
			   { '⎸', '-', '+', '-', '+', '-', '⎹' },
			   { '⎸', ' ', '|', ' ', '|', ' ', '⎹' },
			   { '⎿', '_', '_', '_', '_', '_','_', '⏌' } };
	}
	*/
	public void placePiece(int y, int x) {

	}

}
