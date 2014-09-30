package com.badwater.bot.core.Listeners;

import com.badwater.bot.core.BadwaterBot;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.io.IOException;

/**
 * Created by irinix on 8/19/14.
 */
public class LearnerListener extends ListenerAdapter {
	BadwaterBot b;

	public LearnerListener() {

	}

	public void onMessage(MessageEvent e) throws IOException, ClassNotFoundException {
		if (!e.getMessage().startsWith("?") || !e.getMessage().startsWith("!")) {
			b = (BadwaterBot) e.getBot();
			String msg1 = "New Markov Chain Acquired From String \"" + e.getMessage() + "\"";
			//b.getLearner ().learn ( ( e.getMessage () ) );
			System.out.println ( msg1 );
		}
	}

	public void onPrivateMessage(PrivateMessageEvent e) throws IOException, ClassNotFoundException {
		if ( !e.getMessage ().startsWith ( "?" ) ) {
			b = (BadwaterBot) e.getBot ();
			String msg1 = "New Markov Chain Acquired From String \"" + e.getMessage () + "\"";
			//b.getLearner ().learn ( ( e.getMessage () ) );
			System.out.println ( msg1 );
		}
	}
}