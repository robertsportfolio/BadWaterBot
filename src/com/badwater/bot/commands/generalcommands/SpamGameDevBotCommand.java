package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class SpamGameDevBotCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();

	public SpamGameDevBotCommand() {

	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		return;
	}

	@Override
	public String getAlias() {
		return "SpamGameDevBot";
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
		return helpStrings;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Does Nothing");
	}


}
