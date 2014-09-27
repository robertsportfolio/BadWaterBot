package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.Readers.GoogleSearch.GoogleApiReader;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class GoogleCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	ArrayList<String> noteStrings = new ArrayList<>();

	public GoogleCommand() {
		addNoteStrings();
		addHelpStrings();
	}

	private void addNoteStrings() {
		noteStrings.add("Newly Implemented, May Be Buggy!");
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		String googleQuery = event.getMessage().substring(7).trim();

		ArrayList<String> searchResults;
		searchResults = new GoogleApiReader(googleQuery).getResults();
		if (searchResults.isEmpty() || searchResults.equals(null)) {
			event.getChannel().send().message("Sorry, " + event.getUser().getNick() + " no results");
		}
		else {
			event.getChannel().send().message(
				   "Okay: " + event.getUser().getNick() + " Here Are Your Top 5 Search Results:");

			for (int i = 0; i < 5; i++) {
				event.getChannel().send().message(searchResults.get(i));
			}
		}


	}

	@Override
	public String getAlias() {
		return "google";
	}

	@Override
	public ArrayList<String> getHelpList() {
		return helpStrings;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return noteStrings;
	}

	@Override
	public String getTopicString() {
		return "Uses The Google.  Quite Possibly For Maps";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}


	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Googles <Search String>");
		helpStrings.add("Syntax:");
		helpStrings.add(" <Search String>");
	}

}
