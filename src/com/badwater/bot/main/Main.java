package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.Listeners.BotInfoListener;
import com.badwater.bot.core.Listeners.Listener;
import com.badwater.bot.helpers.Readers.bwFileReader;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

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



	public static void main1(String args[]) throws SQLException, IOException, IrcException, ClassNotFoundException {
		HashMap<String, String[]> configData = new bwFileReader(
			   "./DB/Configs/BotConfigs/Badwater_Bill.conf").getConfigData();
		Configuration c = new Configuration.Builder().setServerHostname(
			   configData.get("Server")[0]).setLogin(
			   configData.get("Login")[0]).setNickservPassword(configData.get("Password")[0]).setName(
			   configData.get("Name")[0]).addAutoJoinChannel("#0x").addAutoJoinChannel(
			   "#badwater").addAutoJoinChannel("#testroom01").addListener(new Listener()).buildConfiguration();

		bot = new BadwaterBot(c);
		bot.startBot();

	}


}

