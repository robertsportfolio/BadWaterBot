package com.badwater.bot.helpers.Loggers;

import java.io.*;
import java.util.Date;

/**
 * Created by irinix on 9/22/14.
 */
public class Logger {

	private final String LogPath;
	private       String LogFilePath;
	private       String dateStamp;


	public Logger(String LogPath) throws IOException {
		this.LogPath = LogPath;
		if (!initLogger()) {
			System.out.println("Error Initializing Logger");
		}
	}

	private boolean initLogger() throws IOException {
		dateStamp = new Date().toString().substring(0, 9).replace(" ", "-").trim();
		boolean success = false;
		File file = new File(LogPath);
		if (!file.exists()) {
			System.out.println("Logging Directory Does Not Exist.  Creating it.");
			success = file.mkdirs();
		}
		else if (file.exists()) {
			System.out.println("Logging Directory Exists.  Using Directory: " + LogPath);
			success = true;
		}
		LogFilePath = LogPath + "/" + dateStamp;

		log("=======New Run At: " + new Date().toString());


		return success;
	}

	public void log(String logMsg) throws IOException {
		String dateStampCheck = new Date().toString().substring(0, 10).replace(" ", "-").trim();
		System.out.println(dateStampCheck);
		if (!dateStamp.equalsIgnoreCase(dateStampCheck)) {
			dateStamp = dateStampCheck;
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

