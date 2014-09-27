package com.badwater.bot.helpers.Readers;

import com.badwater.bot.helpers.helperFuncs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

				//ignore comments
				if (!line.startsWith("#")) {
					lines.add(line);
				}
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

	public boolean isUserAuthorized(String userName, String cmdName) throws IOException {
		System.out.println(cmdName);
		System.out.println(userName);
		read();
		for (String s : lines) {
			System.out.println(s);
			String[] args = s.split("=");
			if (args[0].equalsIgnoreCase(userName)) {
				args = args[1].split(",");
				for (String s1 : args) {
					System.out.println(s1);
					if ((s1.equalsIgnoreCase("all")) || s1.equalsIgnoreCase(cmdName)) {
						System.out.println(s1);
						return true;

					}
				}
			}
		}
		return false;
	}
}
