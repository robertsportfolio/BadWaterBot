package com.badwater.bot.Game;

import com.badwater.bot.core.gamecore.actors.AbstractActor;

/**
 * Created by Robert on 8/31/2014.
 */
public class GameHandler {
	private Game game;
	private int  gameCount = 0;
	private AbstractActor player;

	public void createNewGame() {
		System.out.println("BWGame_" + gameCount);
		game = new Game("BWGame_" + gameCount);

	}

	public String getGameName() {
		return game.getName();
	}

	public Game getGame(){
		return game;
	}

	public AbstractActor getPlayer() {
		return player;
	}

	public String printGameInfo(){
		return game.getName() + "::" + game.getRoom().getName();
	}
}
