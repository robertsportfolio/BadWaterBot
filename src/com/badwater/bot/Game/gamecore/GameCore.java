package com.badwater.bot.Game.gamecore;

import com.badwater.bot.Game.gamecore.actors.AbstractActor;
import com.badwater.bot.helpers.helperFuncs;

/**
 * Created by Robert on 8/31/2014.
 */
public abstract class GameCore {
	protected Room          room;
	private   String        gameName;
	private   AbstractActor player;

	public GameCore(String gameName) {

		this.gameName = gameName;
	}

	public String getName() {

		return gameName;
	}

	public void update() {
		helperFuncs.debugGotHere("Update in Game");
		room.onUpdate();
	}

	public Room getRoom() {
		return room;
	}

	public AbstractActor getPlayer() {
		return player;
	}

	public void setPlayer(AbstractActor playerName) {
		player = playerName;
	}
}
