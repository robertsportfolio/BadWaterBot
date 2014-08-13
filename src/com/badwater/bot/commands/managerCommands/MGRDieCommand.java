package com.badwater.bot.commands.managerCommands;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.BotManager;
import org.pircbotx.hooks.events.MessageEvent;
import org.pircbotx.hooks.events.PrivateMessageEvent;

/**
 * Created by irinix on 8/3/14.
 */
public class MGRDieCommand implements MGRCommand<PrivateMessageEvent> {

	public void exec(MessageEvent e) throws Exception {

		System.out.println ( "Message Recieved!" + e.getMessage () );
		e.getUser ().send ().message ( "Okay, " + e.getUser ().getNick () + " I'm Going Away Now!" );
		e.getBot ().sendIRC ().quitServer ( "Was Told To Go Away" );
	}

	public void exec(PrivateMessageEvent e) throws Exception {
		BotManager bm = (BotManager) e.getBot ();
		System.out.println ( "Recieved PM!" + e.getMessage () );
		for ( BadwaterBot b : bm.getManagedBots () ) {
			b.die ( e.getUser ().getNick () );
		}
		e.getUser ().send ().message ( "Okay, " + e.getUser ().getNick () + " I'm Going Away Now!" );
		e.getBot ().sendIRC ().quitServer ( "Was Told To Go Away" );
	}


	@Override
	public String getAlias() {
		return "die";
	}
}
