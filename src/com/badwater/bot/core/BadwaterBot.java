package com.badwater.bot.core;

//local imports

import com.badwater.bot.Game.GameHandler;
import com.badwater.bot.helpers.RSSReader;
import org.pircbotx.Configuration;

import java.io.IOException;

//external imports
//java imports

/**
 * Created by irinix on 8/2/14.
 */
public class BadwaterBot extends BadwaterBotCore {
	//Create a new RSS Reader for this bot
	private RSSReader reader = new RSSReader();
	//Create A Learner for This Bot
	//private Learner learner;
	//create a new game for this bot
	private GameHandler gameHandler = new GameHandler();
	private int gameNum;

	public BadwaterBot(Configuration config) throws IOException, ClassNotFoundException {
		super(config);
		//learner = new Learner();

		reader.loadSources();

	}

	public GameHandler getGameHandler() {
		return gameHandler;
	}

	public int getGameNum() {
		return gameNum;
	}

	public RSSReader getReader() {
		return reader;
	}


}


