package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBotManager;
import com.badwater.bot.core.Listener;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
	public static void main(String[] args) throws IOException, IrcException {
		BadwaterBotManager manager =
			   new BadwaterBotManager ( "Badwater_Manager", "bwManager", "irc.freenode.net", "#badwater", true,
	                       false );

		manager.run ();
		Listener l = new Listener ();
		manager.addManagedBot ( "BadWater_Bill", "BWBIL","irc.freenode.org","#badwater",false,false );
		manager.startManagedBot ( "BadWater_Bill" );

	}
}
