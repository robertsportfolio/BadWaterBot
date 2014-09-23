package com.badwater.bot.core;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.generalcommands.*;
import com.badwater.bot.helpers.Loggers.Logger;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class Listener extends ListenerAdapter {
	protected ArrayList<Command> commands     = new ArrayList<>();
	protected String             prefix       = "?";
	private Logger logger;

	public Listener() throws IOException {
		commands.add(new DieCommand());
		commands.add(new NewsCommand());
		commands.add(new JoinCommand());
		//commands.add(new GameCommand());
		commands.add(new SayCommand());
		commands.add(new SourceCommand());
		commands.add(new CreditsCommand());

		logger = new Logger("./Logs/IRC/", 1);

	}

	public void onMessage(MessageEvent e) throws Exception {

		String userName = e.getUser().getNick();
		if (e.getChannel().getName().contains("BWGame")) {
			//just ignore the message if it's in a game channel.
		}
		else if (e.getMessage().equalsIgnoreCase("Hi Bill")) {

			e.getChannel().send().message("Hello " + userName + " Loverly Day, Isn't it?");
		}
		else {
			String[] parseMsg = helperFuncs.toArgs(e.getMessage());

			if (parseMsg[0].equalsIgnoreCase("?Help")) {
				handleHelp(e, parseMsg);
			}
			else if (parseMsg[0].startsWith(prefix)) {
				String command = parseMsg[0].replace("?", "");
				boolean commandFound = processCommands(command);
				if (!commandFound) {
					e.getChannel().send().message("I'm Sorry, " + userName + " I Don't Understand: " + command);
				}
				else if (commandFound) {
					Command c = getCommand(command);
					if (!c.equals(null)) {
						System.out.println(userName + " Issued the " + c.getAlias() + " Command");
						c.exec(e);
					}
				}
			}
		}


		logger.log("<" + userName + "> " + e.getMessage());
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


	public ArrayList<Command> getCommands() {
		return commands;
	}

	public String getPrefix() {
		return prefix;
	}

	private boolean processCommands(String command) throws Exception {
		boolean success = false;
		for (Command c : commands) {
			if (command.equalsIgnoreCase(c.getAlias())) {
				success = true;
				break;
			}
		}
		return success;
	}

	private void handleHelp(MessageEvent e, String[] parsedMsg) {

		User issuingUser = e.getUser();
		String issuingUserNick = issuingUser.getNick();
		if (parsedMsg.length == 1) {

			for (Command c : commands) {
				String fullCommand = prefix + c.getAlias();
				issuingUser.send().notice(fullCommand);
				issuingUser.send().notice("Syntax:");
				if (!c.getHelpString().equals(null)) {
					ArrayList<String> helpList = c.getHelpString();
					for (String s : helpList) {
						issuingUser.send().notice(fullCommand + ":" + s);
					}
				}
			}
		}
		else if (parsedMsg.length == 2) {
			String helpTopic = parsedMsg[1];

			for (Command c : commands) {
				if (c.getAlias().equalsIgnoreCase(helpTopic)) {
					String[] helpList = (String[]) c.getHelpString().toArray(
						   new String[c.getHelpString().size()]);
					for (String s : helpList) {
						e.getBot().sendIRC().notice(issuingUserNick, s);
					}
				}
			}
		}
	}
}


