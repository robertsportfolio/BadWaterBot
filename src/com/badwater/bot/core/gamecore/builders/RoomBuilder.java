package com.badwater.bot.core.gamecore.builders;

import com.badwater.bot.core.gamecore.Room;
import com.badwater.bot.core.gamecore.actors.AbstractActor;
import com.badwater.bot.helpers.helperFuncs;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public class RoomBuilder {
	private static final int MAX_ENEMIES = 5;
	private int shhh;

	public RoomBuilder() {
		helperFuncs.debugGotHere("ROOM BUILDER");

		shhh = helperFuncs.nextIntInRange(MAX_ENEMIES, 3);
		System.out.println("\t\t" + shhh);
	}

	private ArrayList<AbstractActor> createEnemies() {
		ArrayList<AbstractActor> enemies = new ArrayList<AbstractActor>();
		EnemyBuilder e = new EnemyBuilder();
		for (int i = 0; i < shhh; i++) {
			enemies.add(e.buildRandomEnemy());
		}
		return enemies;
	}



	public Room build() {
		Room r = new Room();
		System.out.println("Building Room With " + shhh + " Enemies");
		r.setActors(createEnemies());
		r.setName("RoomBuilder test");
		System.out.println("Room Built Succesfully!");
		return r;

	}
}