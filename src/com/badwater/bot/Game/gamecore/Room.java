package com.badwater.bot.Game.gamecore;

import com.badwater.bot.Game.gamecore.actors.AbstractActor;
import com.badwater.bot.helpers.helperFuncs;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public class Room {
	//private treasure<AbstractTreasure> treasures;  <<DELETE THIS COMMENT: add setter when Implementing this.>>

	private String name = "";
	private String[]      info;
	private AbstractActor player;


	private ArrayList<AbstractActor> actors = new ArrayList<>();

	public Room() {

	}

	public ArrayList<AbstractActor> getActors() {
		return actors;
	}

	public void setActors(ArrayList<AbstractActor> buildActors) {
		actors.addAll(buildActors);
	}

	public String[] getInfo() {
		return info;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AbstractActor getPlayer() {
		return player;
	}

	public void setPlayer(AbstractActor incPlayer) {
		player = incPlayer;
	}

	public void onUpdate() {
		ArrayList<AbstractActor> removeUs = new ArrayList<>();
		for (AbstractActor a : actors) {
			helperFuncs.debugGotHere("Got To Update Loop on Room " + this.getName() + " and enemy " + a.getName());
			if (a.isAlive()) {
				System.out.println(a.getHealth());
				a.onUpdate();
			}
			else {
				removeUs.add(a);
			}
		}
		actors.removeAll(removeUs);
	}


}
