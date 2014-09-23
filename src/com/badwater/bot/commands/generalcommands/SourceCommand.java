package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/20/14.
 */
public class SourceCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();

	public SourceCommand() {
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
	public ArrayList<String> getHelpString() {
		return helpStrings;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add(" :prints the source URL ");

	}

}
