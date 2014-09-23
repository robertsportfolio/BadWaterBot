package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.Loggers.Logger;
import com.badwater.bot.helpers.Readers.bwFileReader;
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
	ArrayList<String> helpStrings     = new ArrayList<String>();

	public DieCommand() throws IOException {
		logger = new Logger("./Logs/ServerLogs/", 0);
		in = new bwFileReader("./DB/Configs/.authUsers");
		in.read();
		addHelpStrings();
	}

	@Override
	public void exec(MessageEvent e) throws Exception {
		User issuingUser = e.getUser();
		String issuingUserName = e.getUser().getNick();
		if (!checkAuthorizedUsers(issuingUser)) {
			dennisNedry(e);
			e.getChannel().send().kick(issuingUser, "Don't Fuck With The Bot!  " +
				   "Please Note That Continued Abuse may result in the banhammer dropping.");
			logger.log(e.getChannel().getName(),
			           issuingUserName + " tried to shutdown the bot without authorization.");
		}
		else {
			e.getChannel().send().message("Okay " + issuingUserName + " I'll Go Away Now.");
			e.getBot().sendIRC().quitServer("Was Told To Go Away!");
			logger.log(e.getChannel().getName(), "Was Told To Quit By: " + issuingUserName);


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
	public ArrayList<String> getHelpString() {
		return helpStrings;
	}

	@Override
	public void addHelpStrings() {
		helpStrings.add(" :Kills The Bot");
		helpStrings.add(
			   " :Misuse of this command, may result in a perma-ban from any channel registered to Badwater.)");
		helpStrings.add(" :Try it if you do not believe me.");


	}


}
