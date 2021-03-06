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
	private ArrayList<String> notesList = new ArrayList<String>();

	public CreditsCommand() {
		addNoteStrings();
		addHelpStrings();
	}

	private void addNoteStrings() {
		notesList.add("NONE");
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
	public ArrayList<String> getHelpList() {
		return helpStrings;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return notesList;
	}

	@Override
	public String getTopicString() {
		return "Prints The Credits";
	}

	@Override
	public boolean requiresAuthentication() {
		return false;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Prints the name of my creator");
		helpStrings.add("Syntax: ?credits");


	}


}
