package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.Readers.bwFileReader;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class DieCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings     = new ArrayList<String>();
	ArrayList<String> authorizedUsers = new ArrayList<String>();
	private bwFileReader in;

	public DieCommand() {
		in = new bwFileReader("./DB/Configs/.authUsers");
		authorizedUsers = in.getLines();
		helpStrings.add(" :Kills The Bot");
		helpStrings.add(
			   " :Misuse of this command, may result in a perma-ban from any channel registered to Badwater.)");
		helpStrings.add(" :Try it if you do not believe me.");

	}

	@Override
	public void exec(MessageEvent e) throws Exception {

		if (authorizedUsers.contains(e.getUser().getNick()) && e.getUser().isVerified()) {
			e.getChannel().send().message("Okay: " + e.getUser().getNick() + " I'm Going Away Now!");
			e.getChannel().getBot().sendIRC().quitServer("Was Told To Go Away");
		}
		else {
			for (int i = 0; i < 25; i++) {
				e.getBot().sendIRC().message(e.getUser().getNick(), "AH, AH, AH!  YOU DIDN'T SAY THE MAGIC WORD!");
			}
			e.getChannel().send().kick(e.getUser(), "Don't Fuck With The Bot!");
			for (String s : authorizedUsers) {
				e.getBot().sendIRC().message("memoserv",
				                             "send " + s + " Please Add A Strike Against: " + e.getUser().getNick());
			}
		}
	}

	@Override
	public String getAlias() {
		return "die";
	}

	@Override
	public ArrayList<String> getHelpString() {
		return helpStrings;
	}
	}
