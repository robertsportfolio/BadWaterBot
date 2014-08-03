package com.badwater.bot.commands;

import org.pircbotx.hooks.events.MessageEvent;
import com.badwater.bot.core.BadwaterBotCore;

/**
 * Created by irinix on 8/3/14.
 */
public class DieCommand implements Command<MessageEvent> {

	@Override
	public void exec(MessageEvent e) throws Exception {
		e.getChannel ().getBot ().sendIRC ().quitServer ( "Was Told To Go Away" );
	}

	@Override
	public String getAlias() {
		return "die";
	}
}
