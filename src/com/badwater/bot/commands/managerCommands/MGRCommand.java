package com.badwater.bot.commands.managerCommands;

import org.pircbotx.hooks.Event;

/**
 * Created by irinix on 8/3/14.
 */
public interface MGRCommand<e extends Event> {
	public void exec(e event) throws Exception;


	public String getAlias();
}
