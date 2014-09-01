package com.badwater.bot.core.gamecore.actors;

import com.badwater.bot.core.gamecore.Room;

import java.util.ArrayList;

/**
 * Created by Robert on 8/31/2014.
 */
public abstract class AbstractActor {

	private String name;

	private int health;
	private int damage;
	private int armor;
	private int level;
	private int maxTargets;

	private boolean alive;

	private ArrayList<AbstractActor> targets     = new ArrayList<>();
	private ArrayList<Integer>       healQueue   = new ArrayList<>();
	private ArrayList<Integer>       damageQueue = new ArrayList<>();

	private Room currentRoom;

	public AbstractActor() {

	}

	public Room getCurrentRoom() {
		return currentRoom;
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public void setCurrentRoom(Room r) {
		currentRoom = r;
	}

	public String[] getInfo() {
		String[] info = {"Health: " + health, "Damage: " + damage, "Armor: " + armor, "Level" + level};
		return info;
	}

	public String getName() {
		return name;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	private void chooseTarget(AbstractActor target) {
		targets.add(target);
	}

	public void onUpdate() {
		if (!checkForDeath()) {
			processHealQueue();
			processDamageQueue();
			attack();
		}
	}

	public void heal(int incomingHeals) {
		healQueue.add(incomingHeals);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void takeDamage(AbstractActor source, int incomingDamage) {
		int damage = applyArmor(incomingDamage);
		System.out.println("Taking " + damage + " damage");
		health -= damage;
		//damageQueue.add(incomingDamage);

	}

	private void processHealQueue() {
		if (!healQueue.isEmpty()) {
			for (int i : healQueue) {
				health += i;
			}
		}
	}

	private void processDamageQueue() {
		if (!damageQueue.isEmpty()) {
			for (int i : damageQueue) {

				health = applyArmor(i);
				System.out.println("Took " + applyArmor(i) + " Damage!");

				checkForDeath();
			}
			damageQueue.clear();
		}
	}

	private boolean checkForDeath() {
		if (health <= 0) {
			die();
			System.out.println("Urrrrrghhhhhhhhgurglegurglgurgle");
			return true;
		}
		else {
			return false;
		}
	}

	private int applyArmor(int incomingDamage) {
		return health - (incomingDamage - armor);
	}

	private void attack() {
		if (!targets.isEmpty()) {
			for (AbstractActor target : targets) {
				target.takeDamage(this, damage);
			}
		}
	}

	private void die() {
		alive = false;
	}

	public boolean isAlive() {
		return alive;
	}

}
