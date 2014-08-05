package com.badwater.bot.core;

import com.badwater.bot.managerCommands.newGameCommand;

/**
 * Created by irinix on 8/4/14.
 */
public class GameManagerListener extends Listener {


	public GameManagerListener() {
		commands.add ( new newGameCommand () );
	}


}
