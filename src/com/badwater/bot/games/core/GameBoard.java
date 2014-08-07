package com.badwater.bot.games.core;

import com.badwater.bot.helpers.Tuple;

/**
 * Created by irinix on 8/4/14.
 */
public abstract class GameBoard {
	//      0    1    2    3   4     5     6    7    8    9   10
	protected char[] seperators = { '╔', '╗', '╚', '╝', '╠', '║', '╣', '╩', '═', '╦', '╬' };
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
		int rowSize = ( sizeW * 2 ) + 1;
		int numOfRows = ( sizeH * 2 ) + 1;
		board = new char[numOfRows][];
		//top row
		for ( int y = 0; y < board.length; y++ ) {
			if ( y == 0 ) {
				board[y] = new char[rowSize];
				//top Left Corner
				board[y][0] = seperators[0];
				//top middle
				for ( int x = 1; x < board[y].length - 1; x++ ) {
					if ( x % 2 == 0 ) {
						board[y][x] = seperators[9];
					}
					else {
						board[y][x] = seperators[8];
					}
				}
				//top Right Corner
				board[y][rowSize - 1] = seperators[1];
			}
			//interior
			else if ( y != 0 && y < board.length - 1 ) {
				board[y] = new char[rowSize];
				if ( y % 2 == 0 ) {
					board[y][0] = seperators[4];
				}
				else {
					board[y][0] = seperators[5];
				}
				for ( int x = 1; x < board[y].length; x++ ) {
					//draw blank space for player
					if ( y % 2 == 1 && x % 2 == 1 ) {
						board[y][x] = ' ';
					}
					//draw a horizontal interior wall
					else if ( y % 2 == 0 && x % 2 == 1 ) {
						board[y][x] = seperators[8];
					}
					//draw a vertical
					else if ( y % 2 == 1 && x % 2 == 0 ) {
						board[y][x] = seperators[5];
					}
					//draw an intersection
					else if ( y % 2 == 0 && x % 2 == 0 ) {
						board[y][x] = seperators[10];
					}

				}
				//right hand wall
				//draw a right hand intersetcion
				if ( y % 2 == 0 ) {
					board[y][rowSize - 1] = seperators[6];
				}
				//draw a vertical wall
				else {
					board[y][rowSize - 1] = seperators[5];
				}

			}
			else {
				board[y] = new char[rowSize];
				//bottom right corner
				board[y][0] = seperators[2];
				//bottom middle
				for ( int x = 1; x < board[y].length - 1; x++ ) {
					if ( x % 2 == 0 ) {
						board[y][x] = seperators[7];
					}
					//vertical intersection
					else {
						board[y][x] = seperators[8];
					}
				}
				//lower right corner
				board[y][rowSize - 1] = seperators[3];
			}


		}
	}

	public void updateBoard(char piece, Tuple<Integer, Integer> position) {
		board[position.getSecond ()][position.getFirst ()] = piece;

	}

	public char[][] getBoard() {
		return board;
	}
}


