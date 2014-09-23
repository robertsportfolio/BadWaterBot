package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.BadwaterBot;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/20/14.
 */
public class CreditsCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();

	public CreditsCommand() {
		addHelpStrings();
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		BadwaterBot b = (BadwaterBot) event.getBot();
		b.sendIRC().notice(event.getUser().getNick(),
		                   "My Creator is Irinix.  " +
			                      "Also, My Lord and Master would like to thank everybody in #Reddit-GameDev, " +
			                      "and ##Java for their time, patience and help in getting me off the ground, " +
			                      "and giving ideas for new features.  If you have questions for the Great and " +
			                      "Almighty Irinix, please send an email to rstr5105.forums@gmail.com.  He may " +
			                      "or may not respond, at his leisure.  Thank you for Using "
			                      + event.getBot().getNick() + " Version: " + b.getVersion());
	}

	@Override
	public String getAlias() {
		return "Credits";
	}

	@Override
	public ArrayList<String> getHelpString() {
		return helpStrings;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add(" :prints the name of my creator ");


	}


}
