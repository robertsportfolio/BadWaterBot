package com.badwater.bot.core.gamecore;

import com.badwater.bot.core.gamecore.actors.AbstractActor;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public class Room {
	//private treasure<AbstractTreasure> treasures;  <<DELETE THIS COMMENT: add setter when Implementing this.>>

	private String name = "";
	private String[] info;
	private ArrayList<AbstractActor> actors = new ArrayList<>();

	public Room() {

	}

	public ArrayList<AbstractActor> getActors() {
		return actors;
	}

	public String[] getInfo() {
		return info;
	}

	public void setActors(ArrayList<AbstractActor> buildActors) {
		actors.addAll(buildActors);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void onUpdate() {
		ArrayList<AbstractActor> removeUs = new ArrayList<>();
		for (AbstractActor a : actors) {
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
