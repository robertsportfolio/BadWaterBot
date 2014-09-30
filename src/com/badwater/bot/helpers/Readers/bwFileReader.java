package com.badwater.bot.helpers.Readers;

import com.badwater.bot.helpers.helperFuncs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by irinix on 9/22/14.
 */
public class bwFileReader {
	private String pathToFile;
	private ArrayList<String> lines = new ArrayList<String>();


	public bwFileReader(String pathToFile) {
		this.pathToFile = pathToFile;
	}

	public void read() throws IOException {
		helperFuncs.debugGotHere("Read(" + pathToFile + ")");

		try (BufferedReader in = new BufferedReader(new FileReader(pathToFile))) {
			String line = "";
			while ((line = in.readLine()) != null) {
				lines.add(line);
			}

		}
	}

	public ArrayList<String> getLines() {
		return lines;
	}

	public String getAPIKey(String requestedKey) throws IOException {
		read();
		helperFuncs.debugGotHere(pathToFile);
		for (String s : lines) {

			if (s.contains("=")) {
				String[] args = s.split("=");
				if (args[0].equalsIgnoreCase(requestedKey)) {
					return args[1];
				}

			}
		}

		return null;
	}

	public HashMap<String, String[]> getConfigData() throws IOException {
		read();
		String key = "";
		HashMap<String, String[]> configData = new HashMap<String, String[]>();
		for (String s : lines) {
			String[] args1 = s.split("=");
			key = args1[0];
			args1 = args1[1].split(",");
			configData.put(key, args1);
		}

		return configData;
	}

	public HashMap<String, String> getUsers() throws IOException {
		read();
		HashMap<String, String> userList = new HashMap<String, String>();
		for (String s : lines) {
			if (!s.startsWith("#")) {
				String[] splitString = s.split("=");
				String userName = splitString[0];
				String commands = splitString[1];
				userList.put(userName, commands);
			}
		}
		return userList;
	}


	public boolean isUserAuthorized(String userName, String cmdName) throws IOException {
		read();
		for (String s : lines) {
			System.out.println(s);
			String[] args = s.split("=");
			if (args[0].equalsIgnoreCase(userName)) {
				args = args[1].split(",");
				if (args[0].equalsIgnoreCase("all")) {
					System.out.println(userName + " " + args[0]);
					return true;
				}
				else {
					for (String s1 : args) {
						System.out.println(userName + " " + s1);
						if (s1.equalsIgnoreCase(cmdName)) {
							System.out.println(s1);
							return true;

						}
					}
				}
			}
		}
		return false;
	}
}
