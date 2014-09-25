package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.Loggers.Logger;
import com.badwater.bot.helpers.Readers.bwFileReader;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.User;
import org.pircbotx.hooks.events.MessageEvent;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class DieCommand implements Command<MessageEvent> {
	private final bwFileReader in;
	private final Logger       logger;
	ArrayList<String> helpStrings = new ArrayList<String>();
	private ArrayList<String> notesList = new ArrayList<String>();

	public DieCommand() throws IOException {
		logger = new Logger("./Logs/ServerLogs/", 0);
		in = new bwFileReader("./DB/Configs/.authUsers");
		in.read();
		addHelpStrings();
		addNoteStrings();
	}

	private void addNoteStrings() {
		notesList.add("Misuse of this command, may result in a ");
		notesList.add("perma-ban from any channel registered to Badwater.)");
		notesList.add("Try it if you do not believe me.");
	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		User issuingUser = e.getUser();
		String chanName = e.getChannel().getName();
		String[] parseMsg = helperFuncs.toArgs(e.getMessage());

		String issuingUserName = e.getUser().getNick();
		if (!checkAuthorizedUsers(issuingUser)) {
			dennisNedry(e);
			e.getChannel().send().kick(issuingUser, "Don't Fuck With The Bot!  " +
				   "Please Note That Continued Abuse may result in the banhammer dropping.");
			logger.log(e.getChannel().getName(),
			           issuingUserName + " tried to shutdown the bot without authorization.");
		}
		else {

			if (parseMsg.length <= 1) {
				logger.log(chanName, "Was Told To Quit By: " + issuingUserName);
			}
			else if (parseMsg.length > 1) {
				String reason = "";
				for (int i = 1; i < parseMsg.length; i++) {
					reason += parseMsg[i] + " ";
				}
				logger.log(chanName, "Was Told To Quit By: " + issuingUserName + "For: " + reason);
			}

			e.getChannel().send().message("Okay " + issuingUserName + " I'll Go Away Now.");
			e.getBot().sendIRC().quitServer("Was Told To Go Away!");
		}
	}

	private void dennisNedry(MessageEvent e) {
		for (int i = 0; i < 10; i++) {
			e.getUser().send().notice("AH, AH, AH!  You didn't say the magic word.");
		}
	}

	private boolean checkAuthorizedUsers(User user) {
		boolean userAuthorized = false;
		System.out.println(user.getNick());
		for (String authUserName : in.getLines()) {
			System.out.print(authUserName);
			if (user.getNick().equalsIgnoreCase(authUserName) && user.isVerified()) {
				userAuthorized = true;
				break;
			}


		}
		return userAuthorized;
	}


	@Override
	public String getAlias() {
		return "die";
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
		return "Kills The Bot";
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add("Use: Kills The Bot");
		helpStrings.add("Syntax: ?die <reason>(optional)");


	}


}
