package com.badwater.bot.games.core;

import org.pircbotx.User;

import java.util.ArrayList;

/**
 * Created by irinix on 8/5/14.
 */
public abstract class Player {
	protected User user;
	protected ArrayList pieces;

	public Player() {

	}

	public Player(Object piece) {
		pieces.add ( piece );


	}


}
