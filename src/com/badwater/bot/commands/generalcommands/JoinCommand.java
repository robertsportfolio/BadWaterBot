package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/11/14.
 */
public class JoinCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> notesList = new ArrayList<String>();

	public JoinCommand() {
		addNotesStrings();
		addHelpStrings();

	}

	private void addNotesStrings() {
		notesList.add("NONE");
	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		String[] parsedCmd = helperFuncs.toArgs(e.getMessage());

		for (String s : parsedCmd) {
			if (s.startsWith("?join")) {
				continue;
			}

			else if (isThisAChannelName(s)) {
				if (s.contains(",")) {
					s = s.substring(s.indexOf("#"), s.indexOf(",") - 1);
					helperFuncs.debugGotHere(s);
				}
			}
			e.getChannel().send().message("Okay " + e.getUser().getNick() + " I'll see you in: " + s);
			e.getBot().sendIRC().joinChannel(s);
		}
	}


	private boolean isThisAChannelName(String s) {
		boolean success = false;
		if (s.startsWith("#")) {
			success = true;
		}
		return success;
	}

	@Override
	public String getAlias() {
		return "join";
	}

	@Override
	public ArrayList<String> getHelpList() {

		return helpStrings;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return notesList;
	}

	@Override
	public String getTopicString() {
		return "Joins A Channel";
	}

	@Override
	public boolean requiresAuthentication() {
		return true;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Joins a new Channel.");
		helpStrings.add("Syntax: ?join #<channelName>");
	}


}
