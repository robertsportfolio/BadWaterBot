package com.badwater.bot.commands;

import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by irinix on 8/3/14.
 */
public class DieCommand implements Command<MessageEvent> {

	@Override
	public void exec(MessageEvent e) throws Exception {
		e.getChannel ().send ().message ( "Okay: " + e.getUser ().getNick () + " I'm Going Away Now!" );
		e.getChannel ().getBot ().sendIRC ().quitServer ( "Was Told To Go Away" );
	}

	@Override
	public String getAlias() {
		return "die";
	}
}
