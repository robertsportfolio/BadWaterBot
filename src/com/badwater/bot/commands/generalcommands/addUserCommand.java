package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.core.Listeners.Listener;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 9/29/14.
 */
public class addUserCommand implements Command {
	private Listener l;
	private ArrayList<String> helpList;

	public addUserCommand(Listener listener) {
		l = listener;
	}

	@Override
	public void exec(MessageEvent event) throws Exception {
		String usrNameAndCommands = event.getMessage().substring(8).trim();
		l.getAuthenticator().addUserAuthentication(usrNameAndCommands);
	}

	@Override
	public String getAlias() {
		return "addUser";
	}

	@Override
	public ArrayList<String> getHelpList() {
		return helpList;
	}

	@Override
	public ArrayList<String> getNoteList() {
		return noteList();
	}

	private ArrayList<String> noteList() {
		return noteList();
	}

	@Override
	public String getTopicString() {
		return "Adds a new <User> with <Permissions>";
	}

	@Override
	public boolean requiresAuthentication() {
		return true;
	}

	@Override
	public void addHelpStrings() {

	}
}
