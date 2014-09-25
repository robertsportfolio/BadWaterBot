package com.badwater.bot.commands.gamecommands;

import com.badwater.bot.Game.GameHandler;
import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.GameListener;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public class GameCommand implements Command<MessageEvent> {

	@Override
	public void exec(MessageEvent event) throws Exception {
		BadwaterBot b = (BadwaterBot) event.getBot();
		GameHandler gh = b.getGameHandler();
		gh.createNewGame(event.getUser().getNick());
		String gameChannel = "#" + gh.getGameName();
		System.out.println("Joining: " + gameChannel);
		b.getConfiguration().getListenerManager().addListener(new GameListener(gameChannel));
		b.sendIRC().joinChannel(gameChannel);
		b.sendIRC().invite(event.getUser().getNick(), gameChannel);
		System.out.println(b.getName());

	}

	@Override
	public String getAlias() {
		return "newgame";
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

	public ArrayList<String> getHelpString() {
		return null;
	}

	@Override
	public void addHelpStrings() {

	}


}
