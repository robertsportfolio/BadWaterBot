package com.badwater.bot.core.gamecore;

/**
 * Created by Robert on 8/31/2014.
 */
public abstract class GameCore {
	private   String gameName;
	protected Room  room;

	public GameCore(String gameName) {

		this.gameName = gameName;
	}

	public String getName() {

		return gameName;
	}

	public void update() {
		room.onUpdate();
	}

	public Room getRoom() {
		return room;
	}
}
