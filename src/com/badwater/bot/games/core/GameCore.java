package com.badwater.bot.games.core;

import com.badwater.bot.helpers.Tuple;


/**
 * Created by irinix on 8/4/14.
 */
public abstract class GameCore {
	protected int boardSizeH;
	protected int boardSizeW;
	protected GameBoard board;
	protected boolean isRunning;


	protected void run(Tuple players) {
		isRunning = true;
		while ( isRunning ) {
			//do stuff here!

			updateBoard ();
		}
	}

	protected void updateBoard() {
	}

	protected void stop() {
		isRunning = false;
	}

	protected boolean move(Tuple newPosition) {
		boolean valid = verifyMove ( translateMove ( newPosition ) );
		if ( valid ) {
			//put player's piece at location

			return true;
		}
		else {
			//send back invalid move

			return false;
		}
	}

	protected boolean verifyMove(Tuple<Integer, Integer> move) {
		if ( move.getFirst () % 2 != 1 || move.getSecond () % 2 != 1 ) {
			return false;
		}
		else {
			return true;
		}
	}

	protected Tuple<Integer, Integer> translateMove(Tuple<Integer, Integer> position) {
		int x = ( position.getFirst () * 2 ) + 1;
		int y = ( position.getSecond () * 2 ) + 1;

		return new Tuple<Integer, Integer> ( x, y );

	}
}
