package com.badwater.bot.commands;

import com.badwater.bot.helpers.RSSReader;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
@SuppressWarnings("DefaultFileTemplate") public class NewsCommand implements Command<MessageEvent> {
	private final RSSReader reader = new RSSReader ();

	@Override public void exec(MessageEvent e) throws Exception {
		String user = e.getUser ().getNick ();
		String[] args = helperFuncs.toArgs ( e.getMessage () );
		if ( args[1].equalsIgnoreCase ( "add" ) ) {
			String name = args[2];
			String url = args[3];
			reader.addSource ( e, name, url );
		}

		else if ( args[1].equalsIgnoreCase ( "get" ) ) {
			//noinspection unchecked
			ArrayList<String> argList = new ArrayList ();
			for ( int argNum = 2; argNum < args.length; argNum++ ) {
				argList.add ( args[argNum] );
			}

			reader.showNews ( e, argList.toArray ( new String[argList.size ()] ) );


		}

		else if ( args[1].equalsIgnoreCase ( "update" ) ) {
			reader.update ( e );
		}


	}

	@Override public String getAlias() {
		return "news";
	}
}
