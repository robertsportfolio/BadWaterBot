package com.badwater.bot.core;

import com.badwater.bot.helpers.RSSReader;
import org.pircbotx.Configuration;

import java.io.IOException;

/**
 * Created by irinix on 8/2/14.
 */
public class BadwaterBot extends BadwaterBotCore {
	private RSSReader reader = new RSSReader ();

	public BadwaterBot(Configuration config) throws IOException {
		super ( config );
		reader.loadSources ();

	}

	public RSSReader getReader() {
		return reader;
	}

}


