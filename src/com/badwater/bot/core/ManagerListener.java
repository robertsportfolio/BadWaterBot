package com.badwater.bot.core;

import com.badwater.bot.commands.managerCommands.MGRCommand;
import com.badwater.bot.commands.managerCommands.MGRDieCommand;
import com.badwater.bot.commands.managerCommands.newBotCommand;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by irinix on 8/11/14.
 */
public class ManagerListener extends ListenerAdapter {
	private String prefix = "!";

	private ArrayList<MGRCommand> commands = new ArrayList<MGRCommand> ();

	public ManagerListener() {
		commands.add ( new MGRDieCommand () );
		commands.add ( new newBotCommand () );
	}


	@Override public void onPrivateMessage(PrivateMessageEvent e) throws Exception {
		String[] parseMsg = helperFuncs.toArgs ( e.getMessage () );
		String dateTime = new Date ().toString ();
		String msg = dateTime + " ::: " + e.getUser ().getNick () + " : " + e.getMessage ();
		boolean understood = false;
		if ( !parseMsg[0].startsWith ( prefix ) ) {
			System.out.println ( msg );
			understood = true;
			return;
		}
		else if ( parseMsg[0].startsWith ( prefix ) ) {
			for ( MGRCommand cmd : commands ) {
				if ( parseMsg[0].equalsIgnoreCase ( prefix + cmd.getAlias () ) ) {
					String msg1 = dateTime + " ::: " + e.getUser ().getNick () + " issued the " + cmd.getAlias ()
					              + " Command";
					System.out.println ( msg );
					System.out.println ( msg1 );
					understood = true;
					cmd.exec ( e );
					break;
				}
				else {
					understood = false;
				}

			}
		}
		if ( !understood ) {
			String tmpMessage =
				   "I'm Sorry " + e.getUser ().getNick () + " I don' understand \"" + e.getMessage () + "\"";
			e.getUser ().send ().message ( tmpMessage );
		}

	}
}

