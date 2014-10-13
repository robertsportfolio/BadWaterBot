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

	private static Timer   t           = new Timer();
	ImmutableSortedSet<User> users;
	private BadwaterBot bot;

	public BotInfoListener(BadwaterBot b) {
		bot = b;
	}

	public void onJoin(JoinEvent e) throws InterruptedException {
		User joiningUser = e.getUser();
		String joiningNick = joiningUser.getNick();
		String hostMask = joiningUser.getHostmask();

		Pattern p = Pattern.compile(".+/bot/.+$");


		Timer t = new Timer();
		bwTimerTask q = new bwTimerTask();
		long timeOut = 10000l;
		long period = 200l;

		if (joiningNick.equalsIgnoreCase(bot.getNick())) {
			//if we're the ones joining a channel, get the list of users.

			users = e.getChannel().getUsers();

			private boolean synReceived = false;
			t.scheduleAtFixedRate(q, 0, period);
			//wait ten seconds for "SYN
			while (q.getCount() < timeOut) {
				//If it hasn't come in yet, keep waiting.

				long timeLeft = (timeOut - q.getCount()) / 1000;
				if (!synReceived) {
					continue;

				}
				else {
					synReceived = true;
					System.out.println("Syn Recieved in: " + ((timeOut / 1000) - timeLeft) + " Seconds");
					t.cancel();
					break;
				}
			}
			//stop the timer.
			t.cancel();
			if (!synReceived) {
				System.out.println("Syn Not Recieved, pinging Bots!");

				//check the userlist for bots.
				for (User u : users) {
					Matcher m = p.matcher(u.getHostmask());
					//if a bot is found by hostMask
					if (m.matches()) {
						//send Syn()
						System.out.println("sending SYN to: " + u.getNick());
						if(sendSyn(u)){
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
			System.out.println("Sending SYN to: " + joiningNick);
			Matcher m = p.matcher(e.getUser().getHostmask());
			if (m.matches()&& sendSyn(e.getUser())){
				System.out.println("Received ACK from: " + joiningNick);
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
		//reset SynReceived;

		if (e.getMessage().equalsIgnoreCase("SYN")) {

			sendACK(e.getUser());
		}

	}

	private void sendCaps(User u) throws InterruptedException {
		u.send().message("NONE");
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
