package com.badwater.bot.core;

import com.badwater.bot.helpers.Loggers.Logger;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.WaitForQueue;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by irinix on 8/3/14.
 */
public class GameDevBotListener extends ListenerAdapter {
	protected String  prefix       = "?";
	protected boolean respondState = true;
	private Logger logger;

	public GameDevBotListener() throws IOException {
		logger = new Logger("./Logs/GameDevLogs", 0);

	}

	public void onMessage(MessageEvent e) throws Exception {
		if (e.getChannel().getName().contains("BWGame")) {
			//just ignore the message if it's in a game channel.
		}
		else {
			if (e.getChannel().getName().equalsIgnoreCase(
				   "#reddit-gamedev") && e.getUser().getNick().equalsIgnoreCase("GameDevBot")) {
				Pattern p = Pattern.compile("\\[\\d+\\]");
				Matcher m = p.matcher(e.getMessage().substring(e.getMessage().lastIndexOf('[')));
				if (m.find()) {
					e.getBot().sendIRC().message("GamedevBot", "!text " + e.getMessage().substring(
						   e.getMessage().lastIndexOf('[', e.getMessage().lastIndexOf(']')))
						   .replace("[", "")
						   .replace("]", ""));
				}
			}

		}
	}

	public void onPrivateMessage(PrivateMessageEvent e) throws IOException, InterruptedException {

		if (e.getUser().getNick().equalsIgnoreCase("GameDevBot")) {
			WaitForQueue queue = new WaitForQueue(e.getBot());

			while (true) {
				PrivateMessageEvent pmEvent = queue.waitFor(PrivateMessageEvent.class);
				if (!pmEvent.getMessage().endsWith("[link][comment]")) {
					logger.log(e.getMessage());
					e.getBot().sendIRC().message("irinix", e.getMessage());
				}
				else if (pmEvent.getMessage().endsWith("[link][comment]")) {
					logger.log(e.getMessage());
					e.getBot().sendIRC().message("irinix", e.getMessage());

					queue.close();
					return;
				}

			}
		}

	}


}


