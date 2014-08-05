package com.badwater.bot.games.core;

import com.badwater.bot.helpers.Tuple;
import org.pircbotx.User;

import java.util.ArrayList;

/**
 * Created by irinix on 8/4/14.
 */
public abstract class GameCore {
	protected int boardSizeH;
	protected int boardSizeW;
	protected GameBoard board;
	protected ArrayList<User> players;
	protected User player1;
	protected User player2;

	
	protected Tuple<Integer, Integer> TranslateMove(Tuple<Integer, Integer> position) {
		int x = ( position.getFirst () * 2 ) + 1;
		int y = ( position.getSecond () * 2 ) + 1;
		if ( !verifyMove ( new Tuple<Integer, Integer> ( x, y ) ) ) {
			return null;
		}

		else {
			return new Tuple<Integer, Integer> ( x, y );
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
}
