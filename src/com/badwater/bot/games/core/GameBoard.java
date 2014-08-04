package com.badwater.bot.games.core;

/**
 * Created by irinix on 8/4/14.
 */
public abstract class GameBoard {
	//    0    1    2    3   4     5     6     7
	private char[] seperators = { '⏌', '⏋', '⎿', '⎾', '⎸', '⎹', '⎺', '_' };
	private char[][] board;
	private int sizeH;
	private int sizeW;

	public GameBoard(int sizeH, int sizeW) {
		this.sizeH = sizeH;
		this.sizeW = sizeW;
		initializeBoard ();

	}


	public void initializeBoard() {

	}

	public char[][] getBoard() {
		return board;
	}
}


