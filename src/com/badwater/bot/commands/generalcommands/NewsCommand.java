package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.helpers.Readers.RSSReader;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
@SuppressWarnings("DefaultFileTemplate")
public class NewsCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();
	ArrayList<String> noteStrings = new ArrayList<>();

	public NewsCommand() {
		addNoteStrings();
		addHelpStrings();
	}

	private void addNoteStrings() {
		noteStrings.add("Must be in XML Formatted RSS Feed.");
		noteStrings.add("This System is Still Buggy.  Please be patient with it.");
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		BadwaterBot b = (BadwaterBot) event.getBot();
		RSSReader reader = b.getReader();
		String[] args = helperFuncs.toArgs(event.getMessage());

		if (args[1].equalsIgnoreCase("add")) {
			String name = args[2];
			String url = args[3];
			reader.addSource(event, name, url);
		}

		else if (args[1].equalsIgnoreCase("get")) {
			//noinspection unchecked
			ArrayList<String> argList = new ArrayList();
			for (int argNum = 2; argNum < args.length; argNum++) {
				argList.add(args[argNum]);
			}

			reader.showNews(event, argList.toArray(new String[argList.size()]));


		}

		else if (args[1].equalsIgnoreCase("update")) {
			reader.update(event);
		}

		else if (args[1].equalsIgnoreCase("sources")) {
			reader.showSources(event);
		}


	}

	@Override
	public String getAlias() {
		return "news";
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
		return "Various News Functions";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}


	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Add View and Update News Sources");
		helpStrings.add("Syntax:");
		helpStrings.add("add: add <source name> <source url>");
		helpStrings.add("get: get <source name 1> <source name 2> ....<source name N>");
		helpStrings.add("sources: prints a list of sources");
		helpStrings.add("Update: Updates all news sources");


	}

}
