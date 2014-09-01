package com.badwater.bot.commands.gamecommands;

import com.badwater.bot.Game.GameHandler;
import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by Robert on 8/31/2014.
 */
public class PrintGameInfoCommand implements Command<MessageEvent> {

	@Override
	public void exec(MessageEvent event) throws Exception {
		BadwaterBot b = (BadwaterBot) event.getBot();
		GameHandler gh = b.getGameHandler();
		event.respond(gh.printGameInfo());
	}

	@Override
	public String getAlias() {
		return "printGameInfo";
	}
}
