package com.badwater.bot.commands.generalcommands;

import com.badwater.bot.commands.Command;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/11/14.
 */
public class JoinCommand implements Command<MessageEvent> {
	ArrayList<String> helpStrings = new ArrayList<String>();

	public JoinCommand() {
		helpStrings.add("Joins a new Channel.");
		helpStrings.add("Join #<channelName>");
	}
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

	@Override
	public ArrayList<String> getHelpString() {

		return helpStrings;
	}
}
