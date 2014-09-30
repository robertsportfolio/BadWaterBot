package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.helperFuncs;
import org.apache.commons.lang3.time.StopWatch;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class PingCommand implements Command<MessageEvent> {
	private final long TIMEOUT_MILLIS = 3000l;
	ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> notesList = new ArrayList<String>();

	public PingCommand() {

		addHelpStrings();
		addNoteStrings();
	}

	private void addNoteStrings() {
		notesList.add("Experimental.  Not sure if it works yet");
	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		User issuingUser = e.getUser();
		String[] parseMsg = helperFuncs.toArgs(e.getMessage());

		String issuingUserName = e.getUser().getNick();

		if (parseMsg.length <= 1) {
			pingTarget(e, issuingUser, issuingUserName);
		}
		else if (parseMsg.length >= 2) {
			pingTarget(e, issuingUser ,parseMsg[1]);
		}



	}


	private void pingTarget(MessageEvent e, User issuingUser, String target) throws InterruptedException {
		e.getBot().sendRaw().rawLine("ping " + target );
		StopWatch sw = new StopWatch();
		sw.start();


	}

	@Override
	public String getAlias() {
		return "ping";
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
	public void addHelpStrings() {
		helpStrings.add("Use: Ping Yourself, or Another User");
		helpStrings.add("Syntax: ?Ping <user>(optional)");


	}


}
