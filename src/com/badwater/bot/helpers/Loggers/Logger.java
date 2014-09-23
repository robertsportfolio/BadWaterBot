package com.badwater.bot.helpers.Loggers;

import java.io.*;
import java.util.Date;

/**
 * Created by irinix on 9/22/14.
 */
public class Logger {

	private String LogPath;
	private String LogFilePath;
	private String dateStamp;


	public Logger(String LogPath, int flag) throws IOException {
		this.LogPath = LogPath;
		if (!initLogger(flag)) {
			System.out.println("Error Initializing Logger");
		}
	}

	/**
	 * *@param flag can be 0, 1, use 1 for IRC Channel logging.
	 */
	private boolean initLogger(int flag) throws IOException {
		dateStamp = generateDateStamp();
		boolean success = false;
		File file = new File(LogPath);
		if (!file.exists()) {
			System.out.println("Logging Directory Does Not Exist.  Creating: ");
			success = file.mkdirs();
		}
		else if (file.exists()) {
			System.out.println("Logging Directory Exists.  Using Directory: " + LogPath);
			success = true;
		}
		if (flag == 0) {
			LogFilePath = LogPath + "/" + dateStamp;
			log("=======New Run At: " + generateTimeStamp());
		}

		return success;
	}

	private String generateTimeStamp() {
		return new Date().toString().substring(10, 19).replace(" ", "::");
	}

	public void log(String ChannelName, String logMsg) throws IOException {
		LogPath += ChannelName.replace("#", "");
		initLogger(0);
		LogFilePath = LogPath + "/" + generateDateStamp();
		log(generateTimeStamp() + "::" + logMsg);
	}

	private String generateDateStamp() {
		return new Date().toString().substring(0, 10).replace(" ", "-").trim();
	}

	public void log(String logMsg) throws IOException {

		if (!dateStamp.equals(generateDateStamp())) {
			dateStamp = generateDateStamp();
			LogFilePath = LogPath + "/" + dateStamp;
		}
		String timeStamp = new Date().toString().substring(10, 19).trim().replace(" ", "-");
		logMsg = timeStamp + " :: " + logMsg;
		try (PrintWriter out = new PrintWriter(
			   new BufferedWriter(new FileWriter(LogFilePath, true)))) {
			out.println(logMsg);
		}
	}

}

