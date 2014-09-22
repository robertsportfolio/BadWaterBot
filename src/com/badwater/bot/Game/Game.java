package com.badwater.bot.Game;

import com.badwater.bot.Game.gamecore.GameCore;
import com.badwater.bot.Game.gamecore.builders.RoomBuilder;
import com.badwater.bot.helpers.helperFuncs;

/**
 * Created by Robert on 8/31/2014.
 */
public class Game extends GameCore {


	public Game(String gameName) {

		super(gameName);
		helperFuncs.debugGotHere("GAME");
		room = new RoomBuilder().build();
		if (room.equals(null)) {
			helperFuncs.debugGotHere("Room = null error!");
		}
		else {
			helperFuncs.debugGotHere("Not An Error!");
		}
	}


}
