package com.badwater.bot.Game.gamecore.actors;

import com.badwater.bot.helpers.helperFuncs;

/**
 * Created by irinix on 9/9/14.
 */
public class Player extends AbstractActor {
	public Player(String playerName) {
		setName(playerName);
		setHealth(helperFuncs.nextIntInRange(15, 10));
		setArmor(helperFuncs.nextIntInRange(10, 5));
		setDamage(helperFuncs.nextIntInRange(6, 3));
	}
}
