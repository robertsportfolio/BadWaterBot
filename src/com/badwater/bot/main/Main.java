package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.Listener;
import com.badwater.bot.helpers.ConfigManager;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by irinix on 8/3/14.
 */
public class Main {
	//database shit.  Not quite in use yet.  But will be.
	private static String dbURL = "jdbc:derby:./DB/BotDB;";
	private static Connection conn = null;
	private static Statement stmt = null;
	private static Driver dbDriver;
	private static DriverManager dMgr;


	//bot shit. stuff will be added here.
	private static BadwaterBot bot;

	public static void main(String args[]) throws SQLException, IOException, IrcException {
		ConfigManager cfgMgr = new ConfigManager ();
		ArrayList<Listener> listeners = new ArrayList<Listener> ();
		listeners.add ( new Listener () );
		Configuration c =
			   cfgMgr.createConfig ( "BadWater_Bill", "BwBill", "weber.freenode.net", "#badwater",
			                         "givem3thecookie", listeners, false, false, false );
		cfgMgr.addConfig ( c );
		c = cfgMgr.getConfig ( c.getName () );

		cfgMgr.saveConfigToFile ( c );
		createBot ( c );


	}


	private static void createBot(Configuration c) throws IOException, IrcException {
		bot = new BadwaterBot ( c );
		bot.startBot ();
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

