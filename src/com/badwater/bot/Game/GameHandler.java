package com.badwater.bot.Game;

import com.badwater.bot.Game.gamecore.actors.AbstractActor;
import com.badwater.bot.Game.gamecore.actors.Player;

/**
 * Created by Robert on 8/31/2014.
 */
public class GameHandler {
	private Game game;
	private int gameCount = 0;
	private AbstractActor player;

	public void createNewGame(String playerName) {
		System.out.println("BWGame_" + gameCount);
		game = new Game("BWGame_" + gameCount);
		player = new Player(playerName);
		game.setPlayer(player);

	}

	public String getGameName() {
		return game.getName();
	}

	public Game getGame() {
		return game;
	}

	public AbstractActor getPlayer() {
		return player;
	}

	public String printGameInfo() {
		return game.getName() + "::" + game.getRoom().getName();
	}
}
