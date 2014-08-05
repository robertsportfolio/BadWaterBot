package com.badwater.bot.managerCommands;

import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by irinix on 8/4/14.
 */
public class newGameCommand implements ManagerCommand {
	@Override public void exec(MessageEvent event) throws Exception {

	}

	@Override public String getAlias() {
		return "NewGame";
	}
}
