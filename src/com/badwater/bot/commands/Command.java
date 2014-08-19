package com.badwater.bot.commands;

import org.pircbotx.hooks.Event;

/**
 * Created by irinix on 8/3/14.
 */
public interface Command<e extends Event> {


	public void exec(e event) throws Exception;

	public String getAlias();
}
