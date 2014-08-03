package com.badwater.bot.core;

import com.badwater.bot.commands.Command;
import com.badwater.bot.commands.DieCommand;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class Listener extends ListenerAdapter {
	private ArrayList<Command> commands = new ArrayList<> ();
	private String prefix = "?";

	public Listener() {
		commands.add ( new DieCommand () );
	}

	public void onMessage(MessageEvent e) throws Exception {

		if ( !e.getMessage ().startsWith ( prefix ) ) {
			System.out.println(e.getMessage());
			return;
		}
		for ( Command cmd  : commands ) {
			if ( e.getMessage ().equalsIgnoreCase ( prefix + cmd.getAlias () ) ) {
				cmd.exec ( e );
			}
		}

	}
}
