package com.badwater.bot.core.Listeners;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.generalcommands.*;
import com.badwater.bot.helpers.Authenticator.Authenticator;
import com.badwater.bot.helpers.Loggers.Logger;
import com.badwater.bot.helpers.helperFuncs;
import com.badwater.bot.info.BotInfo;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by irinix on 8/3/14.
 */

public class Listener extends ListenerAdapter {
	protected ArrayList<Command> commands    = new ArrayList<>();
	private   ArrayList<BotInfo> botRegistry = new ArrayList<BotInfo>();
	protected String             prefix      = "?";
	private Logger        logger;
	private Authenticator authenticator;

	private class QueueTask extends TimerTask {
		private long count = 0;
		private long increment;

		public QueueTask(long pIncrement) {
			count = 0;
			increment = pIncrement;
		}

		@Override
		public void run() {
			count += increment;

		}

		public long getCount() {
			return count;
		}
	}


	public Listener() throws IOException {
		authenticator = new Authenticator();

		commands.add(new DieCommand());
		commands.add(new NewsCommand());
		commands.add(new JoinCommand());
		commands.add(new LeaveCommand());
		commands.add(new GoogleCommand());
		commands.add(new SayCommand());
		commands.add(new SourceCommand());
		commands.add(new CreditsCommand());
		commands.add(new PMCommand());


		//This must be the last command added
		commands.add(new HelpCommand(this));
		logger = new Logger("./Logs/IRC/", 1);

	}

	public void onJoin(JoinEvent e) throws InterruptedException {

		//on a join event, wait for the user to announce itself as a bot.
		e.respond(e.getUser().getHostmask());
		String joiningNick = e.getUser().getNick();
		String hostMask = e.getUser().getHostmask();
		e.respond(joiningNick + " : " + hostMask);
		e.getBot().sendIRC().message("irinix", joiningNick + " has joined a channel");

		if (!joiningNick.equalsIgnoreCase(e.getBot().getNick())) {
			String response = "";
			Pattern p = Pattern.compile(".+/bot/.+$");
			Matcher m = p.matcher(hostMask);
			if (m.matches()) {
				System.out.println(m.matches());

			}
		}
		for(BotInfo b : botRegistry){
			System.out.println(b.getName());
			for(String s : b.getCaps()){
				System.out.println(s);
			}
		}
	}

	private BotInfo getBotInfo(JoinEvent e, String pUserName, String pMsg) throws InterruptedException {
		String response = "";
		String msg = pMsg;
		String userName = pUserName;
		long TimeOut = 3000l;
		long period = 20l;
		Timer t = new Timer();
		QueueTask q = new QueueTask(period);
		e.getBot().sendIRC().message(userName, msg);
		WaitForQueue queue = new WaitForQueue(e.getBot());
		t.scheduleAtFixedRate(q, 0, period);
		while (q.getCount() < TimeOut + period) {
			System.out.println(q.getCount());
			PrivateMessageEvent currEvent = queue.waitFor(PrivateMessageEvent.class);
			if (!currEvent.getUser().getNick().equalsIgnoreCase(userName)) {
				continue;
			}
			else if (msg.equalsIgnoreCase("SYN")) {
				if (!currEvent.getMessage().equalsIgnoreCase("ACK")) {
					currEvent.getBot().sendIRC().message(currEvent.getUser().getNick(), "Invalid Response");

					response = null;
					t.cancel();
					queue.close();
				}
				else{
					e.getBot().sendIRC().message("irinix", "Recieved ACK FROM: " + userName);
				}
			}
			else if (msg.equalsIgnoreCase("CAPS")) {
				response = currEvent.getMessage();
				System.out.println("Recieved: " + response + " From: " + currEvent.getUser().getNick());
				t.cancel();
				queue.close();
			}
		}



	return response;
}


	public void onMessage(MessageEvent e) throws Exception {

		String userName = e.getUser().getNick();
		if (e.getChannel().getName().contains("BWGame")) {
			//just ignore the message if it's in a game channel.
		}
		else if (e.getMessage().equalsIgnoreCase("Hi Bill") || e.getMessage().equalsIgnoreCase(
			   "Hi " + e.getBot().getNick())) {

			e.getChannel().send().message("Hi " + userName);
		}
		else {
			String[] parseMsg = helperFuncs.toArgs(e.getMessage());

			if (parseMsg[0].startsWith(prefix) && !parseMsg[0].equals(prefix)) {
				String command = parseMsg[0].replace(prefix, "");


				if (!processCommands(command)) {
					e.getChannel().send().message("I'm Sorry, " + userName + " I Don't Understand: " + command);
				}
				else {
					Command c = getCommand(command);
					if (!c.equals(null)) {
						boolean Authorization = true;
						if (c.requiresAuthentication()) {

							Authorization = authenticator.isUserAuthorizedForCmd(userName, command);
						}
						System.out.println(e.getUser().getNick() + ": " + Authorization);
						if (Authorization) {
							c.exec(e);
						}
						else {
							e.getChannel().send().message(
								   "I'm sorry, " + e.getUser().getNick() + " I can't let you do that!");
						}

					}
				}
			}
		}


		logger.log("<" + userName + "> " + e.getMessage());
	}

	private Command getCommand(String command) {

		for (Command c : commands) {
			if (command.equalsIgnoreCase(c.getAlias())) {
				return c;
			}
		}
		//should never happen
		return null;
	}


	public ArrayList<Command> getCommands() {
		return commands;
	}

	public String getPrefix() {
		return prefix;
	}


	private boolean processCommands(String command) throws Exception {
		boolean success = false;
		for (Command c : commands) {
			if (command.equalsIgnoreCase(c.getAlias())) {
				success = true;
			}
		}
		return success;
	}

	public Authenticator getAuthenticator() {
		return authenticator;
	}
}


