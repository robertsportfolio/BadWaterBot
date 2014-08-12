package com.badwater.bot.commands.managerCommands;

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
		System.out.println ( "Recieved PM!" + e.getMessage () );
		e.getUser ().send ().message ( "Okay, " + e.getUser ().getNick () + " I'm Going Away Now!" );
		e.getBot ().sendIRC ().quitServer ( "Was Told To Go Away" );
	}


	@Override
	public String getAlias() {
		return "die";
	}
}
