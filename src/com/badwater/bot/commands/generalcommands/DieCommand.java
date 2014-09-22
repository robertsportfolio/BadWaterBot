package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class DieCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();

	public DieCommand() {
		helpStrings.add("Kills The Bot");
	}

	@Override
	public void exec(MessageEvent e) throws Exception {

		if (e.getUser().getNick().equalsIgnoreCase("irinix") && e.getUser().isVerified()) {
			e.getChannel().send().message("Okay: " + e.getUser().getNick() + " I'm Going Away Now!");
			e.getChannel().getBot().sendIRC().quitServer("Was Told To Go Away");
		}
		else {
			e.getChannel().send().message("I'm sorry " + e.getUser().getNick() + " Only Irinix can do that!");
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
