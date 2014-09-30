package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.Listeners.BotInfoListener;
import com.badwater.bot.core.Listeners.Listener;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
	//bot shit. stuff will be added here.
	private static BadwaterBot bot;

	public static void main(String args[]) throws IOException, IrcException, ClassNotFoundException {
		Configuration c = new Configuration.Builder().setServerHostname("chat.freenode.net")
			   .setName("BadWater_Test").setAutoNickChange(true).addAutoJoinChannel("#testroom01").addListener(
					 new Listener()).setLogin("BW_TESTBOT").buildConfiguration();
		bot = new BadwaterBot(c);
		bot.getConfiguration().getListenerManager().addListener(new BotInfoListener(bot));
		bot.startBot();
	}


}

