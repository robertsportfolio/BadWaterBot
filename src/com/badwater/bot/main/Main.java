package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.BadwaterBotManager;
import com.badwater.bot.core.Listener;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
	public static void main(String[] args) throws IOException, IrcException {
		BadwaterBotManager manager =
			   new BadwaterBotManager ( "Botman", "BwBotMan", "#irc.freenode.net", "#badwater", new Listener (),
			                            false, false );
		BadwaterBot BadWaterBill =
			   new BadwaterBot ( "Badwater_Bill", "bwBill", "givem3thecookie", "barjavel.freenode.net", "#badwater",
			                     new Listener (), false, false );
		manager.addManagedBot ( BadWaterBill );

	}
}
