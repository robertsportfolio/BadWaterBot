package com.badwater.bot.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by irinix on 8/2/14.
 */
public abstract class BadwaterBotCore {
	protected PircBotX bot;

	protected UUID id;
	protected String name;
	protected String login;
	protected String server;
	protected String channel;
	protected boolean autoNickChange;
	protected boolean capEnabled;


	protected Configuration config;



	public String getName(){
		return name;
	}

	public void run() throws IOException, IrcException {
		bot.startBot ();
	}

	public void die(String sender) throws Throwable {
		bot.sendIRC ().message ( sender, "Okay, I'll Go Away Now!" );
		bot.sendIRC ().quitServer ( "Was Told To Go Away By" + sender );
		bot.stopBotReconnect ();
	}

	public UUID getID(){
		return id;
	}
}


