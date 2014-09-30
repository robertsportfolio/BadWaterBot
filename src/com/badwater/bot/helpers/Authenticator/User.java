package com.badwater.bot.helpers.Authenticator;

import java.util.ArrayList;

/**
 * Created by irinix on 9/29/14.
 */
public class User {
	private String UserName;
	private ArrayList<String> AuthorizedFor = new ArrayList<String>();


	public void addCommand(String cmd){
		if(!AuthorizedFor.contains(cmd)) {
			AuthorizedFor.add(cmd);
		}
	}

	public boolean isAuthorized(String cmd) {
		return AuthorizedFor.contains(cmd);
	}
}