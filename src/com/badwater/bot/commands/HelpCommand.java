package com.badwater.bot.commands;

import org.pircbotx.hooks.Event;

/**
 * Created by irinix on 8/19/14.
 */
public class HelpCommand implements Command<Event> {
	@Override public void exec(Event event) throws Exception {

	}

	@Override public String getAlias() {
		return "Help";
	}
}
