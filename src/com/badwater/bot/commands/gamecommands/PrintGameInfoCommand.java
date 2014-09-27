package com.badwater.bot.commands.gamecommands;

import com.badwater.bot.Game.GameHandler;
import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

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

	@Override
	public ArrayList<String> getHelpList() {
		return null;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return null;
	}

	@Override
	public String getTopicString() {
		return null;
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}

	public ArrayList<String> getHelpString() {
		return null;
	}

	@Override
	public void addHelpStrings() {

	}


}
