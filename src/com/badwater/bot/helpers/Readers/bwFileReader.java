package com.badwater.bot.helpers.Readers;

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
}
