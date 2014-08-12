package com.badwater.bot.commands.managerCommands;

import com.badwater.bot.core.BotManager;
import com.badwater.bot.core.Listener;
import org.pircbotx.Configuration;
import org.pircbotx.hooks.events.PrivateMessageEvent;

import java.util.ArrayList;

/**
 * Created by irinix on 8/11/14.
 */
public class newBotCommand implements MGRCommand<PrivateMessageEvent> {


	public void exec(PrivateMessageEvent e) throws Exception {
		System.out.println ( "Creating new bot!" );
		BotManager bm = (BotManager) e.getBot ();
		System.out.println ( bm.getName () );
		ArrayList<Listener> l = new ArrayList<Listener> ();
		l.add ( new Listener () );
		Configuration c = bm.getConfigMgr ()
		                    .createConfig ( "BadWater_Bill", "BWBill", "weber.freenode.net", "#badwater",
		                                    "givem3thecookie", l, false, false, false );

		bm.addManagedBot ( bm.createBot ( c ) );
		bm.startManagedBots ();
	}


	@Override public String getAlias() {
		return "NewBot";
	}
}
