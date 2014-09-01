package com.badwater.bot.core.gamecore.builders;

import com.badwater.bot.core.gamecore.actors.AbstractActor;
import com.badwater.bot.core.gamecore.actors.Enemy;
import com.badwater.bot.helpers.helperFuncs;

/**
 * Created by Robert on 8/31/2014.
 */
public class EnemyBuilder {
	public EnemyBuilder() {
	}

	public AbstractActor buildRandomEnemy() {
		Enemy e = new Enemy();
		e.setName("Vile Creature");
		e.setHealth(helperFuncs.nextIntInRange(10, 5));
		e.setArmor(helperFuncs.nextIntInRange(2, 1));
		e.setDamage(helperFuncs.nextIntInRange(4, 2));
		return e;
	}
}
