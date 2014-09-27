package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/20/14.
 */
public class SayCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	ArrayList<String> noteList    = new ArrayList<>();

	public SayCommand() {
		addHelpStrings();
		addNoteStrings();
	}

	private void addNoteStrings() {
		noteList.add("I must be join'd to <#channel>");
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		String[] args = helperFuncs.toArgs(event.getMessage());
		if (!args[1].startsWith("#")) {
			event.getUser().send().notice(
				   "Sorry " + event.getUser().getNick() + " That is not a valid Channel Name");
		}
		else {
			String msg = "";
			for (int i = 2; i < args.length; i++) {
				msg += args[i] + " ";
			}
			event.getBot().sendIRC().message(args[1], msg);
		}
	}

	@Override
	public String getAlias() {
		return "Say";
	}

	@Override
	public ArrayList<String> getHelpList() {
		return helpStrings;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return noteList;
	}

	@Override
	public String getTopicString() {
		return "Say Something On A Channel";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Says <message> on <#channel>");
		helpStrings.add("Syntax: ?Say <#channel name> <message> : Says Message To Channel.");

	}


}
