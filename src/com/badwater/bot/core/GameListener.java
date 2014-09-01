package com.badwater.bot.core;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.gamecommands.AttackCommand;
import com.badwater.bot.commands.gamecommands.PrintGameInfoCommand;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by irinix on 8/3/14.
 */
public class GameListener extends ListenerAdapter {
	protected ArrayList<Command> commands     = new ArrayList<>();
	protected String             prefix       = "!";
	private String gameChannel;
	protected boolean            respondState = true;

	public GameListener(String gameChannel) {
		this.gameChannel = gameChannel;
		commands.add(new AttackCommand());
		commands.add(new PrintGameInfoCommand());
	}

	public void onMessage(MessageEvent e) throws Exception {
			if(!e.getChannel().getName().contains("BWGame_")){
				//skip the message
			}
			String[] parseMsg = helperFuncs.toArgs(e.getMessage());
			String dateTime = new Date().toString();
			boolean understood = false;
			if (!parseMsg[0].startsWith(prefix)) {
				understood = true;
			}
			else if (parseMsg[0].startsWith(prefix)) {
				for (Command cmd : commands) {
					if (parseMsg[0].equalsIgnoreCase(prefix + cmd.getAlias())) {
						String msg1 = dateTime + " ::: " + e.getUser().getNick() + " issued the " + cmd.getAlias()

						              + " Command";
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




