package com.badwater.bot.helpers.Writers;

import com.badwater.bot.helpers.Readers.bwFileReader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by irinix on 9/24/14.
 */
public class bwFileWriter {

	public bwFileWriter(String path) {

	}

	public void writeToConfig(String configPath, String key, String[] values) throws IOException {
		File file = new File(configPath);
		ArrayList<String> lines;
		String editLine = "";
		if (file.exists()) {
			lines = new bwFileReader(configPath).getLines();

			for (String s : lines) {
				if (s.startsWith(key)) {
					editLine = s;
				}
			}
			int index;
			if (!editLine.equals("")) {
				index = lines.indexOf(editLine);
				lines.remove(editLine);

				for (String s : values) {
					if (!editLine.contains(s)) {
						editLine += "," + s;
					}
				}
				lines.add(index, editLine);
			}
			else {
				editLine = key + "=";
				for (String s : values) {
					editLine += s + ",";
				}
				if (editLine.endsWith(",")) {
					editLine = editLine.substring(0, editLine.length() - 1);
				}
				lines.add(editLine);
			}
		}
		//if the file doesn't exist split our path into an array
		else {
			String[] pathToFile = configPath.split("\\/");
			//pathToFile.length - 1 so that we only check the path.
			boolean success = false;
			for (int i = 0; i < pathToFile.length - 2; i++) {
				file = new File(pathToFile[i]);
				if (!file.exists()) {
					success = file.mkdir();
				}
			}
			//once we've created the directories we need.
			if (success) {
				int i = pathToFile.length - 1;
				//make the file
				file = new File(pathToFile[i]);
			}
			editLine = key + "=";
			for (String s : values) {
				editLine += s + ",";
			}
			//trim off that final comma
			if (editLine.endsWith(",")) {
				editLine = editLine.substring(0, editLine.length() - 1);
			}
			lines = new ArrayList<String>();
			lines.add(editLine);
		}
		try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
			for (String s : lines) {
				out.write(s + "\n");
			}
		}

	}
}

