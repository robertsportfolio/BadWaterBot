package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.Listeners.BotInfoListener;
import com.badwater.bot.core.Listeners.Listener;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
//bot shit. stuff will be added here.
private static BadwaterBot bot;
private static Connection  conn;
private static String dbPath = "jdbc:mysql://54.68.79.122:3306/badwaterbill?user=badwaterbill&password=h1llz90210";

private static Connection createDBConn()
	   throws SQLException, ClassNotFoundException, IllegalAccessException, InstantiationException {


	Class.forName("com.mysql.jdbc.Driver")
		.newInstance();
	return DriverManager.getConnection(dbPath);

}

public static void main(String args[]) throws IOException, IrcException, ClassNotFoundException, SQLException {
	Configuration c = null;
	if (args[0].equalsIgnoreCase("Testing")) {
		System.out.println("Testing");
		try {
			conn = createDBConn();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}

		c = new Configuration.Builder().setServerHostname("chat.freenode.net")
								 .setName("BadWater_Test")
								 .setAutoNickChange(true)
								 .addAutoJoinChannel("#testroom01")
								 .addListener(new Listener(conn))
								 .setLogin("BW_TESTBOT")
								 .buildConfiguration();
		bot = new BadwaterBot(c);
		bot.getConfiguration()
		   .getListenerManager()
		   .addListener(new BotInfoListener(bot, conn));


	}
	else if (args[0].equalsIgnoreCase("Production")) {
		c = new Configuration.Builder().setServerHostname("chat.freenode.net")
								 .setName("BadWater_Bill")
								 .setNickservPassword("givem3thecookie")
								 .setAutoNickChange(true)
								 .addAutoJoinChannel("#badwater")
								 .addAutoJoinChannel("#0x")
								 .addListener(
												 new Listener(conn))
								 .setLogin("BW_Bill")
								 .buildConfiguration();
	}
	bot = new BadwaterBot(c);
	bot.startBot();

}


}



