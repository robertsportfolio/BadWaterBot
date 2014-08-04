package com.badwater.bot.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/2/14.
 */
public abstract class BadwaterBotCore {
	protected PircBotX bot;


	protected Configuration config;


	public void getName() {


	}

	public void run() throws IOException, IrcException {

		bot.startBot ();
	}

	public void die(String sender) {
		bot.sendIRC ().message ( sender, "Okay, I'll Go Away Now!" );
		bot.sendIRC ().quitServer ( "Was Told To Go Away By" + sender );
		bot.stopBotReconnect ();
	}


}


