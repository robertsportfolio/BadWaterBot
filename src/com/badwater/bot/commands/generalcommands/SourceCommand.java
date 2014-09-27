package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/20/14.
 */
public class SourceCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> noteList = new ArrayList<String>();

	public SourceCommand() {
		addHelpStrings();
		addNoteStrings();
	}

	private void addNoteStrings() {
		noteList.add("NONE");
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		event.respond("https://github.com/rstr5105/badwaterbot");
	}

	@Override
	public String getAlias() {
		return "Source";
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
		return "Prints The Source URL";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}


	@Override
	public void addHelpStrings() {
		helpStrings.add("Use :prints the source URL ");
		helpStrings.add("Syntax: ?Source");

	}

}
