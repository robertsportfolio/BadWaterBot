package com.badwater.bot.core;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.gamecommands.GameCommand;
import com.badwater.bot.commands.gamecommands.PrintGameInfoCommand;
import com.badwater.bot.commands.generalcommands.*;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by irinix on 8/3/14.
 */
public class Listener extends ListenerAdapter {
	protected ArrayList<Command> commands     = new ArrayList<>();
	protected String             prefix       = "?";
	protected boolean            respondState = true;

	public Listener() {
		commands.add(new DieCommand());
		commands.add(new NewsCommand());
		commands.add(new JoinCommand());
		commands.add(new HelpCommand(this));
		commands.add(new GameCommand());
		commands.add(new SayCommand());
		commands.add(new PrintGameInfoCommand());
	}

	public void onMessage(MessageEvent e) throws Exception {
		if (e.getChannel().getName().contains("BWGame")) {
			//just ignore the message if it's in a game channel.
		}
		else {
			String[] parseMsg = helperFuncs.toArgs(e.getMessage());
			String dateTime = new Date().toString();
			String msg = dateTime + " ::: " + e.getChannel().getName() + " :: " + e.getUser().getNick() + " : " + e
				  .getMessage();
			boolean understood = false;
			if (!parseMsg[0].startsWith(prefix)) {
				System.out.println(msg);
				understood = true;
				return;
			}
			else if (parseMsg[0].startsWith(prefix)) {
				for (Command cmd : commands) {
					if (parseMsg[0].equalsIgnoreCase(prefix + cmd.getAlias())) {
						String msg1 = dateTime + " ::: " + e.getUser().getNick() + " issued the " + cmd
							  .getAlias() + " Command";
						System.out.println(msg);
						System.out.println(msg1);
						understood = true;
						cmd.exec(e);
						break;
					}
					else {
						understood = false;
					}

				}
			}

			if (!understood) {
				String tmpMessage =
					  "I'm Sorry " + e.getUser().getNick() + " I don' understand \"" + e.getMessage() + "\"";
				e.getChannel().send().message(tmpMessage);
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


