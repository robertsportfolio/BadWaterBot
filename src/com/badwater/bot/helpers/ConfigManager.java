package com.badwater.bot.helpers;

import com.badwater.bot.core.Listener;
import org.pircbotx.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by irinix on 8/8/14.
 */
public class ConfigManager {
	private static final String PATH_TO_CONFIG = "./DB/Configs/SavedConfigs.conf";
	private HashMap<String, Configuration> savedConfigs = new HashMap<String, Configuration> ();
	private Configuration defaultConfig = new Configuration.Builder ().setName ( "badWater_Bot" )
	                                                                  .setLogin ( "badWaterBot" )
	                                                                  .setServerHostname ( "irc.freenode.net" )
	                                                                  .addAutoJoinChannel ( "#badwater" )
	                                                                  .setCapEnabled ( false )
	                                                                  .addListener ( new Listener () )
	                                                                  .setAutoNickChange ( true )
	                                                                  .buildConfiguration ();


	public Configuration createConfig(String name,
	                                  String login,
	                                  String server,
	                                  String channel,
	                                  String password,
	                                  ArrayList<Listener> listeners,
	                                  boolean capEnabled,
	                                  boolean nickChange,
	                                  boolean reconnect) {

		Configuration.Builder configBuilder = new Configuration.Builder ().setName ( name )
		                                                                  .setLogin ( login )
		                                                                  .setServerHostname ( server )
		                                                                  .addAutoJoinChannel ( channel )
		                                                                  .setNickservPassword ( password )
		                                                                  .setCapEnabled ( capEnabled )
		                                                                  .setAutoNickChange ( nickChange )
		                                                                  .setAutoReconnect ( reconnect );
		for ( Listener l : listeners ) {
			configBuilder.addListener ( l );
		}
		Configuration config;
		config = configBuilder.buildConfiguration ();
		return config;
	}

	public Configuration getConfig(String name) {
		Configuration tmpConfig;
		if ( savedConfigs.containsKey ( name ) ) {
			return savedConfigs.get ( name );
		}
		else {
			return defaultConfig;
		}
	}

	/*public void saveConfigToFile(Configuration config) throws IOException {
		File file = new File(PATH_TO_CONFIG);
		//just a little hack to make sure the file exists before we try to save to it.
		if(!file.exists () ){
			FileOutputStream out = new FileOutputStream ( PATH_TO_CONFIG );
			out.write ( 1 );
			out.close();
		}
		FileOutputStream out = new FileOutputStream ( PATH_TO_CONFIG );
		ObjectOutputStream savedConfig = new ObjectOutputStream ( out );
		savedConfig.writeObject ( this.savedConfigs );

		//in reverse order now.
		savedConfig.close ();
		out.close ();
	}

	public void loadConfigFromFile() throws IOException, ClassNotFoundException {
		//Open a File Input Stream, and then use that to create a ObjectInput Stream

		File file = new File ( PATH_TO_CONFIG );
		if ( file.exists () ) {
			FileInputStream in = new FileInputStream ( PATH_TO_CONFIG );
			ObjectInputStream loadedConfig = new ObjectInputStream ( in );

			if ( loadedConfig.readObject ().getClass () != savedConfigs.getClass () ) {
				System.out.println ( "Error Loading Config.  Resetting to default" );
				saveConfigToFile ( new HashMap<String, Configuration> ().put ( "default", defaultConfig ) );
			}
			else {
				this.savedConfigs = (HashMap<String, Configuration>) loadedConfig.readObject ();
			}
			//Close Streams in reverse order
			loadedConfig.close ();
			in.close ();
		}
		else {
			System.out.println ( "File: " + PATH_TO_CONFIG + " Does Not Exist." );
		}
	}
	*/
	public void addConfig(Configuration config) {
		if ( !savedConfigs.containsKey ( config.getName () ) ) {
			savedConfigs.put ( config.getName (), config );
		}
		else {
			System.out.println (
				   "Sorry, Config Already Exists, please run Save Config Again, and choose a new name." );

		}
	}


}
