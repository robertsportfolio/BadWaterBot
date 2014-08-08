package com.badwater.bot.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/2/14.
 */
public abstract class BadwaterBotCore extends PircBotX {


	protected Configuration config;

	/**
	 * Constructs a PircBotX with the provided configuration.
	 *
	 * @param configuration
	 */
	public BadwaterBotCore(Configuration<? extends PircBotX> configuration) {
		super ( configuration );
	}

	//protected String loginInfo = getName () + "'s Password does Not equal NULL: Logging in";

	public String getName() {
		return this.getNick ();

	}

	public int getId() {
		return this.getBotId ();
	}

	protected void run() throws IOException, IrcException {
		if ( this.config.getNickservPassword () != null ) {
			System.out.println ( "Password Does != Null, Logging in." );
		}
		this.startBot ();
	}

	public void die(String sender) {
		this.sendIRC ().message ( sender, "Okay, I'll Go Away Now!" );
		this.sendIRC ().quitServer ( "Was Told To Go Away By" + sender );
		this.stopBotReconnect ();
	}


}


