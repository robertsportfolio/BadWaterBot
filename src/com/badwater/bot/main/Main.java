package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.Listener;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.sql.*;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
	private static String dbURL = "jdbc:derby:./DB/BotDB;";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static Driver dbDriver;
	private static DriverManager dMgr;

	private static BadwaterBot bot;

	public static void main(String args[]) throws SQLException, IOException, IrcException {
		createBot ();


	}


	private static void createBot() throws IOException, IrcException {
		bot = new BadwaterBot ( "Badwater_Bill", "BWBill", "givem3thecookie", "irc.freenode.net", "#badwater",
		                        new Listener (), false, false );
		bot.createAndRun ();

	}

	private static void createConnection() throws SQLException {
		try {
			dbURL = dbURL + "create=true;";
			dbDriver = new EmbeddedDriver ();
			DriverManager.registerDriver ( dbDriver );
			conn = DriverManager.getConnection ( dbURL );
		} catch (SQLException e) {
			e.printStackTrace ();
		}
		System.out.println ( "Connected!" );
	}

	private static void shutdown() throws SQLException {
		if ( conn != null ) {
			dbURL = "jdbc:derby:;shutdown=true";
			DriverManager.getConnection ( dbURL );
			conn.close ();
			System.out.println ( "System Shut Down!" );
		}
	}
}

