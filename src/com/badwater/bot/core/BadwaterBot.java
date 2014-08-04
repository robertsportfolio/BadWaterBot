package com.badwater.bot.core;

import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/2/14.
 */
public class BadwaterBot extends BadwaterBotCore {

	public BadwaterBot(String name,
	                   String login, String password,
	                   String server,
	                   String channel, Listener listener,
	                   boolean autoNickChange,
	                   boolean capEnabled) throws IOException, IrcException {

		if ( password != null ) {
			System.out.println ( "Password does not equal null, Logging In" );
			this.config = new Configuration.Builder ().setName ( name )
			                                          .setLogin ( login )
			                                          .setNickservPassword ( password )
			                                          .setAutoNickChange ( autoNickChange )
			                                          .setCapEnabled ( capEnabled )
			                                          .setServerHostname ( server )
			                                          .addAutoJoinChannel ( channel )
			                                          .addListener ( listener )
			                                          .buildConfiguration ();
		}
		else {
			this.config = new Configuration.Builder ().setName ( name )
			                                          .setLogin ( login )
			                                          .setAutoNickChange ( autoNickChange )
			                                          .setCapEnabled ( capEnabled )
			                                          .setServerHostname ( server )
			                                          .addAutoJoinChannel ( channel )
			                                          .addListener ( listener )
			                                          .buildConfiguration ();
		}

		this.bot = new PircBotX ( config );


	}


}

