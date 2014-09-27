package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.Listener;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/24/14.
 */
public class HelpCommand implements Command<MessageEvent> {
	private Listener           l;
	private ArrayList<Command> commands;
	private ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> noteStrings = new ArrayList<String>();

	public HelpCommand(Listener l) {
		this.l = l;
		addHelpStrings();
		addNoteStrings();
		commands = l.getCommands();
	}

	private void addNoteStrings() {
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		String[] parsedMsg = helperFuncs.toArgs(event.getMessage());
		String divider = "=================";
		String moreInfoMsg = "Type ?help <command> for more info.";
		User issuingUser = event.getUser();
		if (parsedMsg.length == 1) {
			issuingUser.send().notice(divider);
			issuingUser.send().notice("Available Commands Are:");
			for (Command c : commands) {
				String fullCommand = l.getPrefix() + c.getAlias() + " - " + c.getTopicString();
				issuingUser.send().notice("\t\t" + fullCommand);
			}
			issuingUser.send().notice(divider);
			issuingUser.send().notice(moreInfoMsg);
			issuingUser.send().notice(divider);


		}
		else if (parsedMsg.length == 2) {
			String helpTopic = parsedMsg[1].replace("?", "");

			System.out.println(helpTopic);
			for (Command c : commands) {
				if (c.getAlias().equalsIgnoreCase(helpTopic)) {
					ArrayList<String> helpList = c.getHelpList();
					ArrayList<String> notesList = c.getNoteList();
					String fullCommand = l.getPrefix() + c.getAlias();
					issuingUser.send().notice(fullCommand + ":");
					issuingUser.send().notice(divider);
					for (String s : helpList) {
						if (s.startsWith("Use") || s.startsWith("Syntax")) {
							issuingUser.send().notice(s);
						}

						else {
							issuingUser.send().notice("\t\t\t" + fullCommand + " " + s);
						}
					}
					issuingUser.send().notice(divider);
					issuingUser.send().notice("Notes:");
					issuingUser.send().notice(divider);
					for (String s : notesList) {
						issuingUser.send().notice("\t\t\t" + s);
					}
					issuingUser.send().notice(divider);
				}
			}
		}
	}

	@Override
	public String getAlias() {
		return "help";
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
		return "This Command";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Prints The Available Commands, and their Usage");
		helpStrings.add("Syntax: ?help");
	}
}
