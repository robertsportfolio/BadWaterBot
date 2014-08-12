package com.badwater.bot.main;

import com.badwater.bot.core.BadwaterBot;
import com.badwater.bot.core.BotManager;
import com.badwater.bot.core.ManagerListener;
import org.apache.derby.jdbc.EmbeddedDriver;
import org.pircbotx.Configuration;
import org.pircbotx.exception.IrcException;

import java.io.IOException;
import java.sql.*;

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
		Configuration bmgr = new Configuration.Builder<> ().setName ( "BW_BOTMAN" )
			   //.addAutoJoinChannel ( "#badwater" )
			   .setServerHostname ( "weber.freenode.net" )
			   .addListener ( new ManagerListener () )
			   .buildConfiguration ();

		BotManager botman = new BotManager ( bmgr );
		botman.startBot ();

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

