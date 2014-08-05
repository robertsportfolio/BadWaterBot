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

	protected String loginInfo = getName () + "'s Password does Not equal NULL: Logging in";

	public String getName() {
		return bot.getNick ();

	}

	public int getId() {
		return bot.getBotId ();
	}

	protected void run() throws IOException, IrcException {
		if ( this.config.getNickservPassword () != null ) {
			System.out.println ( loginInfo );
		}
		bot.startBot ();
	}

	public void die(String sender) {
		bot.sendIRC ().message ( sender, "Okay, I'll Go Away Now!" );
		bot.sendIRC ().quitServer ( "Was Told To Go Away By" + sender );
		bot.stopBotReconnect ();
	}


}


