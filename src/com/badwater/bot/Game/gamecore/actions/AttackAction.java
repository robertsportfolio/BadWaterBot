package com.badwater.bot.Game.gamecore.actions;

import com.badwater.bot.Game.gamecore.actors.AbstractActor;

/**
 * Created by Robert on 8/31/2014.
 */
public class AttackAction implements IAction {
	@Override
	public void exec(AbstractActor source, AbstractActor target, int amount) {
		target.takeDamage(source, amount);
	}

	@Override
	public String getAlias() {
		return "Attack";
	}
}
