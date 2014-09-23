package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.Listener;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by irinix on 8/19/14.
 */
public class HelpCommand implements Command<MessageEvent> {
	Listener l;
	ArrayList<String>                   helpStrings  = new ArrayList<String>();
	HashMap<Command, ArrayList<String>> CommandsHelp = new HashMap<>();

	public HelpCommand(Listener listener) {
		helpStrings.add("Returns Command Strings");
		l = listener;
		for (Command c : l.getCommands()) {
			if (c.getHelpString() != null) {
				CommandsHelp.put(c, c.getHelpString());

			}
			else {
				continue;
			}
		}
	}


	@Override
	public void exec(MessageEvent event) throws Exception {
		for (Command c : CommandsHelp.keySet()) {
			event.getUser().send().notice(l.getPrefix() + c.getAlias());
			event.getUser().send().notice("Syntax:");
			for (String s : CommandsHelp.get(c)) {
				event.getUser().send().notice(l.getPrefix() + c.getAlias() + " " + s);
			}
		}
	}

	@Override
	public String getAlias() {
		return "Help";
	}

	@Override
	public ArrayList<String> getHelpString() {
		return helpStrings;
	}
}
