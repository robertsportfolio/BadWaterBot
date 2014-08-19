package com.badwater.bot.core;

//local imports

import com.badwater.bot.helpers.Markov.Learner;
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
	private RSSReader reader = new RSSReader ();
	//Create A Learner for This Bot
	private Learner learner;


	public BadwaterBot(Configuration config) throws IOException, ClassNotFoundException {
		super ( config );
		learner = new Learner ();
		reader.loadSources ();

	}

	public Learner getLearner() {
		return learner;
	}

	public RSSReader getReader() {
		return reader;
	}


}


