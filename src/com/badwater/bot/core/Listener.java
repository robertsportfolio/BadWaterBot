package com.badwater.bot.core;

import com.badwater.bot.commands.*;
import com.badwater.bot.helpers.helperFuncs;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by irinix on 8/3/14.
 */
public class Listener extends ListenerAdapter {
	protected ArrayList<Command> commands = new ArrayList<> ();
	protected String prefix = "?";
	protected boolean respondState = true;

	public Listener() {
		commands.add ( new DieCommand () );
		commands.add ( new NewsCommand () );
		commands.add ( new JoinCommand () );
		commands.add ( new HelpCommand () );
	}

	public void respondToCommands(boolean state) {
		respondState = state;
	}

	public void onMessage(MessageEvent e) throws Exception {
		if ( e.getMessage ().equalsIgnoreCase ( prefix + "learn on" ) && e.getUser ()
		                                                                  .getNick ()
		                                                                  .equalsIgnoreCase ( "irinix" )
		     && e.getUser ().isVerified () ) {
			e.respond ( "Okay, I'm going into Learn Mode Now.  " );
			respondState = false;
		}
		if ( respondState == true ) {
			String[] parseMsg = helperFuncs.toArgs ( e.getMessage () );
			String dateTime = new Date ().toString ();
			String msg = dateTime + " ::: " + e.getChannel ().getName () + " :: " + e.getUser ().getNick () + " : "
			             + e.getMessage ();
			boolean understood = false;
			if ( !parseMsg[0].startsWith ( prefix ) ) {
				System.out.println ( msg );
				understood = true;
				return;
			}
			else if ( parseMsg[0].startsWith ( prefix ) ) {
				for ( Command cmd : commands ) {
					if ( parseMsg[0].equalsIgnoreCase ( prefix + cmd.getAlias () ) ) {
						String msg1 =
							   dateTime + " ::: " + e.getUser ().getNick () + " issued the " + cmd.getAlias ()
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
				e.getChannel ().send ().message ( tmpMessage );
			}

		}
		else if ( !respondState && !e.getMessage ().equalsIgnoreCase ( "?learn off" ) ) {
			BadwaterBot b = (BadwaterBot) e.getBot ();
			String msg1 = "New Markov Chain Aquired From String \"" + e.getMessage () + "\"";
			b.getLearner ().learn ( e.getMessage () );
			System.out.println ( msg1 );
		}
		else if ( !respondState && e.getMessage ().equalsIgnoreCase ( "?learn off" ) && e.getUser ()
		                                                                                 .getNick ()
		                                                                                 .equalsIgnoreCase (
			                                                                                    "irinix" ) && e.getUser ().isVerified () ) {
			respondState = true;
		}
	}

}
