package com.badwater.bot.commands;

import org.pircbotx.hooks.Event;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by irinix on 8/3/14.
 */
public interface Command<e extends Event> {


	void exec(MessageEvent event) throws Exception;

	public String getAlias();

	public java.util.ArrayList<String> getHelpString();
}
