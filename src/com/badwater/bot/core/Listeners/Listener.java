package com.badwater.bot.core.Listeners;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.generalcommands.*;
import com.badwater.bot.helpers.Authenticator.Authenticator;
import com.badwater.bot.helpers.Loggers.Logger;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */

public class Listener extends ListenerAdapter {
private Logger        logger;
private Connection    dbConn;
private Authenticator authenticator;
	protected ArrayList<Command> commands = new ArrayList<>();
	protected String             prefix   = "?";

public Listener(Connection conn) throws IOException {
	dbConn = conn;

		commands.add(new DieCommand());
		commands.add(new NewsCommand());
		commands.add(new JoinCommand());
		commands.add(new LeaveCommand());
		commands.add(new GoogleCommand());
		commands.add(new SayCommand());
		commands.add(new SourceCommand());
		commands.add(new CreditsCommand());
		commands.add(new PMCommand());

		//This must be the last command added
		commands.add(new HelpCommand(this));
		logger = new Logger("./Logs/IRC/", 1);

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

	String userName = e.getUser()
				    .getNick();
	if (e.getChannel()
		.getName()
		.contains("BWGame")) {
		//just ignore the message if it's in a game channel.
	}
	else if (e.getMessage()
			.equalsIgnoreCase("Hi Bill") || e.getMessage()
									   .equalsIgnoreCase(
														   "Hi " + e.getBot()
																  .getNick())) {

		e.getChannel()
		 .send()
		 .message("Hi " + userName);
	}
	else {
		String[] parseMsg = helperFuncs.toArgs(e.getMessage());

		if (parseMsg[0].startsWith(prefix) && !parseMsg[0].equals(prefix)) {
			String command = parseMsg[0].replace(prefix, "");


			if (!processCommands(command)) {
				e.getChannel()
				 .send()
				 .message("I'm Sorry, " + userName + " I Don't Understand: " + command);
			}
			else {
				Command c = getCommand(command);
				//if c exists
				if (!c.equals(null)) {
					//if c
					if (c.requiresAuthentication()) {
						if (authenticator.isUserAuthorizedForCmd(userName, command)) {
							c.exec(e);
						}
						else {
							e.getChannel()
							 .send()
							 .message(
										  "I'm sorry, " + e.getUser()
													    .getNick() + " I can't let you do that!");
						}
					}
					else {
						c.exec(e);
					}

				}
			}
		}


		logger.log("<" + userName + "> " + e.getMessage());
	}
	}

public ArrayList<Command> getCommands() {
	return commands;
	}

public String getPrefix() {
	return prefix;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}
}


