package com.badwater.bot.core;

import com.badwater.bot.helpers.ConfigManager;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by irinix on 8/8/14.
 */
public class BotManager extends BadwaterBot {
	private ConfigManager cfm = new ConfigManager ();
	private ArrayList<BadwaterBot> managedBots = new ArrayList<BadwaterBot> ();
	private BadwaterBot bot;


	public BotManager(Configuration config) {
		super ( config );

	}

	public void addManagedBot(BadwaterBot b) {
		if ( !managedBots.contains ( b ) ) {
			managedBots.add ( b );

		}
	}


	public BadwaterBot createBot(Configuration c) throws IOException, IrcException {

		return new BadwaterBot ( c );

	}

	public ConfigManager getConfigMgr() {

		return cfm;
	}

	public void createBotConfig() throws IOException {
		ArrayList<Listener> listeners = new ArrayList<Listener> ();
		listeners.add ( new Listener () );
		Configuration c =
			   cfm.createConfig ( "BadWater_Bill", "BwBill", "weber.freenode.net", "#badwater", "givem3thecookie",
			                      listeners, false, false, false );
		cfm.addConfig ( c );

	}

	public void startManagedBots() throws IOException, IrcException {
		for ( BadwaterBot b : managedBots ) {
			b.startBot ();

		}
	}
}
