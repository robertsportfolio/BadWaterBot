package com.badwater.bot.games.core;

/**
 * Created by irinix on 8/4/14.
 */
public abstract class GameBoard {
	//      0    1    2    3   4     5     6    7    8    9   10
	protected char[] seperators = { '⎾', '⏋', '⎿', '⏌', '⎸', '|', '⎹', '⎺', '-', '_', '+' };
	protected char[][] board;
	protected int sizeH;
	protected int sizeW;

	public GameBoard(int sizeH, int sizeW) {
		this.sizeH = sizeH;
		this.sizeW = sizeW;
		initializeBoard ();

	}


	public void initializeBoard() {
		//Add rows and columns for our borders and internal structure
		int rowSize = ( sizeH * 2 ) + 1;
		int topAndBottom = ( sizeW * 2 ) + 2;
		board = new char[( sizeH * 2 ) + 1][];
		for ( int y = 0; y < board.length; y++ ) {
			if ( y == 0 ) {
				board[y] = new char[topAndBottom];
				//top Left Corner
				board[y][0] = seperators[0];
				for ( int x = 1; x < board[y].length - 1; x++ ) {
					board[y][x] = seperators[7];
				}
				//top Right Corner
				board[y][topAndBottom - 1] = seperators[1];
			}
			else if ( y != 0 && y < board.length - 1 ) {
				board[y] = new char[rowSize];
				board[y][0] = seperators[4];
				for ( int x = 1; x < board[y].length - 1; x++ ) {
					if ( y % 2 == 1 && x % 2 == 1 ) {
						board[y][x] = ' ';
					}

					else if ( y % 2 == 0 && x % 2 == 1 ) {
						board[y][x] = seperators[8];
					}
					else if ( y % 2 == 1 && x % 2 == 0 ) {
						board[y][x] = seperators[5];
					}
					else if ( y % 2 == 0 && x % 2 == 0 ) {
						board[y][x] = seperators[10];
					}
				}
				board[y][rowSize - 1] = seperators[6];
			}
			else {
				board[y] = new char[topAndBottom];
				board[y][0] = seperators[2];
				for ( int x = 1; x < board[y].length - 1; x++ ) {
					board[y][x] = seperators[9];
				}
				board[y][topAndBottom - 1] = seperators[3];
			}


		}
	}


	public char[][] getBoard() {
		return board;
	}
}


