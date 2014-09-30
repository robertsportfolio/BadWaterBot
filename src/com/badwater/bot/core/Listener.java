package com.badwater.bot.core;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.generalcommands.*;
import com.badwater.bot.helpers.Readers.bwFileReader;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */

public class Listener extends ListenerAdapter {
	protected ArrayList<Command> commands = new ArrayList<>();
	protected String prefix = "?";

	public Listener() throws IOException {
		commands.add(new DieCommand());
		commands.add(new NewsCommand());
		commands.add(new JoinCommand());
		commands.add(new LeaveCommand());
		commands.add(new GoogleCommand());
		commands.add(new SayCommand());
		commands.add(new SourceCommand());
		commands.add(new CreditsCommand());

		//This must be the last command added
		commands.add(new HelpCommand(this));

	}

	private Command getCommand(String command) {

		for (Command c : commands) {
			if (command.equalsIgnoreCase(c.getAlias())) {
				return c;
			}
		}
		//should never happen
		return null;
	}

	private boolean isUserAuthorized(MessageEvent e, Command c) throws Exception {
		return e.getUser().isVerified() && new bwFileReader("./DB/Configs/.authUsers").isUserAuthorized(
			   e.getUser().getNick(), c.getAlias());
	}

	private boolean processCommands(String command) throws Exception {
		boolean success = false;
		for (Command c : commands) {
			if (command.equalsIgnoreCase(c.getAlias())) {
				success = true;
			}
		}
		return success;
	}

	public void onMessage(MessageEvent e) throws Exception {

		String userName = e.getUser().getNick();
		if (e.getChannel().getName().contains("BWGame")) {
			//just ignore the message if it's in a game channel.
		}
		else if (e.getMessage().equalsIgnoreCase("Hi Bill") || e.getMessage().equalsIgnoreCase(
			   "Hi " + e.getBot().getNick())) {

			e.getChannel().send().message("Hi " + userName);
		}
		else {
			String[] parseMsg = helperFuncs.toArgs(e.getMessage());

			if (parseMsg[0].startsWith(prefix) && !parseMsg[0].equals(prefix)) {
				String command = parseMsg[0].replace(prefix, "");

				boolean commandFound = processCommands(command);
				if (!commandFound) {
					e.getChannel().send().message("I'm Sorry, " + userName + " I Don't Understand: " + command);
				}
				else if (commandFound) {
					Command c = getCommand(command);
					if (!c.equals(null)) {

						boolean Authorization = true;
						if (c.requiresAuthentication()) {

							Authorization = isUserAuthorized(e, c);
						}

						System.out.println(e.getUser().getNick() + ": " + Authorization);
						if (Authorization) {
							c.exec(e);
						}
						else {
							e.getChannel().send().message(
								   "I'm sorry, " + e.getUser().getNick() + " I can't let you do that!");
						}

					}
				}
			}
		}


	}

	public ArrayList<Command> getCommands() {
		return commands;
	}

	public String getPrefix() {
		return prefix;
	}

}


