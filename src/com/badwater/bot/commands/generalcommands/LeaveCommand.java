package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/11/14.
 */
public class LeaveCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> notesList = new ArrayList<String>();

	public LeaveCommand() {
		addNotesStrings();
		addHelpStrings();

	}

	private void addNotesStrings() {
		notesList.add("NONE");
	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		String[] parsedCmd = helperFuncs.toArgs(e.getMessage());
		String s = parsedCmd[0];
		if (s.startsWith("?leave")) {
			if (isThisAChannelName(parsedCmd[0])) {
				if (s.contains(",")) {
					s = s.substring(s.indexOf("#"), s.indexOf(",") - 1);
					helperFuncs.debugGotHere(s);
				}
			}
			e.getChannel().send().message("Okay " + e.getUser().getNick() + " I'm Leaving: " + s);
			e.getBot().sendRaw().rawLine("part " + s);
			e.getBot().sendIRC().joinChannel("#badwater");
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
		return "leave";
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
	public void addHelpStrings() {
		helpStrings.add("Use: Leaves a Channel.");
		helpStrings.add("Syntax: ?leave #<channelName>");
	}


}
