package com.badwater.bot.helpers.Authenticator;

import com.badwater.bot.helpers.Readers.bwFileReader;
import com.badwater.bot.helpers.Writers.bwFileWriter;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

/**
 * Created by irinix on 9/29/14.
 */
public class Authenticator {
	private final bwFileWriter userConfigWriter;
	private HashMap<String, String> Users = new HashMap<String, String>();

public Authenticator(Connection dbConn) throws IOException {
	;
		userConfigWriter = new bwFileWriter("./DB/Configs/.authUsers");
		Users = new bwFileReader("./DB/Configs/.authUsers").getUsers();
	}

	public boolean isUserAuthorizedForCmd(String userName, String command) {
		boolean authorized = false;
		System.out.println(Users.get(userName));
		if (Users.containsKey(userName.toLowerCase())) {
			if (Users.containsKey(userName)) {
				if(Users.get(userName).equalsIgnoreCase("all")){
					System.out.println(userName + " : " + command + " : " + authorized);
					authorized = true;
				}
				else{
					for(String s1 : Users.get(userName).split(",")){
						if(s1.equalsIgnoreCase(command)){
							authorized = true;
						}
					}
				}
			}
		}
		return authorized;
	}

	public void addUserAuthentication(String userNameAndCommands) throws IOException {
		String userName = userNameAndCommands.split("=")[0];
		String[] commands = userNameAndCommands.split("=")[1].split(",");
		userConfigWriter.writeToConfig(userName, commands);
	}
}

