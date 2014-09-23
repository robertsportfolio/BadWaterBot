package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.Listener;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by irinix on 8/19/14.
 */
public class HelpCommand implements Command<MessageEvent> {
	Listener l;
	ArrayList<String>                         helpStrings = new ArrayList<String>();
	LinkedHashMap<Command, ArrayList<String>> commandKeys = new LinkedHashMap<>();


	public HelpCommand(Listener listener) {
		l = listener;
		addHelpStrings();
		fillCommandKeys();
	}

	private void printHelp(PrivateMessageEvent e) {
		String issuingUser = e.getUser().getNick();
		String[] parsedMsg = helperFuncs.toArgs(e.getMessage());

		ArrayList<ArrayList<String>> out = new ArrayList<ArrayList<String>>();
		if (parsedMsg.length == 1) {
			for (Command c : commandKeys.keySet()) {
				out.add(commandKeys.get(c));
			}
		}
		else if (parsedMsg.length == 2) {
			for (Command c : commandKeys.keySet()) {
				if (c.getAlias().equalsIgnoreCase(parsedMsg[2])) {
					out.add(c.getHelpString());
				}
			}
		}
		for (ArrayList<String> commands : out) {
			for (String s : commands) {
				e.getUser().send().notice(s);
			}
		}

		for (Command c : commandKeys.keySet()) {
			out.add(c.getHelpString());
		}


	}

	private void fillCommandKeys() {
		for (Command c : l.getCommands()) {
			if (c.getHelpString().equals(null)) {
				break;
			}
			else {
				commandKeys.put(c, c.getHelpString());

			}
		}
	}

	@Override
	public void exec(MessageEvent event) throws Exception {

		for (Command c : commandKeys.keySet()) {
			event.getUser().send().notice(l.getPrefix() + c.getAlias());
			event.getUser().send().notice("Syntax:");
			for (String s : commandKeys.get(c)) {
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

	@Override
	public void addHelpStrings() {
		helpStrings.add("Returns Command Strings");
	}


}
