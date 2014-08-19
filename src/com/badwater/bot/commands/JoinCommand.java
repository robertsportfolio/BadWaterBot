package com.badwater.bot.commands;

import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

/**
 * Created by irinix on 8/11/14.
 */
public class JoinCommand implements Command<MessageEvent> {
	@Override public void exec(MessageEvent e) throws Exception {
		String[] parsedCmd = helperFuncs.toArgs ( e.getMessage () );

		for ( String s : parsedCmd ) {
			if ( s.startsWith ( "?join" ) ) {
				continue;
			}

			else if ( s.startsWith ( "#" ) ) {
				if ( s.contains ( "," ) ) {
					s = s.substring ( s.indexOf ( "#" ), s.indexOf ( "," ) );
					s = s.replace ( ",", "" );
				}
			}
			e.getChannel ().send ().message ( "Okay " + e.getUser ().getNick () + " I'll see you in: " + s );
			e.getBot ().sendIRC ().joinChannel ( s );
		}
	}


	@Override public String getAlias() {
		return "join";
	}
}
