package com.badwater.bot.helpers;

import com.badwater.bot.core.Listener;
import org.pircbotx.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by irinix on 8/8/14.
 */
public class ConfigManager {
	private static final String                         PATH_TO_CONFIG = "./DB/Configs/";
	private final        HashMap<String, Configuration> savedConfigs   = new HashMap<String, Configuration>();
	private final        Configuration                  defaultConfig  = new Configuration.Builder().setName(
		   "badWater_Bot")
		   .setLogin("badWaterBot")
		   .setServerHostname("irc.freenode.net")
		   .addAutoJoinChannel("#badwater")
		   .setCapEnabled(false)
		   .addListener(new Listener())
		   .setAutoNickChange(true)
		   .buildConfiguration();

// --Commented out by Inspection START (8/20/14 7:02 PM):
//	public Configuration createConfig(String name,
//	                                  String login,
//	                                  String server,
//	                                  String channel,
//	                                  String password,
//	                                  ArrayList<Listener> listeners,
//	                                  boolean capEnabled,
//	                                  boolean nickChange,
//	                                  boolean reconnect) {
//
//		Configuration.Builder configBuilder = new Configuration.Builder ().setName ( name )
//		                                                                  .setLogin ( login )
//		                                                                  .setServerHostname ( server )
//		                                                                  .addAutoJoinChannel ( channel )
//		                                                                  .setNickservPassword ( password )
//		                                                                  .setCapEnabled ( capEnabled )
//		                                                                  .setAutoNickChange ( nickChange )
//		                                                                  .setAutoReconnect ( reconnect );
//		for ( Listener l : listeners ) {
//			configBuilder.addListener ( l );
//		}
//		Configuration config;
//		config = configBuilder.buildConfiguration ();
//
//		return config;
//	}
// --Commented out by Inspection STOP (8/20/14 7:02 PM)

	public void saveConfigToFile() throws IOException {
		File file = new File(PATH_TO_CONFIG);

		if (!file.exists()) {

			file.mkdir();
		}

		String line = "";
		String tmp = "";
		String out = "";
		for (String key : savedConfigs.keySet()) {
			System.out.println(key);
			file = new File(PATH_TO_CONFIG + key + ".conf");
			if (file.exists()) {
				file.delete();
			}
			BufferedWriter fos = new BufferedWriter(new FileWriter(file));
			fos.write("[" + key + "]\n");
			String name = "Name=" + savedConfigs.get(key).getName() + "\n";
			String login = "Login=" + savedConfigs.get(key).getLogin() + "\n";
			String server = "Server=" + savedConfigs.get(key).getServerHostname() + "\n";
			String channel = savedConfigs.get(key).getAutoJoinChannels() + "\n";
			channel = channel.replace("{", "");
			channel = channel.replace("}", "");
			channel = channel.replace("=", "");
			channel = "Channel=" + channel;
			String password = "Password=" + savedConfigs.get(key).getNickservPassword() + "\n";
			String capEnabled = "CapEnabled=" + savedConfigs.get(key).isCapEnabled() + "\n";
			String autoNickChange = "IsAutoNickChange=" + savedConfigs.get(key).isAutoNickChange() + "\n";
			String autoReconnect = "IsAutoReconnect=" + savedConfigs.get(key).isAutoReconnect() +
				   "\n";
			String listeners = "{Listeners}\n";
			for (Object l : (savedConfigs.get(key).getListenerManager().getListeners())) {
				String listener = l.getClass().toString();
				if (listener.contains("org.pircbotx")) {
					listener = "";
				}
				else {
					listeners += listener + "\n";
				}
			}
			out = name + login + server + channel + password + capEnabled + autoNickChange + autoReconnect
				   + listeners;
			fos.write(out + "\n\n");
			fos.close();
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
		if (!savedConfigs.containsKey(config.getName())) {
			savedConfigs.put(config.getName(), config);
		}
		else {
			System.out.println(
				   "Sorry, Config Already Exists, please run Save Config Again, and choose a new name.");

		}
	}


}
