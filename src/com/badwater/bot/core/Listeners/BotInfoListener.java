package com.badwater.bot.core.Listeners;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.helpers.BWTimers.bwTimerTask;
import com.google.common.collect.ImmutableSortedSet;
import org.pircbotx.User;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.JoinEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.Timer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by irinix on 9/29/14.
 */
public class BotInfoListener extends ListenerAdapter {

	private        boolean synReceived = false;
	private static Timer   t           = new Timer();
	ImmutableSortedSet<User> users;
	private BadwaterBot bot;

	public BotInfoListener(BadwaterBot b) {
		bot = b;
	}

	public void onJoin(JoinEvent e) throws InterruptedException {
		String joiningNick = e.getUser().getNick();
		String hostMask = e.getUser().getHostmask();

		Pattern p = Pattern.compile(".+/bot/.+$");


		Timer t = new Timer();
		bwTimerTask q = new bwTimerTask();
		long timeOut = 10000l;
		long period = 200l;

		if (joiningNick.equalsIgnoreCase(bot.getNick())) {
			//if we're the ones joining a channel, get the list of users.

			users = e.getChannel().getUsers();

			t.scheduleAtFixedRate(q, 0, period);
			//wait ten seconds for "SYN
			while (q.getCount() < timeOut) {
				//If it hasn't come in yet, keep waiting.

				long timeLeft = (timeOut - q.getCount()) / 1000;
				if (!synReceived) {
					System.out.println("Syn Not Recieved.  Waiting: " + timeLeft + " More Seconds");
					continue;

				}
				else {
					synReceived = true;
					System.out.println("Syn Recieved in: " + ((timeOut / 1000) - timeLeft) + " Seconds");
					t.cancel();
					break;
				}
			}
			if (!synReceived) {
				System.out.println("Syn Not Recieved, pinging Bots!");
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


		//if somebody else is joining the channel, if they're a bot, send syn
		else if (!joiningNick.equalsIgnoreCase(bot.getNick())) {
			
			Matcher m = p.matcher(hostMask);
			if (m.matches()) {
				System.out.println(m.matches());
				if(sendSyn(e.getUser())){
					sendCapsRequest(e.getUser());
				}
			}

		}
		//On a join, ask if the user is a bot.
		e.respond(joiningNick + " : " + hostMask);

	}

	private void sendCapsRequest(User user) {
	}


	private boolean sendSyn(User u) throws InterruptedException {
		u.send().message("SYN");
		return WaitForReply(u, "ACK");
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
