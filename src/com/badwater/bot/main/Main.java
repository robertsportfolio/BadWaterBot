package com.badwater.bot.main;

import com.badwater.bot.games.TicTacToe.TicTacToeBoard;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {

	public static void main(String args[]) {

		TicTacToeBoard b = new TicTacToeBoard ( 5, 5 );
		char[][] arr = b.getBoard ();
		int XX = arr.length;
		int YY = 0;
		for ( int y = 0; y < arr.length; y++ ) {
			String bLine = "";
			for ( int x = 0; x < arr[y].length; x++ ) {

				bLine += String.valueOf ( arr[y][x] );

			}

			System.out.println ( bLine + "::" + YY );
		}

	}
}

