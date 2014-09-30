package com.badwater.bot.core.Listeners;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.helpers.BWTimers.bwTimerTask;
import com.badwater.bot.info.BotInfo;
import com.google.common.collect.ImmutableSortedSet;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.ArrayList;
import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by irinix on 9/29/14.
 */
public class BotInfoListener extends ListenerAdapter {

	private BotInfo thisInfo;
	private        ArrayList<BotInfo> botRegistry = new ArrayList<BotInfo>();
	private        boolean            synReceived = false;
	private static Timer              t           = new Timer();
	ImmutableSortedSet<User> users;
	private BadwaterBot bot;

	public BotInfoListener(BadwaterBot b) {
		bot = b;
	}

	public void onJoin(JoinEvent e) throws InterruptedException {
		String joiningNick = e.getUser().getNick();
		String hostMask = e.getUser().getHostmask();
		users = e.getChannel().getUsers();

		Pattern p = Pattern.compile(".+/bot/.+$");


		//if we're the ones joining a channel, get the list of users.
		Timer t = new Timer();
		bwTimerTask q = new bwTimerTask();
		if (joiningNick.equalsIgnoreCase(bot.getNick())) {
			t.scheduleAtFixedRate(q, 0, 20);
			//wait ten seconds for "SYN
			long timeOut = 10000l;
			while (q.getCount() < timeOut) {
				//If it hasn't come in yet, keep waiting.

				long timeLeft = (timeOut - q.getCount()) / 1000;
				if (!synReceived) {
					System.out.println("Syn Not Recieved.  Waiting: " + timeLeft + " More Seconds");

				}
				else {
					System.out.println("Syn Recieved in: " + ((timeOut / 1000) - timeLeft) + " Seconds");
					t.cancel();
					break;
				}
				if (!synReceived) {
					//stop the timer.
					t.cancel();
					//check the userlist for bots.
					for (User u : users) {
						Matcher m = p.matcher(u.getHostmask());
						//if a bot is found by hostMask
						if (m.matches()) {
							//send Syn()
							sendSyn(u);
							if (WaitForReply(u, "ack")) {
								sendCaps(u);
							}
							else {
								bot.sendIRC().message("Irinix", u.getNick() + " TIMED OUT!");

							}

						}
					}
				}
			}
		}

		//if somebody else is joining the channel, if they're a bot, send syn
		else

		{
			if (!joiningNick.equalsIgnoreCase(bot.getNick())) {
				String response = "";
				Matcher m = p.matcher(hostMask);
				if (m.matches()) {
					System.out.println(m.matches());
					sendSyn(e.getUser());
				}

			}
			//On a join, ask if the user is a bot.
			e.respond(joiningNick + " : " + hostMask);

		}

	}


	private void sendSyn(User u) throws InterruptedException {
		u.send().message("SYN");

	}

	private void sendACK(User u) {
		u.send().message("ACK");
	}

	public void onPrivateMessage(PrivateMessageEvent e) throws InterruptedException {

		if (e.getMessage().equalsIgnoreCase("SYN")) {
			synReceived = true;
			sendACK(e.getUser());
		}
		if (synReceived) {
			if (e.getMessage().equalsIgnoreCase("CAPS")) {
				sendCaps(e.getUser());
				//reset SYNReceived
				synReceived = false;
			}

		}
	}

	private void sendCaps(User u) throws InterruptedException {
		String capsReply = "NONE";
		if (!thisInfo.equals(null)) {
			if (!thisInfo.getCaps().isEmpty()) {
				capsReply = "";

				for (String s : thisInfo.getCaps()) {
					capsReply += s + ",";
				}

				capsReply = capsReply.substring(0, capsReply.lastIndexOf(',') - 1);
			}
			u.send().message(capsReply);
			u.send().message("ENDSCAP");
			if (!WaitForReply(u, "ACK")) {
				System.out.println("There was an ERROR Sending BotInfo To: " + u.getNick());
			}
			else {
				System.out.println("successfully sent Bot Info to " + u.getNick());
			}
		}

	}

	private boolean WaitForReply(User u, String reply) throws InterruptedException {
		long timeOut = 3000l;
		long period = 20l;
		String UserName = u.getNick();
		boolean hasReply = false;
		bwTimerTask q = new bwTimerTask();
		t.scheduleAtFixedRate(q, 0, period);
		WaitForQueue queue = new WaitForQueue(bot);
		while (q.getCount() < timeOut) {
			long timeLeft = (timeOut - q.getCount()) / 1000;
			PrivateMessageEvent currEvent = queue.waitFor(PrivateMessageEvent.class);
			if (!currEvent.getMessage().equalsIgnoreCase(reply)) {
				hasReply = false;

				System.out.println("No Reply, Waiting: " + timeLeft + " More Seconds");
				continue;
			}
			else {
				System.out.println(
					   "Received Reply: " + reply + " from: " + UserName + " in: " + ((timeOut / 1000) - timeLeft) + " Seconds");
				hasReply = true;
				t.cancel();
				queue.close();
				break;
			}
		}
		return hasReply;
	}

}
